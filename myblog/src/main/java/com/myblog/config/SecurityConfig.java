package com.myblog.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.myblog.filter.AuthenticationTokenProcessingFilter;
import com.myblog.filter.CORSFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	@Bean
	public CORSFilter cORSFilter() throws Exception {
		return new CORSFilter();
	}
	
	@Bean
	public AuthenticationTokenProcessingFilter authenticationTokenProcessingFilter() throws Exception {
		return new AuthenticationTokenProcessingFilter();
	}
	
	@Override
    public void configure(WebSecurity webSecurity) throws Exception {
        /*webSecurity
            .ignoring()
            	.antMatchers(HttpMethod.GET, "/rest/");*/
    }
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        	.addFilter(authenticationTokenProcessingFilter())
        	.addFilterBefore(cORSFilter(), AuthenticationTokenProcessingFilter.class)
            .authorizeRequests()
                //.antMatchers("/rest/**").permitAll()
                .anyRequest().authenticated()
                .and()
            //.requiresChannel() // UTILIZAR PARA O PROTOCOLO HTTPS
            	//.anyRequest()
            	//.requiresSecure()
            	//.and()
            .csrf()
            	.disable()
            .sessionManagement()
            	.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            	.and()
            .exceptionHandling()
            	.and();
            
    }
	
    
	@Bean(name="myAuthenticationManager")
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
	
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("teste").password("teste").authorities(new SimpleGrantedAuthority("teste"));
    }
	
}