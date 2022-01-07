package com.taibei.shopping.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;


@Configuration @EnableWebSecurity @RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {



    final  private UserDetailsService userDetailsService;
    final  private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.authenticationProvider(daoAuthenticationProvider());
    }

     // configure the parameter that don't go through any filter.
    @Override
    public void configure(WebSecurity web) throws Exception {

        web.ignoring().antMatchers("/api/v1/token/refresh/**",
                                   "/api/v1/product/**","/api/v1/registerConsumer");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //authenticate login information.
        CustomAuthenticationFilter customAuthenticationFilter =new CustomAuthenticationFilter(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/api/v1/login");

        http.cors();
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // permitall only show the privilege without ristriction of role. however, the request need to walk through the filters.
//        http.authorizeRequests().antMatchers("/api/v1/login").permitAll();

        http.authorizeRequests().antMatchers(GET,"/api/v1/**").hasAnyAuthority("ROLE_USER");
        http.authorizeRequests().antMatchers(POST,"/api/v1/add**").hasAnyAuthority("ROLE_ADMIN");
        http.authorizeRequests().anyRequest().authenticated();

        //http.addFilter(new CustomAuthenticationFilter(authenticationManagerBean()));
        http.addFilter(customAuthenticationFilter);
        //CustomAuthorizationFilter is before UsernamePasswordAuthenticationFilter
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

    }

    @Bean
    @Override
    public  AuthenticationManager  authenticationManagerBean() throws Exception{

        return  super.authenticationManagerBean();

    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){

        DaoAuthenticationProvider daoAuthenticationProvider= new DaoAuthenticationProvider();
        daoAuthenticationProvider.setHideUserNotFoundExceptions(false);
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder);
        return  daoAuthenticationProvider;
    }

}
