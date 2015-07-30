package com.joe.jauth.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Service;

@Service
public class TokenStoreService {

	@Autowired
	TokenStore tokenStore;

	public void removeAccessToken(String clientId) {
		Collection<OAuth2AccessToken> tokens = tokenStore.findTokensByClientId(clientId);
		for (OAuth2AccessToken token : tokens) {
			tokenStore.removeAccessToken(token);
		}
	}
	
	public String removeAccessToken(Authentication authentication) { 
		System.out.println("name: " + authentication.getName());
		System.out.println("detail: " + authentication.getDetails());
		return "lol";
	}
}
