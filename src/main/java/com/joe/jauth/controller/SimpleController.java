package com.joe.jauth.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.authentication.BearerTokenExtractor;
import org.springframework.security.oauth2.provider.authentication.TokenExtractor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.joe.jauth.service.SimpleService;
import com.joe.jauth.service.TokenStoreService;

@RestController
public class SimpleController {

	@Autowired
	SimpleService simpleService;

	@Autowired
	TokenStoreService tokenStoreService;

	@RequestMapping(value = "/hello", method = { RequestMethod.GET, RequestMethod.POST })
	public String hello() {
		return "JAuth - hello ";
	}

	@RequestMapping(value = "/users", method = { RequestMethod.GET, RequestMethod.POST })
	public String users() {
		return simpleService.getUsers();
	}

	@RequestMapping(value = "/admin", method = { RequestMethod.GET, RequestMethod.POST })
	public String admin() {
		return "Admin only api";
	}

	@RequestMapping(value = "/removeAccessToken", method = { RequestMethod.GET, RequestMethod.POST })
	public String removeAccessToken(HttpServletRequest request, HttpServletResponse response) {

		TokenExtractor tokenExtractor = new BearerTokenExtractor();
		Authentication authentication = tokenExtractor.extract(request);

		return tokenStoreService.removeAccessToken(authentication);
	}
	
	@RequestMapping(value = "/removeAccessTokenByClientId", method = { RequestMethod.GET, RequestMethod.POST })
	public String removeAccessTokenByClientId(@RequestParam String clientId) {
		tokenStoreService.removeAccessToken(clientId);
		return clientId + "token removed";
	}
	
}