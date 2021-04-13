package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.MessageDao;
import com.example.demo.model.ChatMessage;



@Service
public class ChatService {

	@Autowired
	private MessageDao messagedao;
	
	public void saveMessage(ChatMessage message) {
		messagedao.save(message);
	}
	
}
