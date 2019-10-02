package com.ticketmaster.services.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityJavaConfig extends WebSecurityConfigurerAdapter {
    private static final String URL = "/api/users/**";
    private static final String ROLE_ADMIN = "ADMIN";
    private static final String ROLE_CASHIER = "CASHIER";

    @Autowired
    private DataSource dataSource;

    @Override
    public void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(bcryptPasswordEncoder())
                .usersByUsernameQuery("SELECT username, password, true FROM user WHERE username=?")
                .authoritiesByUsernameQuery("SELECT username, role FROM user WHERE username=?");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/api/users/username").permitAll()
                .antMatchers(HttpMethod.GET, URL).hasAnyRole(ROLE_ADMIN, ROLE_CASHIER)
                .antMatchers(HttpMethod.POST, URL).hasRole(ROLE_ADMIN)
                .antMatchers(HttpMethod.PUT, URL).hasAnyRole(ROLE_ADMIN, ROLE_CASHIER)
                .antMatchers(HttpMethod.DELETE, URL).hasRole(ROLE_ADMIN)
                .and()
                .csrf().disable()
                .formLogin().disable();


        http.headers()
                .frameOptions()
                .sameOrigin();
    }

    @Bean
    PasswordEncoder bcryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SimpleUrlAuthenticationFailureHandler myFailureHandler() {
        return new SimpleUrlAuthenticationFailureHandler();
    }
}
