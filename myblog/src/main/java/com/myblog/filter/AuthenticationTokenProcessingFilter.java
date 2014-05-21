package com.myblog.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationTokenProcessingFilter extends UsernamePasswordAuthenticationFilter {

    @Autowired
    @Override
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
    	//HttpServletRequest req = (HttpServletRequest) request;
    	
    	//HttpServletResponse res = (HttpServletResponse) response;
    	
                    UsernamePasswordAuthenticationToken authentication = 
                            new UsernamePasswordAuthenticationToken("teste", "teste");
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails((HttpServletRequest) request));
        try {
        	SecurityContextHolder.getContext().setAuthentication(this.getAuthenticationManager().authenticate(authentication));
        } catch (BadCredentialsException e) {
			System.out.println("bad credentials");
		} 
        // continue thru the filter chain
        chain.doFilter(request, response);
    }
}