package com.chatroom.message.configuration;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@EnableFeignClients(basePackages = "com.eugenez.message")
@Configuration
public class FeignConfig {
    
}
