package ru.tusur.udo.wildfly.ejbs;


import java.io.IOException;
import java.util.Scanner;

import javax.inject.Inject;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ApiController extends HttpServlet {

	private static final long serialVersionUID = -2602517675102655241L;

	@Inject
	SensorsMonitoringService monitoringService;
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		
		this.monitoringService
			.getSensorsStartTemplate()
			.sendBody("seda:sensorsStartPoint", this.extractPostBody(req));
		
		resp.setStatus(HttpServletResponse.SC_OK);
	}
	
	private String extractPostBody(HttpServletRequest req) {
	
		if ("POST".equalsIgnoreCase(req.getMethod())) {
			Scanner s = null;
			try {
				s = new Scanner(req.getInputStream(), "UTF-8").useDelimiter("\\A");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return s.hasNext() ? s.next() : "";
		}
		
		return "";
	}
	
	
	
}
