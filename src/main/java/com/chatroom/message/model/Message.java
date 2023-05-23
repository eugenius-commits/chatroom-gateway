package com.chatroom.message.model;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@NoArgsConstructor(force= true, access = AccessLevel.PRIVATE)
public class Message {
    String messageId;
    String messageType;
    String messageText;

}
