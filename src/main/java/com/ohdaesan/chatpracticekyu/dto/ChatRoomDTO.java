package com.ohdaesan.chatpracticekyu.dto;

import java.util.UUID;

public class ChatRoomDTO {
    private String roomId;
    private String name;

    public ChatRoomDTO() {}

    public ChatRoomDTO(String name) {
        this.roomId = UUID.randomUUID().toString();
        this.name = name;
    }

    public static ChatRoomDTO create(String name) {
        return new ChatRoomDTO(name);
    }

    // getters and setters
}