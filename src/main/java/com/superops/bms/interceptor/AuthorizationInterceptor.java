package com.superops.bms.interceptor;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.superops.bms.entity.APIPermsEntity;
import com.superops.bms.entity.UserEntity;
import com.superops.bms.exception.AuthorizationException;
import com.superops.bms.repository.APIPermsRepository;
import com.superops.bms.repository.UserRepository;

@Component
public class AuthorizationInterceptor implements HandlerInterceptor {

	private UserRepository userRepository;
	
	private APIPermsRepository apiPermsRepository;

	public AuthorizationInterceptor(UserRepository userRepository, APIPermsRepository apiPermsRepository) {
		this.userRepository = userRepository;
		this.apiPermsRepository = apiPermsRepository;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		String requestURI = request.getRequestURI();
		String authorizationHeader = request.getHeader("Authorization");
		
		if (requestURI.contains("/generateToken") || requestURI.contains("/error")) {
			return true;
		} else if (null == authorizationHeader && !requestURI.contains("/generateToken")) {
			throw new AuthorizationException("Authorization Header Missing in Request");
		} else {
			
			if(!authorizationHeader.startsWith("Bearer ")) {
				throw new AuthorizationException("Authorization Header Should Start with Bearer");
			}

			String token = authorizationHeader.split(" ")[1];

			// get the userDetails from the token
			UserEntity userEntity = userRepository.findByToken(token).orElseThrow(()->new AuthorizationException("Not a valid Token"));

			if (userEntity.getTokenUpdtTime().before(new Date())) {
				throw new AuthorizationException("Token Expired. Please generate a new token");
			}
			
			String uri = requestURI.lastIndexOf("/") > 0 ? requestURI.substring(0, requestURI.lastIndexOf("/")) : requestURI;
			String httpMethod = request.getMethod();
			
			APIPermsEntity apiPermsEnty = apiPermsRepository.findByUriAndAction(uri, httpMethod)
					.orElseThrow(() -> new AuthorizationException(
							"Roles Not Available for the requested uri :: " + uri + " and Method :: " + httpMethod));
			
			// check if user has the access to api
			if (!apiPermsEnty.getRoles().contains(userEntity.getRole())) {
				throw new AuthorizationException("Failure to Access the requested URI");
			}
			
		}
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

}
