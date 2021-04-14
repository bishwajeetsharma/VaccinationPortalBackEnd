/**
 * 
 */
package com.example.demo.service;

import java.util.Optional;
import java.util.Random;

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
			logger.info("Proceeding for booking of vaccine by the user :: [{}]", persistedUser.get().getId());
			Random random = new Random();
			long drand = (long) (random.nextDouble() * 10000000000L);
			VaccineBooking vaccineBooking = new VaccineBooking(file.getBytes(), "PENDING",
					persistedUser.get().getFirstname() + "_" + persistedUser.get().getLastname() + "_" + drand);
			vaccineBooking.setUser(persistedUser.get());
			vaccineBooking.setDoctor(persistedDoc);
			vaccineBooking.setVaccine(persistedVaccine);
			VaccineBooking persistedVaccineDetails = vaccineBookingDao.save(vaccineBooking);
			logger.info("Successfully saved Vaccine Booking details with booking id :: [{}]",
					persistedVaccineDetails.getBookingId());
		} catch (Exception ex) {
			logger.error("Exception occured while booking vaccing :: [{}]", ex.getMessage());
			throw new ResourceExistsException(ex.getMessage());
		}
	}

}
