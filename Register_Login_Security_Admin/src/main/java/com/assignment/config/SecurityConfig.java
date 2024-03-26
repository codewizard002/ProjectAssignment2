package com.assignment.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	public CustomAuthSucessHandler successHandler;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsService getDetailsService() {
		return new CustomUserDetailsService();
	}

	@Bean
	public DaoAuthenticationProvider getAuthenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(getDetailsService());
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		return daoAuthenticationProvider;
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
	{
		/*http.csrf().disable()
		.authorizeHttpRequests().requestMatchers("/","/register","/signin","/saveUser").permitAll()
		.requestMatchers("/user/**").authenticated().and()
		.formLogin().loginPage("/signin").loginProcessingUrl("/userLogin")
		//.usernameParameter("email")
		.defaultSuccessUrl("/user/profile").permitAll();
		return http.build();*/
		
		http.csrf().disable()
		.authorizeHttpRequests().requestMatchers("/user/**").hasRole("USER")
         .requestMatchers("/admin/**").hasRole("ADMIN")
		.requestMatchers("/admin/**").authenticated()
		.requestMatchers("/**").permitAll().and()
		.formLogin().loginPage("/signin").loginProcessingUrl("/userLogin")
		.successHandler(successHandler)
		.permitAll();
		return http.build();
	}

	
	/* Implementation using SecurityFilterChain 
	 * @Bean public SecurityFilterChain filterChain(HttpSecurity http) throws
	 * Exception {
	 * 
	 * //this is for user http.authorizeHttpRequests( auth ->
	 * auth.requestMatchers("/","/register","/signin","/saveUser").permitAll()
	 * .requestMatchers("/user/**").authenticated()
	 * .requestMatchers("/users/**").hasAuthority("ADMIN")
	 * .requestMatchers("/myapps/**").hasAuthority("USER")
	 * .requestMatchers("/users/**").hasAuthority("") .anyRequest().authenticated()
	 * ) .formLogin(formLogin -> formLogin .loginPage("/signin")
	 * .usernameParameter("email") .defaultSuccessUrl("/user/profile", true)
	 * .permitAll() )
	 * 
	 * .logout(logout -> logout.logoutUrl("/signout").permitAll());
	 * 
	 * 
	 * return http.build();
	 * }
	 */
}
