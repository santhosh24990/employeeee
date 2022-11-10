package com.glearning.employees.config;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

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

import com.glearning.employees.Service.DomainUserDetailsService;
import com.glearning.employees.Service.EmployeeService;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {

	private final DomainUserDetailsService userDetailsService;
	protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
	
         authenticationManagerBuilder
                 .userDetailsService(this.userDetailsService)
                 .passwordEncoder(passwordEncoder());
	}
	 @Override
	    protected void configure(HttpSecurity httpSecurity) throws Exception {
	        httpSecurity.cors().disable();
	        httpSecurity.csrf().disable();
	        httpSecurity.headers().frameOptions().disable();
	        httpSecurity
	                .authorizeRequests()
	                	.antMatchers(GET,"/api/employees/**","/api/role/**")
	                	.hasAnyRole("USER","ADMIN","MANAGER")	
	                	.antMatchers("/h2-console/**","/login**","/contact-us**")
	                	.permitAll()
	                .and()
	                .authorizeRequests()
	                .antMatchers(HttpMethod.POST,"/api/employees/**","/api/role/**")
	                	.hasRole("ADMIN")
	                .antMatchers(HttpMethod.PUT,"/api/employees/**","/api/role/**")
	                	.hasRole("ADMIN")
	                .antMatchers(HttpMethod.DELETE,"/api/employees/**","/api/role/**")
	                	.hasRole("MANAGER")
	                .anyRequest()
	                	.authenticated()
	                .and()
	                	.formLogin()
	                .and()
	                	.httpBasic()
	               .and()
	                .sessionManagement().sessionCreationPolicy(STATELESS);
	    }
	   @Bean
       public PasswordEncoder passwordEncoder() {
          return new BCryptPasswordEncoder();
       }
	   @Bean
	   public EmployeeService employeeservice() {
		   return new EmployeeService();
	   }
}
