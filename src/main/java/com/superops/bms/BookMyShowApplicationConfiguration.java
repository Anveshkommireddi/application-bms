package com.superops.bms;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.superops.bms.interceptor.AuthorizationInterceptor;

@Configuration
public class BookMyShowApplicationConfiguration implements WebMvcConfigurer{
	
	private AuthorizationInterceptor authorizationInterceptor;
	
	BookMyShowApplicationConfiguration(AuthorizationInterceptor authorizationInterceptor){
		this.authorizationInterceptor = authorizationInterceptor;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(authorizationInterceptor);
	}

}
