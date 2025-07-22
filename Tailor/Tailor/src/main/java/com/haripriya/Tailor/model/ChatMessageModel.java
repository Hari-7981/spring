package com.haripriya.Tailor.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Entity
@Table(name = "chat_message")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChatMessageModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String senderId;

    @Column(name = "recipient_id")
    private String receiverId;

    @Column
    private String content;

    @Column
    private LocalDateTime timestamp;


}
