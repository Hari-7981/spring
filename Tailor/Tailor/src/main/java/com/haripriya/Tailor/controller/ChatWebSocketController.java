package com.haripriya.Tailor.controller;

import com.haripriya.Tailor.dto.ChatMessage;
import com.haripriya.Tailor.model.ChatMessageModel;
import com.haripriya.Tailor.service.ChatMessageService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth") // Base path for all tailor-related endpoints in this service
@CrossOrigin(origins = "${cors.allowed-origins}", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS}, allowedHeaders = "*", allowCredentials = "true", maxAge = 3600)

public class ChatWebSocketController {

    private final ChatMessageService messageService;

    public ChatWebSocketController(ChatMessageService messageService) {
        this.messageService = messageService;
    }

    @MessageMapping("/chat.send")
    @SendTo("/topic/messages")
    public ChatMessageModel sendMessage(ChatMessageModel message) {
        return messageService.save(message); // Store in DB
    }
}