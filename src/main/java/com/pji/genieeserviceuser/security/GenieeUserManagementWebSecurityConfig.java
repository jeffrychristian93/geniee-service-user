package com.pji.genieeserviceuser.security;

import com.pji.genieeserviceuser.auth.security.JsonWebTokenSecurityConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan(basePackages = "com.pji.genieeserviceuser.auth.security")
public class GenieeUserManagementWebSecurityConfig extends JsonWebTokenSecurityConfig {

	@Override
	protected void setupAuthorization(HttpSecurity http) throws Exception {
		http.authorizeRequests()

				// allow anonymous access to /authenticate endpoint
				.antMatchers("/authenticate").permitAll()

				// authenticate all other requests
				.anyRequest().authenticated();
	}
}
