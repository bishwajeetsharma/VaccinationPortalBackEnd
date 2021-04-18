/**
 * 
 */
package com.example.demo.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Exception.ResourceExistsException;
import com.example.demo.dao.AuthDao;
import com.example.demo.dao.DoctorDao;
import com.example.demo.dao.UserDao;
import com.example.demo.dao.VaccineBookingDao;
import com.example.demo.dao.VaccineDao;
import com.example.demo.model.Auth;
import com.example.demo.model.Doctor;
import com.example.demo.model.FileDB;
import com.example.demo.model.User;
import com.example.demo.model.Vaccine;
import com.example.demo.model.VaccineBooking;
import com.example.demo.model.VaccineRegistration;

/**
 * @author PRATAP
 *
 */
@Service
@Transactional
public class VaccineBookingService {

	private static final Logger logger = LogManager.getLogger(VaccineBookingService.class);

	@Autowired
	private VaccineBookingDao vaccineBookingDao;
	@Autowired
	private AuthDao authDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private VaccineDao vaccineDao;
	@Autowired
	private DoctorDao doctorDao;
	@Autowired
	private FileStorageService fileStorageService;

	public void vaccineBooking(VaccineRegistration vaccineReg, MultipartFile file) {
		// TODO Auto-generated method stub
		logger.info("In vaccine booking service, porceeding to book vaccine :: [{}] for username ::[{}]",
				vaccineReg.getVaccineName(), vaccineReg.getUsername());
		try {
			logger.info("Fetching Auth details first with the username :: [{}]", vaccineReg.getUsername());
			Optional<Auth> persistedAuth = authDao.findByUsername(vaccineReg.getUsername());
			logger.info("Successfully fetched auth details having user id :: [{}]", persistedAuth.get().getId());
			Optional<User> persistedUser = userDao.findByAuth(persistedAuth);
			logger.info("Successfully fetched user details having id :: [{}]", persistedUser.get().getId());
			Vaccine persistedVaccine = vaccineDao.findByName(vaccineReg.getVaccineName());
			logger.info("Successfully fetched vaccine details for vaccine name ::[{}] having vaccine id :: [{}]",
					persistedVaccine.getName(), persistedVaccine.getId());
			Doctor persistedDoc = doctorDao.findByRegNo(vaccineReg.getDocRegNo());
			logger.info("Successfully fetched doctor details having reg no :: [{}] with doctor id :: [{}]",
					persistedDoc.getRegNo(), persistedDoc.getId());
			logger.info(
					"Checking whether any booking related to user id :: [{}] and vaccine id :: [{}] exists",
					persistedUser.get().getId(), persistedVaccine.getId());
			Optional<VaccineBooking> status = vaccineBookingDao.checkStatus(persistedUser.get().getId().intValue(),
					persistedVaccine.getId().intValue());
			if (status.isPresent() && status.get().getStatus().equals("PENDING")) {
				logger.info("Already a booking is pending, cannot proceed for a new booking");
				throw new Exception("Already a booking from this user name for the provided vaccine is pending.");
			}
			logger.info("Proceeding for booking of vaccine by the user :: [{}]", persistedUser.get().getId());
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			String dateToString = formatter.format(date);
			VaccineBooking vaccineBooking = new VaccineBooking("PENDING", java.sql.Date.valueOf(dateToString));
			vaccineBooking.setUser(persistedUser.get());
			vaccineBooking.setDoctor(persistedDoc);
			vaccineBooking.setVaccine(persistedVaccine);
			FileDB persistedFile = fileStorageService.store(file);
			vaccineBooking.setFileDb(persistedFile);
			VaccineBooking persistedVaccineDetails = vaccineBookingDao.save(vaccineBooking);
			logger.info("Successfully saved Vaccine Booking details with booking id :: [{}]",
					persistedVaccineDetails.getBookingId());
		} catch (Exception ex) {
			logger.error("Exception occured while booking vaccing :: [{}]", ex.getMessage());
			throw new ResourceExistsException(ex.getMessage());
		}
	}

}
