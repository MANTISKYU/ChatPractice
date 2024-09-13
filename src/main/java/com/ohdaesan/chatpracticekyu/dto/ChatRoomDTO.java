package com.ohdaesan.chatpracticekyu.dto;

import com.ohdaesan.chatpracticekyu.service.ChatService;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
public class ChatRoomDTO {

    private String roomId; // 채팅방 아이디
    private String roomName; // 채팅방 이름
    private Set<WebSocketSession> sessions = new HashSet<>();

    @Builder
    public ChatRoomDTO(String roomId, String roomName) {
        this.roomId = roomId;
        this.roomName = roomName;


    }

    public void handleActions(WebSocketSession session, ChatDTO chatDTO, ChatService chatService){
        if (chatDTO.getType().equals(ChatDTO.MessageType.ENTER)) {
            sessions.add(session);
            chatDTO.setMessage((chatDTO.getSender() + "님이 입장했습니다"));
        }
        sendMessage(chatDTO, chatService);
    }

    public  <T> void sendMessage(T message, ChatService chatService) {
        sessions.parallelStream().forEach(session -> chatService.sendMessage(session, message));
    }


//    private HashMap<String, String> userlist = new HashMap<String, String>();

//    public ChatRoomDTO create(String roomName){
//        ChatRoomDTO chatRoom = new ChatRoomDTO();
//        chatRoom.roomId = UUID.randomUUID().toString();
//        chatRoom.roomName = roomName;
//
//        return chatRoom;
//    }

//    private Set<WebSocketSession> sessions = new HashSet<>();
//
//    @Builder
//    public ChatRoom(String roomId, String name){
//        this.roomId = roomId;
//        this.name = name;
//    }
//
//    public void handleAction(WebSocketSession session, ChatDTO message, ChatService service) {
//        // message 에 담긴 타입을 확인한다.
//        // 이때 message 에서 getType 으로 가져온 내용이
//        // ChatDTO 의 열거형인 MessageType 안에 있는 ENTER 과 동일한 값이라면
//        if (message.getType().equals(ChatDTO.MessageType.ENTER)) {
//            // sessions 에 넘어온 session 을 담고,
//            sessions.add(session);
//
//            // message 에는 입장하였다는 메시지를 띄운다
//            message.setMessage(message.getSender() + " 님이 입장하셨습니다");
//            sendMessage(message, service);
//        } else if (message.getType().equals(ChatDTO.MessageType.TALK)) {
//            message.setMessage(message.getMessage());
//            sendMessage(message, service);
//        }
//    }
//
//    public <T> void sendMessage(T message, ChatService service) {
//        sessions.parallelStream().forEach(session -> service.sendMessage(session, message));
//    }

}
