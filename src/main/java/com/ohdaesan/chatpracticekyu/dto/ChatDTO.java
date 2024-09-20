package com.ohdaesan.chatpracticekyu.dto;

import lombok.*;

@Getter
@Setter
public class ChatDTO {
    private String roomId;
    private String sender; // 로그인된 사용자
    private String message;

    public ChatDTO() {}

    public ChatDTO(String roomId, String sender, String message) {
        this.roomId = roomId;
        this.sender = sender;
        this.message = message;
    }

    // getters and setters
}
