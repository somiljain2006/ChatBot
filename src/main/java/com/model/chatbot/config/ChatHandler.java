package com.model.chatbot.config;

import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class ChatHandler extends TextWebSocketHandler {

  private static final Set<WebSocketSession> sessions = new HashSet<>();

  @Override
  public void afterConnectionEstablished(@NonNull WebSocketSession session) {
    if (sessions.size() >= 2) {
      try {
        session.close();
      } catch (IOException e) {
        System.out.println("Failed to close session");
      }
      return;
    }
    sessions.add(session);
  }

  @Override
  protected void handleTextMessage(@NonNull WebSocketSession session, @NonNull TextMessage message) throws Exception {
    for (WebSocketSession webSocketSession : sessions) {
      if (webSocketSession.isOpen() && !webSocketSession.getId().equals(session.getId())) {
        webSocketSession.sendMessage(message);
      }
    }
  }

  @Override
  public void afterConnectionClosed(@NonNull WebSocketSession session, @NonNull CloseStatus status) {
    sessions.remove(session);
  }
}
