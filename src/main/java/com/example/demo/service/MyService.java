package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class MyService {

	
	@PreAuthorize("hasRole('admin')")
	public String findString() {
		return "string";
	}
	
	@PostAuthorize("returnObject == authentication.name")
	public String findStringPostAuth() {
	
		return "admin1";
	}
	
	
	@PostFilter("filterObject.equals( 'hello')")
	public List<String> findStringPostFilter() {
		List<String> result = new ArrayList<>();
		result.add("test");
		result.add("hello");
		return result;
	}
}
