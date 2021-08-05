/*
 * Copyright (c) 2021. Elex. All Rights Reserved.
 * https://www.elex-project.com/
 */

package kr.pe.elex.examples;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebSocketHandler extends TextWebSocketHandler {
	private static Set<WebSocketSession> sessions = new ConcurrentHashMap().newKeySet();

	public static void publish(String message) throws IOException {
		for (WebSocketSession item : sessions) {
			try {
				item.sendMessage(new TextMessage(message));
			} catch (Throwable e) {
				log.error("Couldn't send message over websocket.", e);
			}
		}
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		super.afterConnectionEstablished(session);
		sessions.add(session);
		log.info("Client({}) connected.", session.getRemoteAddress());
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		log.info("Message from client({}):{}", session.getRemoteAddress(), message.getPayload());
		for (WebSocketSession webSocketSession : sessions) {
			//if (session == webSocketSession) continue;
			String msg = message.getPayload().toUpperCase();
			webSocketSession.sendMessage(new TextMessage(msg));
		}
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		super.afterConnectionClosed(session, status);
		sessions.remove(session);
		log.info("Client({}) disconnect.", session.getRemoteAddress());
	}

}
