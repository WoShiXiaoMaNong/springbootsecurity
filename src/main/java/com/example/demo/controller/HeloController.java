package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeloController {
	

	@RequestMapping("/hello")
	public String hello() {
		return "hello";
	}
	
}
