package com.ohdaesan.chatpracticekyu.server;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class WebSocketChatHandler extends TextWebSocketHandler {

    private static final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // WebSocket 연결이 완료되면 세션을 저장
        String token = extractTokenFromSession(session);
        Claims claims = Jwts.parser()
                .setSigningKey("YourSecretKey")
                .parseClaimsJws(token)
                .getBody();
        String username = claims.getSubject();

        sessions.put(username, session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // 메시지를 받으면 발신자 정보를 포함해 처리
        String payload = message.getPayload();
        String token = extractTokenFromSession(session);
        Claims claims = Jwts.parser()
                .setSigningKey("YourSecretKey")
                .parseClaimsJws(token)
                .getBody();

        String username = claims.getSubject(); // 발신자 확인

        // 모든 세션에 메시지 전달
        for (WebSocketSession sess : sessions.values()) {
            sess.sendMessage(new TextMessage(username + ": " + payload));
        }
    }

    private String extractTokenFromSession(WebSocketSession session) {
        // WebSocketSession에서 JWT 토큰을 추출하는 메서드 (헤더나 URL에서)
        return session.getUri().getQuery().split("token=")[1];
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // 연결 종료 시 세션 제거
        sessions.values().remove(session);
    }
}
