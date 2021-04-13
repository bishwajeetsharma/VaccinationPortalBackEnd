package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.DoctorDao;
import com.example.demo.dao.UserDao;
import com.example.demo.model.ChatPartcipant;
import com.example.demo.model.Doctor;
import com.example.demo.model.User;

@Service
public class ChatPartcipantService {

	@Autowired
	private UserDao userdao;
	@Autowired
	private DoctorDao doctordao;
	public Iterable<ChatPartcipant> fetchUsers()
	{
		List<User>listuser=(List<User>) userdao.findAll();
		List<ChatPartcipant>listchatparticipant=new ArrayList<ChatPartcipant>();
		for(User user:listuser) {
			listchatparticipant.add(new ChatPartcipant(user.getAuth().getId(), user.getFirstname()+" "+user.getLastname()));
		}
		return listchatparticipant;
	}
	
	public Iterable<ChatPartcipant> fetchDoctors()
	{
		List<Doctor>listdoctor=(List<Doctor>) doctordao.findAll();
		List<ChatPartcipant>listchatparticipant=new ArrayList<ChatPartcipant>();
		for(Doctor doctor:listdoctor) {
			listchatparticipant.add(new ChatPartcipant(doctor.getAuth().getId(), doctor.getFirstname()+" "+doctor.getLastname()));
		}
		return listchatparticipant;
	}
}
