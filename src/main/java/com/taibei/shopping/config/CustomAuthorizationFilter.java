package com.taibei.shopping.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@Slf4j
public class CustomAuthorizationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        String token;

        log.info("--------CustomAuthorizationFilter------doFilterInternal-------------");

        if (httpServletRequest.getServletPath().equals("/api/v1/login")){

            filterChain.doFilter(httpServletRequest,httpServletResponse);

        }else {

            // request must have header with Authorization beginning with Bearer:
            String authorizationHeader= httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);

            if(authorizationHeader!=null && authorizationHeader.startsWith("Bearer:")){


                try {

                    token =authorizationHeader.substring("Bearer:".length());
                    log.info("----CustomAuthorizationFilter---token:{}",token);

                    Algorithm  algorithm =Algorithm.HMAC256("secret".getBytes());

                    JWTVerifier  jwtVerifier= JWT.require(algorithm).build();
                    DecodedJWT   decodedJWT= jwtVerifier.verify(token);
                    String userName =decodedJWT.getSubject();
                    String[] roles =decodedJWT.getClaim("roles").asArray(String.class);
                    Collection<SimpleGrantedAuthority> authorities= new ArrayList<>();

                    Arrays.stream(roles).forEach(role->
                            authorities.add(new SimpleGrantedAuthority(role)));

                    log.info("----CustomAuthorizationFilter---roles:{}",roles);

                    UsernamePasswordAuthenticationToken  authenticationToken=
                            new UsernamePasswordAuthenticationToken(userName,null,authorities);

                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                    filterChain.doFilter(httpServletRequest,httpServletResponse);


                } catch (Exception e) {

                    log.error("Error login :{}",e.getMessage() !=null ? e.getMessage():" invalid token.");
                    //  httpServletResponse.setHeader("error",e.getMessage());
                    //  httpServletResponse.sendError(HttpStatus.FORBIDDEN.value());

                    httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
                    Map<String,String> errors = new HashMap<>();
                    errors.put("msg",e.getMessage() != null ? e.getMessage():"invalid token." );

                    httpServletResponse.setContentType(APPLICATION_JSON_VALUE);
                    new ObjectMapper().writeValue(httpServletResponse.getOutputStream(),errors);

                }

            }else {

                filterChain.doFilter(httpServletRequest,httpServletResponse);

            }


        }


    }
}
