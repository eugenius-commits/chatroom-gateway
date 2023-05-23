package com.chatroom.message.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Cacheable("messages") 
    public String getMessage(String messageId) {
        // Logic to fetch message from the database or other sources
        return "Hello, message " + messageId;
    }
}

