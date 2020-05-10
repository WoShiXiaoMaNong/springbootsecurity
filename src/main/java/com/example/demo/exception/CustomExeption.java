package com.example.demo.exception;

public class CustomExeption extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int  code;
	private String message;
	
	
	public CustomExeption(CustomExeptionType  type, String message) {
		super();
		this.code = type.getCode();
		this.message = message;
	}
	
	
	public int getCode() {
		return this.code;
	}


	@Override
	public String getMessage() {
		return this.message;
	}
	
	
	
}
