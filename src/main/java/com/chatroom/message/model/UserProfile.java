package com.chatroom.message.model;

import lombok.*;
import org.springframework.security.core.AuthenticatedPrincipal;

@Value
@Builder
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class UserProfile implements AuthenticatedPrincipal {

    String userId;
    String userName;
    String firstName;
    String lastName;
    String email;

    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public String getName() {
        return userName;
    }
    
}
