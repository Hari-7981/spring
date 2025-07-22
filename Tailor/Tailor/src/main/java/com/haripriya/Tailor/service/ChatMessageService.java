package com.haripriya.Tailor.service;



import com.haripriya.Tailor.Repository.ChatMessage.ChatMessageRepository;
import com.haripriya.Tailor.dto.ChatDTO;
import com.haripriya.Tailor.model.ChatMessageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatMessageService {


    @Autowired
    private  ChatMessageRepository repository;




    public ChatMessageModel save(ChatMessageModel message) {
        message.setTimestamp(java.time.LocalDateTime.now());
        return repository.save(message);
    }

//    public List<ChatMessageModel> getChatHistory(String receiverId) {
//        return repository.findThreadsForTailor(receiverId);
//    }

    public List<ChatDTO> getThreadsForTailor(Long tailorId) {
        return repository.findThreadsForTailor(tailorId);
    }


    // ChatMessageService.java
    public List<ChatMessageModel> findBySenderIdAndReceiverIdOrSenderIdAndReceiverIdOrderByTimestamp(
            Long senderId, Long recipientId,
            Long recipientId2, Long senderId2) {

        String sid = String.valueOf(senderId);
        String rid = String.valueOf(recipientId);

        return repository
                .findBySenderIdAndReceiverIdOrSenderIdAndReceiverIdOrderByTimestampAsc(
                        sid, rid,
                        rid, sid);
    }


}
