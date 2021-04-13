package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.demo.model.ChatMessage;
import com.example.demo.service.ChatService;

import io.swagger.annotations.ApiOperation;

@Controller
public class ChatController {

	@Autowired
	private ChatService chatservice;
	
	@Autowired
	private SimpMessagingTemplate simpmessagingtemplate;
	
	@MessageMapping("/message")
	@CrossOrigin
	public void sendMessage(Message<ChatMessage> message) {
		ChatMessage msg=message.getPayload();
		System.out.println("message received");
		chatservice.saveMessage(msg);
		simpmessagingtemplate.convertAndSend("/broker/"+msg.getToId().toString(), msg);
	}
	
}
