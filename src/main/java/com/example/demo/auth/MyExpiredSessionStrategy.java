package com.example.demo.auth;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;

import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class MyExpiredSessionStrategy implements SessionInformationExpiredStrategy{
	private ObjectMapper objmapper = new ObjectMapper();
	@Override
	public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
		Map<String, Object> msg = new HashMap<String, Object>();
		msg.put("Code", 123);
		msg.put("msg", "Multiple login!");
		//event.getResponse().setContentType("applicatoin/json;charset=UTF-8");
		event.getResponse().getWriter().write(objmapper.writeValueAsString(msg));
		
	}

}
