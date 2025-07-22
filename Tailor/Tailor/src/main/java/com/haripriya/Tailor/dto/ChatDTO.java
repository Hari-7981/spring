package com.haripriya.Tailor.dto;

import java.time.LocalDateTime;




public interface ChatDTO {
        Long getPartnerId();
        LocalDateTime getTime();
        String getLastMessage();
    }


