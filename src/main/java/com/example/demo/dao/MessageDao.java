package com.example.demo.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.ChatMessage;

@Repository
public interface MessageDao extends CrudRepository<ChatMessage, Integer> {

	public Iterable<ChatMessage> findByFromIdAndToId(Integer fromId, Integer toId);
}
