package ru.tusur.udo.wildfly.ejbs;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.TimerService;
import javax.inject.Named;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.camel.Body;
import org.apache.camel.Consume;
import org.apache.camel.Handler;

@Stateless
@ServerEndpoint("/sensors")
public class SensorsWebsocketEndpoint {
	private static final Logger log = Logger.getLogger(SensorsWebsocketEndpoint.class.toString());
	
	private Session session;
	
	
	
	@PostConstruct
	public void init() {
	}
	
	
	@OnMessage
	public void onMessage(String msg) {
		log.info("----------MESSAGE: " + msg + "--------------------");
		session.getAsyncRemote().sendText("AWESOME:" + msg);
	}
	
	@OnOpen
	public void connect(Session session) {
		log.info("----------CONNECTING TO WEBSOCKET--------------------");
		this.session = session;
	}

	@OnClose
	public void close() {
		log.info("----------CLOSING WEBSOCKET--------------------");
		this.session = null;
	}
	
	public void handleMessage( String jsonSensors) {
		if (this.session != null) {
			log.info("------------------------------" + jsonSensors + "++++++++++++++++++++++++");	
			this.session.getAsyncRemote().sendText(jsonSensors);	
		} else {
			log.info("FUCKING NULL !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		}
	}
	
	@Schedule(hour="*",minute="*",second="0/1")
	public void handleWebsocket() {
//		this.handleMessage("ddddddddddd");
	}
	
}
