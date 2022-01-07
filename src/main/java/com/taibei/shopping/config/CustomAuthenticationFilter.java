package com.taibei.shopping.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {


    final private AuthenticationManager authenticationManager;
    private final static UrlPathHelper urlPathHelper = new UrlPathHelper();

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        //return super.attemptAuthentication(request, response);
        String userName ="";
        String passWord ="";

        //when Content-Type is json
        if(request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)){

            ObjectMapper mapper = new ObjectMapper(); //use jackson to deserialize json

            try (InputStream is = request.getInputStream()){

                com.taibei.shopping.entity.User authenticationBean =
                        mapper.readValue(is,com.taibei.shopping.entity.User.class);

                userName = authenticationBean.getUserName();
                passWord = authenticationBean.getPassWord();

            }catch (IOException e) {
                e.printStackTrace();
            }

        }else {

             userName = request.getParameter("userName");
             passWord = request.getParameter("passWord");

        }

        log.info("User name is: {},password is:{}",userName,passWord);


        UsernamePasswordAuthenticationToken authenticationToken= new UsernamePasswordAuthenticationToken(userName,passWord);

        //认证,可以使用security提供的built-in 认证方法,也可以use custom athentication method.
        //认证成功或者失败，分别调用下面成功方法或者失败方法
        return authenticationManager.authenticate(authenticationToken);


    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException {

        //super.successfulAuthentication(request, response, chain, authResult);

        User user =(User) authResult.getPrincipal();
        List roleList=user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());

        Algorithm algorithm =Algorithm.HMAC256("secret".getBytes()); // used for signing the jwt.
        String accessToken = JWT.create()
                                .withSubject(user.getUsername())
                                .withExpiresAt(new Date(System.currentTimeMillis()+30*60*1000))
                                .withIssuer(request.getRequestURI())
                                .withClaim("roles",roleList)
                                .sign(algorithm);

        String refreshToken = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis()+300*60*1000))
                .withIssuer(request.getRequestURI())
                .sign(algorithm);

        log.info("-----successfulAuthentication---user {} has roles:{}",user.getUsername(),roleList);
        log.info("-----successfulAuthentication---user {} has access_token:{}",user.getUsername(),accessToken);

//        response.setHeader("access_token",accessToken);
//        response.setHeader("refresh_token",refreshToken);

        Map<String,String> tokens = new HashMap<>();
        tokens.put("accessToken",accessToken);
        tokens.put("refreshToken",refreshToken);

        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(),tokens);
    }


    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {

        log.info("failed authentication while attempting to access "+ urlPathHelper.getPathWithinApplication(request));

        String  errorMsg=failed.getMessage();
        if("Bad credentials".equals(errorMsg))  errorMsg="password incorrect!";

        response.setStatus(HttpStatus.UNAUTHORIZED.value());

        //  response.setContentType(MediaType.TEXT_PLAIN_VALUE);
        //  response.getWriter().print(errorMsg);
        //  response.getWriter().flush();

        Map<String,String> errors = new HashMap<>();
        errors.put("errorMessage",errorMsg);
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(),errors);

    }

}
