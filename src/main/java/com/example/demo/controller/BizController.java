package com.example.demo.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.MyService;

@Controller
public class BizController {
	
	@Resource
	MyService mservice;
	
	//Please find in WebConfig.java
//	@PostMapping("/login")
//	public String index(String username, String password) {
//		return "index";
//	}
	
	@GetMapping("/index")
	public String index() {
		return "index";
	}
	@GetMapping("/syslog")
	public String syslog() {
		return "syslog";
	}
	@GetMapping("/sysuser")
	public String sysuser() {
		return "sysuser";
	}
	@GetMapping("/biz1")
	public String biz1(@RequestParam(required =  false) String test) {
		System.out.println(test);
		return "biz1";	
	}
	@GetMapping("/biz2")
	public String biz2() {
		return "biz2";
	}
	@GetMapping("/hello")
	public @ResponseBody ResponseEntity<Testj> json(HttpServletResponse res) {
		res.setHeader("Access-Control-Allow-Origin","*");
		return ResponseEntity.ok(new Testj("test name" + this.mservice.findStringPostFilter(),12));
	}
	
}
class Testj{
	private String nameString;
	private int age;
	
	
	public Testj(String nameString, int age) {
		super();
		this.nameString = nameString;
		this.age = age;
	}
	public String getNameString() {
		return nameString;
	}
	public void setNameString(String nameString) {
		this.nameString = nameString;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

	
}
