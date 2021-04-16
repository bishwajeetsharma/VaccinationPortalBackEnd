package com.example.demo.service;

import java.io.IOException;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Exception.ResourceExistsException;
import com.example.demo.dao.AuthDao;
import com.example.demo.dao.DoctorDao;
import com.example.demo.dao.FileDBRepository;
import com.example.demo.dao.VaccineBookingDao;
import com.example.demo.model.Auth;
import com.example.demo.model.Doctor;
import com.example.demo.model.FileDB;
import com.example.demo.model.VaccineBooking;

@Service
@Transactional
public class FileStorageService {

	@Autowired
	private FileDBRepository fileDBRepository;
	@Autowired
	private DoctorDao docterDao;
	@Autowired
	private AuthDao authDao;
	@Autowired
	private VaccineBookingDao vaccineBookingDao;

	public FileDB store(MultipartFile file) throws IOException {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		FileDB FileDB = new FileDB(fileName, file.getContentType(), file.getBytes());

		return fileDBRepository.save(FileDB);
	}

	public FileDB getFile(String id) {
		return fileDBRepository.findById(id).get();
	}

	public Stream<FileDB> getAllFiles() {
		return fileDBRepository.findAll().stream();
	}

	public Stream<VaccineBooking> fetchPendingApprovals(String userName) {
		// TODO Auto-generated method stub
		try {
			Auth auth = authDao.findByUsername(userName).get();
			Doctor doctor = docterDao.findByAuth(auth);
			return vaccineBookingDao.findByDoctor(doctor).stream();
		} catch (Exception ex) {
			throw new ResourceExistsException(ex.getMessage());
		}
	}
}
