package com.example.demo.auth;

public class ResponseObj {
	private String message;
	private int code;
	private boolean isOk;
	
	
	public ResponseObj(String message, int code) {
		super();
		this.message = message;
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public void setIsOk(boolean b) {
		this.isOk = b;
		
	}
	public boolean getIsOk() {
		return this.isOk;
	}
	
}
