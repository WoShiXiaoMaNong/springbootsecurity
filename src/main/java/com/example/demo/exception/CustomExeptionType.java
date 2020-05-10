package com.example.demo.exception;


public enum CustomExeptionType {
	USER_INPUT_ERROR(10001,"Input Error");
	
	
	/** 错误码 */   
	private final int code; 
	/** 描述 */    
	
	private final String description;    
	
	private CustomExeptionType(final int code, final String description) {  
		this.code = code;        
		this.description = description;   
	}
	
	
	public int getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}
	
	
}
