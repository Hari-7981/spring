package com.haripriya.Tailor.controller;

import com.haripriya.Tailor.Repository.ChatMessage.ChatMessageRepository;
import com.haripriya.Tailor.dto.ChatDTO;
import com.haripriya.Tailor.dto.ChatMessage;
import com.haripriya.Tailor.model.ChatMessageModel;
import com.haripriya.Tailor.service.ChatMessageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth") // Base path for all tailor-related endpoints in this service
@CrossOrigin(origins = "${cors.allowed-origins}", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS}, allowedHeaders = "*", allowCredentials = "true", maxAge = 3600)

public class ChatRestController {

    private final ChatMessageService messageService;


    public ChatRestController(ChatMessageService messageService) {
        this.messageService = messageService;

    }

//    @GetMapping("/history")
//    public List<ChatMessageModel> getHistory(@RequestParam String receiverId) {
//        return messageService.getChatHistory( receiverId);
//    }

    @GetMapping("/threads/{tailorId}")
    public List<ChatDTO> threads(@PathVariable Long tailorId) {
            return  messageService.getThreadsForTailor(tailorId);
    }


    @GetMapping("/history")
    public List<ChatMessageModel> getHistory(
            @RequestParam Long senderId,
            @RequestParam Long recipientId) {
        return messageService
                .findBySenderIdAndReceiverIdOrSenderIdAndReceiverIdOrderByTimestamp(
                        senderId, recipientId, recipientId, senderId);
    }



}



