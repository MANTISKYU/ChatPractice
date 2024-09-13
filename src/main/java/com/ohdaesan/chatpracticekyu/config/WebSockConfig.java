package com.ohdaesan.chatpracticekyu.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocket
@RequiredArgsConstructor
public class WebSockConfig implements WebSocketConfigurer {

     // WebSocketHandler 에 관한 생성자 추가
    private final WebSocketHandler webSocketHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketHandler, "/ws/chat").setAllowedOrigins("*");

    }

//    @Override
//    public void configureMessageBroker(MessageBrokerRegistry registry) {
//        // 메시지를 구독하는 요청 url => 즉 메시지 받을 때
//        registry.enableSimpleBroker("/sub");
//
//        // 메시지를 발행하는 요청 url => 즉 메시지 보낼 때
//        registry.setApplicationDestinationPrefixes("/pub");
//    }



}
