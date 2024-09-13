package com.ohdaesan.chatpracticekyu.controller;

import com.ohdaesan.chatpracticekyu.dto.ChatRoomDTO;
import com.ohdaesan.chatpracticekyu.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController {

    private final ChatService chatService;
//
    @PostMapping
    public ChatRoomDTO createRoom(@RequestParam String roomName){
        return chatService.createRoom(roomName);
    }

    @GetMapping
    public List<ChatRoomDTO> findAllRooms(){
        return chatService.findAllRoom();
    }


}
