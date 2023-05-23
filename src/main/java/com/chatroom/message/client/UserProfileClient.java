package com.chatroom.message.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.chatroom.message.model.UserProfile;

@FeignClient(name="user-profile-client", url="${user-profile-uri}/1.0/user-profile")
public interface UserProfileClient {
    
    @GetMapping("/{token}")
    UserProfile getUserProfile(@PathVariable("token") String tooken);

}
