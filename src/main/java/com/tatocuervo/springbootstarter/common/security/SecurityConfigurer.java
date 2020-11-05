package com.tatocuervo.springbootstarter.common.security;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //TODO: setup authentication with JPA (postgres)
        super.configure(auth);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //TODO: setup authorization permissions based on path & roles
        http.csrf().disable()
                .authorizeRequests().antMatchers("/").permitAll();
    }
}
