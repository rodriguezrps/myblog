package com.myblog.config;

import javax.servlet.Filter;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.multipart.support.MultipartFilter;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

public class ApplicationInitializer extends AbstractDispatcherServletInitializer {
    
	@Override
    protected WebApplicationContext createRootApplicationContext() {
        AnnotationConfigWebApplicationContext rootAppContext = new AnnotationConfigWebApplicationContext();
        //set active profile for cloud foundry
        
        rootAppContext.register( MongoConfig.class, SecurityConfig.class );
        return rootAppContext;
    }
	
	

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected Filter[] getServletFilters() {
        return new Filter[]{
                new DelegatingFilterProxy("springSecurityFilterChain"), 
                new MultipartFilter()
                };
    }

    @Override
    protected WebApplicationContext createServletApplicationContext() {
        AnnotationConfigWebApplicationContext servletAppContext =
                new AnnotationConfigWebApplicationContext();
        servletAppContext.register( WebConfig.class );
        return servletAppContext;
    }
}