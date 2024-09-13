package com.ohdaesan.chatpracticekyu.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ohdaesan.chatpracticekyu.dto.ChatDTO;
import com.ohdaesan.chatpracticekyu.dto.ChatRoomDTO;
import com.ohdaesan.chatpracticekyu.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;


@Slf4j
@Component
@RequiredArgsConstructor
public class WebSocketChatHandler extends TextWebSocketHandler {

    private final ObjectMapper objectMapper;

    private final ChatService chatService;

    //
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // 채팅방으로 보낸 메세지를꺼냄
        String payload = message.getPayload();
        log.info("payload {}", payload);
//
        // 첫화면??
        TextMessage textMessage = new TextMessage("채팅 시작합니다 삐요삐요");

        // 채팅방에 들어온사람들한테 메세지가 뿌려짐
        session.sendMessage(textMessage);



        ChatDTO chatDTO = objectMapper.readValue(payload, ChatDTO.class);
        ChatRoomDTO roomDTO = chatService.findRoomById(chatDTO.getRoomId());
        roomDTO.handleActions(session, chatDTO, chatService);
    }


}
