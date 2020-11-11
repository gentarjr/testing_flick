package com.app.template.config;

import com.app.template.service.ServiceLogin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
    
    @Autowired
	private BCryptPasswordEncoder bCryptPassword;
	@Autowired
    private CustomizeAuthenticationSuccessHandler customizeHandler;
    @Autowired
    ServiceLogin service;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests().antMatchers("/").permitAll().antMatchers("/login").permitAll().antMatchers("/home")
		.authenticated().and().csrf().disable().formLogin()
		.successHandler((AuthenticationSuccessHandler) customizeHandler).loginPage("/login")
		.failureUrl("/").usernameParameter("username").passwordParameter("password").and()
		.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/").and()
		.exceptionHandling().accessDeniedPage("/access-denied");
    }

    @Override
	public void configure(WebSecurity web) throws Exception{
		web.ignoring().antMatchers("/css/**", "/fonts/**", "/images/**", "/js/**", "/less/**", "/plugins/**",
				"/slider/**");
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(service).passwordEncoder(bCryptPassword);
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder(){
		BCryptPasswordEncoder bCryptPassword = new BCryptPasswordEncoder();
		return bCryptPassword;
    }
    
}
