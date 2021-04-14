package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.model.ChatMessage;
import com.example.demo.model.MessageRequest;
import com.example.demo.service.ChatService;

@Controller
public class ChatController {

	@Autowired
	private ChatService chatservice;

	@Autowired
	private SimpMessagingTemplate simpmessagingtemplate;

	@MessageMapping("/message")
	@CrossOrigin
	public void sendMessage(Message<ChatMessage> message) {
		ChatMessage msg = message.getPayload();
		System.out.println("message received");
		chatservice.saveMessage(msg);
		simpmessagingtemplate.convertAndSend("/broker/" + msg.getToId().toString(), msg);
	}

	@PostMapping("/messagehistory")
	@CrossOrigin
	public @ResponseBody List<ChatMessage> fetchAllMessages(@RequestBody MessageRequest messageRequest) {
		System.out.println("fetching messages");
		return chatservice.fetchAllMessages(messageRequest);
	}

}
