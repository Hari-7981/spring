package com.haripriya.Tailor.Repository.ChatMessage;



import com.haripriya.Tailor.dto.ChatDTO;
import com.haripriya.Tailor.model.ChatMessageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessageModel, Long> {


    List<ChatMessageModel> findBySenderIdAndReceiverId(String senderId, String receiverId);


    @Query(value = """
    SELECT t.partner_id  AS partnerId,
           m.timestamp   AS time,
           m.content     AS lastMessage
    FROM (
        SELECT CASE
                 WHEN sender_id = :tailorId THEN recipient_id
                 ELSE sender_id
               END       AS partner_id,
               MAX(id)   AS last_msg_id
        FROM chat_message
        WHERE sender_id   = :tailorId
           OR recipient_id = :tailorId
        GROUP BY partner_id
    ) t
    JOIN chat_message m ON m.id = t.last_msg_id
    ORDER BY m.timestamp DESC
    """, nativeQuery = true)
    List<ChatDTO> findThreadsForTailor(Long tailorId);

   // List<ChatMessageModel> findByReceiverId(String receiverId); // Optional

    List<ChatMessageModel>
    findBySenderIdAndReceiverIdOrSenderIdAndReceiverIdOrderByTimestampAsc(
            String senderId1, String receiverId1,
            String senderId2, String receiverId2);
}
