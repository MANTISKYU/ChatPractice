package com.ohdaesan.chatpracticekyu.service;


import com.ohdaesan.chatpracticekyu.dto.ChatRoomDTO;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ChatService {

    private List<ChatRoomDTO> chatRooms = new ArrayList<>();

    public ChatRoomDTO createRoom(String name) {
        ChatRoomDTO room = ChatRoomDTO.create(name);
        chatRooms.add(room);
        return room;
    }

    public List<ChatRoomDTO> findAllRooms() {
        return chatRooms;
    }
}
