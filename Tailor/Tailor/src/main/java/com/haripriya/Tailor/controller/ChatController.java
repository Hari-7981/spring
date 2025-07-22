//package com.haripriya.Tailor.controller;
//
//import com.haripriya.Tailor.dto.ChatMessage;
//import com.haripriya.Tailor.model.ChatMessageModel;
//import com.haripriya.Tailor.service.ChatMessageService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.handler.annotation.SendTo;
//import org.springframework.stereotype.Controller;
//
//import java.security.Principal;
//
//@Controller
//public class ChatController {
//
//    @Autowired
//    ChatMessageService  chatMessageService;
//
//    @MessageMapping("/chat.send")
////    @SendTo("/topic/messages")
//    public ChatMessageModel handleMessage(ChatMessageModel message, Principal principal) {
//        String username = principal.getName(); // Comes from JWT in handshake
//        message.setSenderId(username);
//        return chatMessageService.save(message);
//    }
//
//}
//
