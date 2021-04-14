package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.ChatPartcipant;
import com.example.demo.service.ChatPartcipantService;

@RestController
@CrossOrigin
@RequestMapping("/fetch")
public class PartcipantController {

	@Autowired
	private ChatPartcipantService chatparticipantservice;

	@RequestMapping("/doctor")
	@CrossOrigin
	public List<ChatPartcipant> fetchUsers() {
		return (List<ChatPartcipant>) chatparticipantservice.fetchUsers();
	}

	@RequestMapping("/user")
	@CrossOrigin
	public List<ChatPartcipant> fetchDoctors() {
		return (List<ChatPartcipant>) chatparticipantservice.fetchDoctors();
	}
}
