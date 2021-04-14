package com.example.demo.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.dao.MessageDao;
import com.example.demo.model.ChatMessage;
import com.example.demo.model.MessageRequest;

@Service
public class ChatService {

	@Autowired
	private MessageDao messagedao;

	public void saveMessage(ChatMessage message) {
		messagedao.save(message);
	}

	public List<ChatMessage> fetchAllMessages(MessageRequest messageRequest) {
		List<ChatMessage> messages = new ArrayList<ChatMessage>();
		messages = (List<ChatMessage>) messagedao.findByFromIdAndToId(messageRequest.getFrom(),messageRequest.getTo());
		messages.addAll((List<ChatMessage>) messagedao.findByFromIdAndToId(messageRequest.getTo(),messageRequest.getFrom()));
		if (messages.isEmpty()) {
				messages.add(new ChatMessage(messageRequest.getFrom(),messageRequest.getTo(), "No previous chats found", new Date().getTime()));
		}
		else
		{
			Collections.sort(messages, (m1,m2)->m1.getId()-m2.getId());
		}
		return messages;
	}

}
