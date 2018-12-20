package ru.tusur.udo.wildfly.ejbs;

import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@Stateless
@ServerEndpoint("/sensors")
public class SensorsWebsocketController {

	private Session session; 
	
	@Inject
	SensorsMonitoringService monitoringService;
	
	@OnOpen
	public void connect(Session session) {
		this.session = session;
	}
	
	
	@OnClose
	public void close() {
		this.session = null;
	}
	
	private void handleMessage(String jsonSensors) {
		if (this.session != null) {
			this.session.getAsyncRemote().sendText(jsonSensors);
		}
	}
	
	
	@Schedule(hour="*", minute="*", second="0/1")
	public void handleWebsocket() {
		this.handleMessage(this.monitoringService.getSensorsSnapshot());
	}
	
	
}