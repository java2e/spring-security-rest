package org.demircioglu.config;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
/***
 * 
 * @author mdemircioglu
 *
 *define security configuration for rest service 
 *HttpMethod authenticated url
 */


@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) 
	{
		try {
			
			http
			.csrf().disable()
			.authorizeRequests()
			 .antMatchers(HttpMethod.POST, "/api/**").authenticated()
		        .antMatchers(HttpMethod.PUT, "/api/**").authenticated()
		        .antMatchers(HttpMethod.DELETE, "/api/**").authenticated()
		        .anyRequest().permitAll()
		        .and()
		      .httpBasic().and()
		      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
			
		} catch (Exception e) {
			
			Logger.getLogger(SecurityConfiguration.class.getSimpleName()).error("Throws by security config that is :"+e.getMessage());;
			// TODO: handle exception
		}
		
		
	}
	


}
