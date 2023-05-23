package com.chatroom.message.configuration;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@EnableFeignClients(basePackages = "com.chatroom.message")
@Configuration
public class FeignConfig {
    
}
