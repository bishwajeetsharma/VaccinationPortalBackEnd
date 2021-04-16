/**
 * 
 */
package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Exception.ResourceExistsException;
import com.example.demo.dao.AuthDao;
import com.example.demo.dao.DoctorDao;
import com.example.demo.dao.HospitalDao;
import com.example.demo.dao.LocationDao;
import com.example.demo.dao.RolesDao;
import com.example.demo.dao.UserDao;
import com.example.demo.dao.VaccineBookingDao;
import com.example.demo.model.Auth;
import com.example.demo.model.Doctor;
import com.example.demo.model.DoctorRegistration;
import com.example.demo.model.DoctorResponse;
import com.example.demo.model.Hospital;
import com.example.demo.model.Location;
import com.example.demo.model.Roles;
import com.example.demo.model.User;
import com.example.demo.model.VaccineBooking;

/**
 * @author PRATAP
 *
 */
@Service
@Transactional
public class DoctorService {

	private static final Logger logger = LogManager.getLogger(DoctorService.class);
	@Autowired
	private DoctorDao docterDao;
	@Autowired
	private AuthDao authDao;
	@Autowired
	private LocationDao locationDao;
	@Autowired
	private RolesDao rolesDao;
	@Autowired
	private HospitalDao hospitalDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private VaccineBookingDao vaccineBookingDao;

	public int registerDoctor(DoctorRegistration doctorRegData) {
		// TODO Auto-generated method stub
		int status = 0;
		Doctor doc = doctorRegData.getDoctor();
		Auth auth = doctorRegData.getAuth();
		Location loc = doctorRegData.getLocation();
		List<Hospital> hospitals = doctorRegData.getDoctor().getHospital();

		if (docterDao.existsByRegNo(doc.getRegNo())) {
			logger.error("Doctor Registration Number already exists :: [{}]", doc.getRegNo());
			throw new ResourceExistsException("Doctor Registration Number already exists");
		}
		if (docterDao.existsByContactno(doc.getContactno())) {
			logger.error("Doctor Contact Number already exists :: [{}]", doc.getContactno());
			throw new ResourceExistsException("Doctor exists with same Contact Number already exists");
		}
		if (authDao.existsByUsername(auth.getUsername())) {
			logger.error("Doctor Username already exists :: [{}]", auth.getUsername());
			throw new ResourceExistsException("User name already exists");
		}

		Roles role = rolesDao.findByRolename("doctor");
		auth.setRole(role);
		auth.setEnable("true");
		authDao.save(auth);
		logger.info("Doctor Credentials Stored Successfully");
		Location locationPersisted = locationDao.findByCityAndState(loc.getCity(), loc.getState());
		if (locationPersisted == null) {
			locationDao.save(loc);
			doc.setLocation(loc);
			locationPersisted = loc;
		} else {
			doc.setLocation(locationPersisted);
		}
		logger.info("Doctor Location Info Stored Successfully");
		List<Hospital> persistedHospitals = new ArrayList<>();
		for (Hospital hospital : hospitals) {
			hospital.setLocation(locationPersisted);
			Hospital persistedHosp = hospitalDao.findByNameAndLocation(hospital.getName(), locationPersisted);
			if (persistedHosp == null) {
				Hospital persisted = hospitalDao.save(hospital);
				persistedHospitals.add(persisted);
			} else {
				persistedHospitals.add(persistedHosp);
			}
		}
		logger.info("Hospitals Stored Successfully");
		doc.setAuth(auth);
		doc.setHospital(persistedHospitals);
		try {
			docterDao.save(doc);
			status = 1;
			logger.info("Doctor Data Successfully persisted in system.");
			return status;
		} catch (Exception ex) {
			logger.error("Error occured while storing the Doctor Object :: [{}]", ex.getMessage());
			throw new ResourceExistsException(ex.getMessage());
		}
	}

	public List<DoctorResponse> getDoctorsByCity(String username) {
		// TODO Auto-generated method stub
		logger.info("In service for fetching the doctors by city");
		try {
			Auth auth = new Auth();
			auth.setUsername(username);
			Optional<Auth> authDetails = authDao.findByUsername(username);
			logger.info("Successfully fetched auth details for username [{}] having id :: [{}]", username,
					authDetails.get().getId());
			Optional<User> userDetails = userDao.findByAuth(authDetails);
			Location location = userDetails.get().getLocation();
			logger.info("User details found having location id :: [{}]", location.getId());
			logger.info("Proceeding to fetch all the doctors having locationId as :: [{}]", location.getId());

			logger.info("Successfully fetched all the doctors with location id :: [{}] and number of doctors is ::[{}]",
					location.getId(), location.getDoctor().size());
			List<DoctorResponse> doctorsList = new ArrayList<DoctorResponse>();
			for (Doctor d : location.getDoctor()) {
				DoctorResponse docResp = new DoctorResponse(d.getId(), d.getFirstname(), d.getLastname(),
						d.getContactno(), d.getRegNo());
				doctorsList.add(docResp);
			}
			return doctorsList;
		} catch (Exception ex) {
			logger.error("Error occured while fetching the doctors by city :: [{}]", ex.getMessage());
			throw new ResourceExistsException(ex.getMessage());
		}
	}

	public Stream<VaccineBooking> fetchPendingApprovals(String userName) {
		// TODO Auto-generated method stub
		logger.info("In service, trying to fetch the pendring approvals for the doctor with username :: [{}]",
				userName);
		try {
			Auth auth = authDao.findByUsername(userName).get();
			logger.info("Successfully fetched auth details for the doctor and id is :: [{}]", auth.getId());
			Doctor doctor = docterDao.findByAuth(auth);
			logger.info("Successfully fetched doctor details for the doctor and the doctor id is :: [{}]",
					doctor.getId());
			return  vaccineBookingDao.findByDoctor(doctor).stream();
		} catch (Exception ex) {
			logger.error("Error occured while fetching the pending approvals by the doctor :: [{}]", ex.getMessage());
			throw new ResourceExistsException(ex.getMessage());
		}
	}

}
