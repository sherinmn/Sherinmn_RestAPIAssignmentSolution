package com.greatlearning.employee.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.greatlearning.employee.service.impl.UserDetailsServiceImpl;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public UserDetailsServiceImpl getMyUserDetailsService() {
		return new UserDetailsServiceImpl();
	}

	@Bean
	public BCryptPasswordEncoder getPassWordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(getMyUserDetailsService()).passwordEncoder(getPassWordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()

				.antMatchers("/user", "/role").hasAuthority("ADMIN")
				.antMatchers("/list").hasAnyAuthority("USER", "ADMIN")
				.antMatchers("/showFormForAdd").hasAnyAuthority("USER","ADMIN")
				.antMatchers("/showFormForUpdate").hasAuthority("ADMIN")
				.antMatchers("/delete").hasAuthority("ADMIN")
				.antMatchers("/sort").hasAnyAuthority("USER","ADMIN")
				.antMatchers("/search").hasAnyAuthority("USER","ADMIN")
				.antMatchers("/403").hasAnyAuthority("USER","ADMIN")
				.antMatchers("/h2-console/**")
				.permitAll().anyRequest().authenticated().and().httpBasic().and().logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login").permitAll()
				.and().exceptionHandling().accessDeniedPage("/403").and().cors().and().csrf().disable();
		http.headers().frameOptions().disable();
	}
	
	@Bean
    public InternalResourceViewResolver viewResolver()
    {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

}
