package com.example.shoperestweb.config;

import com.example.shoperestweb.service.UserDetailsServiceImpl;
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

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());

    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/product-categories/**", "/users").permitAll() // Allow all for testing
                .antMatchers(HttpMethod.GET, "/products/**").permitAll() // Allow GET to products without authentication
                .antMatchers(HttpMethod.POST, "/product-categories").hasAnyRole("ADMIN", "USER") // Allow POST to product-categories for ADMIN and USER roles
                .antMatchers(HttpMethod.POST, "/products").hasAnyRole("ADMIN", "USER") // Allow POST to products for ADMIN and USER roles

                .antMatchers(HttpMethod.PUT, "/product-categories/**").permitAll() // Allow PUT to product-categories without authentication
                .antMatchers(HttpMethod.PUT, "/products/**").permitAll() // Allow PUT to products without authentication
                .antMatchers(HttpMethod.DELETE, "/product-categories/**").permitAll() // Allow DELETE to product-categories without authentication
                .antMatchers(HttpMethod.DELETE, "/products/**").permitAll() // Allow DELETE to products without authentication
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .httpBasic();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
