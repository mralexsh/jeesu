package ru.tusur.udo.sensors.wildflybeans;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * Session Bean implementation class FrontendSocket
 */
@ServerEndpoint("/sensors")
@Stateless
@LocalBean
public class FrontendSocket {

    /**
     * Default constructor. 
     */
    public FrontendSocket() {
        // TODO Auto-generated constructor stub
    }
    
    @OnMessage
    public String sayHello(String name) {
        System.out.println("Say hello to '" + name + "'");
        return ("Hello" + name);    
    }

    @OnOpen
    public void helloOnOpen(Session session) {
        System.out.println("WebSocket opened: " + session.getId());
    }
    
    @OnClose
    public void helloOnClose(CloseReason reason) {
        System.out.println("Closing a WebSocket due to " + reason.getReasonPhrase());
    }
}
