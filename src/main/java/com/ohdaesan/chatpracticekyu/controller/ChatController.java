package com.ohdaesan.chatpracticekyu.controller;

import com.ohdaesan.chatpracticekyu.dto.ChatRoomDTO;
import com.ohdaesan.chatpracticekyu.service.ChatService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping("/room")
    public ChatRoomDTO createRoom(@RequestParam String name) {
        return chatService.createRoom(name);
    }

    @GetMapping("/rooms")
    public List<ChatRoomDTO> getRooms() {
        return chatService.findAllRooms();
    }
}
