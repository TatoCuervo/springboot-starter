package com.tatocuervo.springbootstarter.common.security;

import com.tatocuervo.springbootstarter.common.security.filter.JwtRequestFilter;
import com.tatocuervo.springbootstarter.routes.Routes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static java.lang.String.format;

/**
 * Spring security configuration
 */

@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Autowired
    private JpaUserDetailService jpaUserDetailService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(jpaUserDetailService).passwordEncoder(getPasswordEncoder());
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .mvcMatchers(buildPublicRoutes()).permitAll()
                .mvcMatchers(buildPrivateRoutes()).authenticated() //TODO: try matching ROLE
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // use stateless session

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class); // set jwt request filter
    }

    private String[] buildPublicRoutes() {
        return new String[]{
                format("/%s/**", Routes.AUTHENTICATE),
        };
    }

    private String[] buildPrivateRoutes() {
        return new String[]{
                format("/%s/**", Routes.API_VERSION),
        };
    }

    @Bean
    PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
