package com.haripriya.Tailor.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage {
    private String senderId;
    private String receiverId;
    private String content;

    // Constructors, getters, setters
}
