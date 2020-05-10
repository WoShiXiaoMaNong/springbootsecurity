package com.example.demo.auth;

import com.example.demo.exception.CustomExeption;

public class AjaxResponse {
	private String message;
	private int code;
	private boolean isOk;
	private Exception e;
	private Object data;
	
	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public static AjaxResponse error(CustomExeption e) {
		AjaxResponse ajaxResponse = new AjaxResponse();
		ajaxResponse.setIsOk(false);
		ajaxResponse.setCode(e.getCode());
		ajaxResponse.setMessage(e.getMessage());
		
		return ajaxResponse;
	}
	
	public static AjaxResponse succeed(Object data) {
		AjaxResponse resultBean = new AjaxResponse();
	    resultBean.setIsOk(true);
	    resultBean.setCode(200);
	    resultBean.setMessage("success");
	    resultBean.setData(data);
	    return resultBean;
	}
	
	private AjaxResponse() {
		
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


	public Exception getE() {
		return e;
	}


	public void setE(Exception e) {
		this.e = e;
	}



	
}
