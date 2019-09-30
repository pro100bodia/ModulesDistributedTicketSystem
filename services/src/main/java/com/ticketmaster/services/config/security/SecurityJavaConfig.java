package com.ticketmaster.services.config.security;

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
import org.springframework.web.servlet.DispatcherServlet;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityJavaConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    DispatcherServlet dispatcherServlet;

    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Autowired
    private TicketMasterRequestAwareAuthenticationSuccessHandler mySuccessHandler;

    @Autowired
    private DataSource dataSource;

    @Override
    public void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
//                .passwordEncoder(auth)
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
                .antMatchers(HttpMethod.GET, "/api/users/**").hasAnyRole("ADMIN", "CASHIER")
                .antMatchers(HttpMethod.POST, "/api/users/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/users/**").hasAnyRole("ADMIN", "CASHIER")
                .antMatchers(HttpMethod.DELETE, "/api/users/**").hasRole("ADMIN")
                .and()
                .csrf().disable()
                .formLogin().disable();

        http.csrf()
                .ignoringAntMatchers("/h2-console/**");
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
