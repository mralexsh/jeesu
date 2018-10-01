package ru.tusur.udo.wildfly.ejbs;

import java.io.IOException;
import java.util.Scanner;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SensorEndpointController  extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Inject
	SensorMonitoringService monitoringService;
	

	public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

		this.monitoringService
			.getSensorEndpointProducer()
			.sendBody("direct:sensorendpoint", extractPostRequestBody(request));
		
		response.setStatus(HttpServletResponse.SC_OK);
    }
	
	static String extractPostRequestBody(HttpServletRequest request) {
	    if ("POST".equalsIgnoreCase(request.getMethod())) {
	        Scanner s = null;
	        try {
	            s = new Scanner(request.getInputStream(), "UTF-8").useDelimiter("\\A");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return s.hasNext() ? s.next() : "";
	    }
	    return "";
	}
}
