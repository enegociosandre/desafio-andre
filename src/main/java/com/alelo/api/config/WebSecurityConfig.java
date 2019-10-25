package com.alelo.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
    @Override
    protected void configure(HttpSecurity httpSecurity) {
        try {
			httpSecurity
		        .authorizeRequests()
		        .antMatchers("/", "/swagger-resources")
		        .permitAll();
	        httpSecurity
	            .csrf()
	            .disable();
		    httpSecurity
	            .headers()
	            .frameOptions()
	            .disable();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
