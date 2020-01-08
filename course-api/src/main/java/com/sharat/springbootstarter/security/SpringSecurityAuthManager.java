package com.sharat.springbootstarter.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SuppressWarnings("deprecation")
@EnableWebSecurity
public class SpringSecurityAuthManager extends WebSecurityConfigurerAdapter {

	private static final String[] AUTH_WHITELIST = {
	        "/swagger-resources/**",
	        "/swagger-ui.html",
	        "/v2/api-docs",
	        "/webjars/**"
	};

	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// In memory authorization
		/**auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN").and().withUser("cs").password("cs")
				.roles("CS").and().withUser("user").password("user").roles("USER");**/
		
		// JPA based authentication
		auth.userDetailsService(userDetailsService);
	}

	// this will be used by spring security to encode the input for validation the
	// password
	@Bean
	public PasswordEncoder getPasswordencoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	// The urls configured explicitly needs to be added for admin as well otherwise
	// it will feel for those urls only specific ROLES are allowed
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.authorizeRequests().antMatchers(AUTH_WHITELIST).authenticated().antMatchers("*.js", "*.img").permitAll().antMatchers("/").permitAll()
				.antMatchers("/**").hasRole("ADMIN").antMatchers("/courses", "/topics").hasAnyRole("ADMIN", "CS", "USER")
				.antMatchers("/courses/**", "/topics/**").hasAnyRole("ADMIN", "CS", "USER").and().formLogin();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
	    web.ignoring().antMatchers(AUTH_WHITELIST);
	}


}
