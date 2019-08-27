package br.com.portalCrc.web.controller.chamado;

import java.io.IOException;
import java.time.LocalTime;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value="/message")
public class MensageEndpoint {
	
	private static Queue<Session> sessions =  new ConcurrentLinkedQueue<>();
	
	@OnOpen
	public void connected(Session session, EndpointConfig conf) {
		System.out.println("connected()");
		sessions.add(session);
	}
	
	
	@OnClose
	public void disconnected(Session session, CloseReason reason) {
		System.out.println("disconnected()");
		sessions.remove(session);
	}
	
	@OnError
	public void error(Session session, EndpointConfig t) {
		System.out.println("error()" + t);
	}
	
	@OnMessage
	public void messageReceived(Session session, String message) throws IOException {
		System.out.println("messageReceived()" + message);
		if(message.startsWith("!C!")) {
			String name = message.substring(3);
			session.getUserProperties().put("name", name);
			broadcast(name + "entrou no chat!");
			
		}else {
			LocalTime now = LocalTime.now();
			String name = (String) session.getUserProperties().get("name");
			String formattedMessage = String.format("[%tH:%tM:%tS] %s -> %s", now, now, now, name, message);
			broadcast(formattedMessage);
		}
	}
	
	private void broadcast(String message) throws IOException {
		for(Session session : sessions) {
			
			session.getBasicRemote().sendText(message);
		}
	}

}
