package com.joe.jauth.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joe.jauth.dao.SimpleDAO;

@Service
public class SimpleService {

	@Autowired
	SimpleDAO simpleDAO;

	public String getUsers() {
		List<Map<String, Object>> results = simpleDAO.getUsers();
		String s = "";
		for(Map<String, Object> result : results){
			s += result.get("username") + " | " + result.get("password");
		}
		
		return s;
	}
}
