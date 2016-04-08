package com.joe.jauth.controller;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.authentication.BearerTokenExtractor;
import org.springframework.security.oauth2.provider.authentication.TokenExtractor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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
//		try {
//			Thread.sleep(7200000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return "JAuth - hello ";
	}

	@RequestMapping(value = "/redirect", method = { RequestMethod.GET, RequestMethod.POST })
	public void forward(HttpServletResponse httpServletResponse) {
		try {
			httpServletResponse.sendRedirect("http://www.microsoft.com");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/forward", method = { RequestMethod.GET, RequestMethod.POST })
	public void forward(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.getRequestDispatcher("").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/mirror", method = { RequestMethod.GET, RequestMethod.POST })
	public String mirror(@RequestBody(required = false) String body, HttpMethod method, HttpServletRequest request, HttpServletResponse response) throws URISyntaxException {
		URI uri = new URI ("https://amgen.compliancedesktop.com/CD-API/dd-cases");
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> responseEntity = restTemplate.exchange(uri, method, new HttpEntity<String>(""), String.class);
		return responseEntity.getBody();
//		return "sdfg";
	}

//	@RequestMapping("/**")
//	public String mirrorRest(@RequestBody String body, HttpMethod method, HttpServletRequest request, HttpServletResponse response) throws URISyntaxException {
//		URI uri = new URI("http", null, "localhost", 8080, request.getRequestURI(), request.getQueryString(), null);
//		RestTemplate restTemplate = new RestTemplate();
//		ResponseEntity<String> responseEntity = restTemplate.exchange(uri, method, new HttpEntity<String>(body), String.class);
//		return responseEntity.getBody();
//	}

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