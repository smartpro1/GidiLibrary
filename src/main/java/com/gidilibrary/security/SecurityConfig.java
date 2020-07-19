
package com.gidilibrary.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.authorizeRequests()
				.antMatchers(
						"/",
						"/**/*.png",
						"/**/*.gif",
						"/**/*.svg",
						"/**/*.jpg",
						"/**/*.html",
						"/**/*.css",
						"/**/*.js",
						"/v2/api-docs",
						"/configuration/ui",
						"/swagger-resources/**",
						"/configuration/security",
						"/swagger-ui.html",
						"/webjars/**"
				).permitAll()
                .antMatchers("/console/**")
				.hasRole("LIBRARIAN")
				.anyRequest().authenticated()
				.and()
				.formLogin();
		http.csrf().disable();
		http.headers().frameOptions().disable();
	}

	@Bean
	@Override
	protected UserDetailsService userDetailsService() {

		UserDetails librarian = User.builder()
				          .username("john")
				          .password(passwordEncoder().encode("password"))
				          .roles("LIBRARIAN")
				          .build();

		return new InMemoryUserDetailsManager(librarian);
	}

}
