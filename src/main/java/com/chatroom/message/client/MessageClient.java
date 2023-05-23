package com.chatroom.message.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="message-client", url = "${mesage-api-uri}/api/1.0/message")
public interface MessageClient {
    
    @GetMapping("/message")
    ResponseEntity<String> getMessage();
    
    @PutMapping("/message")
    ResponseEntity<Void> updateMessage(@RequestBody String message);
    
    @PatchMapping("/message")
    ResponseEntity<Void> patchMessage(@RequestBody String message);
    
    @DeleteMapping("/message")
    ResponseEntity<Void> deleteMessage();
    
    @PostMapping("/message")
    ResponseEntity<Void> createMessage(@RequestBody String message);
}
