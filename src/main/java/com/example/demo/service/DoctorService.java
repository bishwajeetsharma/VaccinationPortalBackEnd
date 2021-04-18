/**
 * 
 */
package com.example.demo.service;

import java.sql.Date;
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
import com.example.demo.dao.AppointmentDao;
import com.example.demo.dao.AuthDao;
import com.example.demo.dao.DoctorDao;
import com.example.demo.dao.HospitalDao;
import com.example.demo.dao.LocationDao;
import com.example.demo.dao.RolesDao;
import com.example.demo.dao.UserDao;
import com.example.demo.dao.VaccineBookingDao;
import com.example.demo.dao.VaccineDao;
import com.example.demo.model.Appointment;
import com.example.demo.model.AppointmentRequest;
import com.example.demo.model.Auth;
import com.example.demo.model.Doctor;
import com.example.demo.model.DoctorRegistration;
import com.example.demo.model.DoctorResponse;
import com.example.demo.model.Dosage;
import com.example.demo.model.Hospital;
import com.example.demo.model.Location;
import com.example.demo.model.Roles;
import com.example.demo.model.User;
import com.example.demo.model.Vaccine;
import com.example.demo.model.VaccineBooking;
import com.example.demo.utils.Constants;

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
	@Autowired
	private VaccineDao vaccineDao;
	@Autowired
	private AppointmentDao appointmentDao;

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
		logger.info("In service, trying to fetch the pending approvals for the doctor with username :: [{}]", userName);
		try {
			Auth auth = authDao.findByUsername(userName).get();
			logger.info("Successfully fetched auth details for the doctor and id is :: [{}]", auth.getId());
			Doctor doctor = docterDao.findByAuth(auth);
			logger.info("Successfully fetched doctor details for the doctor and the doctor id is :: [{}]",
					doctor.getId());
			return vaccineBookingDao.findByDoctor(doctor).stream();
		} catch (Exception ex) {
			logger.error("Error occured while fetching the pending approvals by the doctor :: [{}]", ex.getMessage());
			throw new ResourceExistsException(ex.getMessage());
		}
	}

	public List<Hospital> checkAvailability(Auth auth, String vaccineName) {
		// TODO Auto-generated method stub
		logger.info("In Service to check availability of vaacing");
		try {
			auth = authDao.findByUsername(auth.getUsername()).get();
			logger.info("Doctor exists with doctor auth id :: [{}]", auth.getId());
			Doctor doctor = docterDao.findByAuth(auth);
			logger.info("Doctor's did :: [{}]", doctor.getId());
			List<Hospital> persistedHospitals = doctor.getHospital();
			logger.info("The number of hospitals in which the doctor works :: [{}]", persistedHospitals.size());
			List<Hospital> hospitals = new ArrayList<Hospital>();
			for (Hospital hosp : persistedHospitals) {
				List<Vaccine> vaccines = hosp.getVaccine();
				boolean flag = false;
				for (Vaccine vac : vaccines) {
					if (vac.getName().equalsIgnoreCase(vaccineName)) {
						flag = true;
						break;
					}
				}
				if (flag)
					hospitals.add(hosp);
			}
			return hospitals;
		} catch (Exception ex) {
			logger.error("Error occured while checking the availability of the vaccine :: [{}]", ex.getMessage());
			throw new ResourceExistsException(ex.getMessage());
		}
	}

	public String provideAppointment(AppointmentRequest appointment) {
		// TODO Auto-generated method stub
		logger.info("In service, proceeding with the given appointment having booking id :: [{}]",
				appointment.getBookingId());
		String message = "";
		try {
			Auth authUser = authDao.findByUsername(appointment.getUserName()).get();
			User user = userDao.findByAuth(authUser);
			logger.info("User found having uid :: [{}]", user.getId());
			Auth authDoctor = authDao.findByUsername(appointment.getDoctorUsername()).get();
			Doctor doctor = docterDao.findByAuth(authDoctor);
			logger.info("Doctor found having did ::[{}]", doctor.getId());
			Location loc = doctor.getLocation();
			logger.info("Location id :: [{}] with state :: [{}] and city :: [{}]", loc.getId(), loc.getState(),
					loc.getCity());
			Vaccine vaccine = vaccineDao.findByName(appointment.getVaccineName());
			logger.info("Vaccine details found having id :: [{}] and name :: [{}] and dosage no :: [{}]",
					vaccine.getId(), vaccine.getName(), vaccine.getDosage());
			String comments = appointment.getComments();
			String status = "";
			Date appointmentDate = null;
			String appointmentTime = null;
			String hospitalName = null;
			if (appointment.getApproved().equalsIgnoreCase("YES")) {
				status = "APPROVED";
				for (Dosage dosage : appointment.getDosages()) {
					Date date = Date.valueOf(dosage.getDate());
					String time = dosage.getTime();
					int dosageNo = dosage.getDoseNo();
					Hospital hospital = hospitalDao.findByNameAndLocation(dosage.getHospital(), loc);
					Appointment persistAppointment = new Appointment(user, doctor, vaccine, hospital, dosageNo, time,
							date, comments, status);
					persistAppointment = appointmentDao.save(persistAppointment);
					logger.info("Vaccination appointment has been provided with appointment id :: [{}]",
							persistAppointment.getAppointmentId());
					if (appointmentDate == null || appointmentTime.equalsIgnoreCase("")) {
						appointmentDate = persistAppointment.getAppointmentDate();
						appointmentTime = persistAppointment.getTime();
						hospitalName = hospital.getName();
					}
				}
				logger.info("Appointment for all the dosages have been updated successfully.");
				logger.debug("Proceeding to update the status for approval of vaccine booking.");
				VaccineBooking vaccineBooking = vaccineBookingDao.findById(appointment.getBookingId()).get();
				vaccineBooking.setStatus("APPROVED");
				vaccineBookingDao.save(vaccineBooking);
				logger.info("Vaccine Booking has been updated with the new status :: [{}] for the booking id ::[{}]",
						vaccineBooking.getStatus(), vaccineBooking.getBookingId());
				logger.info(
						"Proceeding to send mail to the respective user for the corresponding appointment date :: [{}] and time :; [{}]",
						appointmentDate, appointmentTime);
//				Call mailing method to send Approval mail
				message = "Vaccination appointment has been provided. Thank you for your kind assistance";
				String approvedMessage = String.format(Constants.approvedMessage, appointment.getVaccineName(),
						appointmentDate, appointmentTime, hospitalName);
				logger.info("Approved mail sent for vaccination :: [{}]", approvedMessage);
			} else {
				status = "REJECTED";
				VaccineBooking vaccineBooking = vaccineBookingDao.findById(appointment.getBookingId()).get();
				logger.debug("Proceeding to update the status for rejection of vaccine booking.");
				vaccineBooking.setStatus("REJECTED");
				vaccineBookingDao.save(vaccineBooking);
				logger.info("Vaccine Booking has been updated with the new status :: [{}] for the booking id ::[{}]",
						vaccineBooking.getStatus(), vaccineBooking.getBookingId());
				logger.info(
						"Proceeding to send mail to the respective user for the rejection of vaccine booking with comments :: [{}]",
						appointment.getComments());
//				Call mailing method to send Rejection mail
				message = "Vaccination request has been rejected. Thank you for your assistance";
				String rejectMessage = String.format(Constants.rejectMessage, appointment.getVaccineName(),
						appointment.getComments());
//				Mail.sendMail(new String[] { appointment.getUserName() }, "Vaccination Request Declined",
//						rejectMessage);
				logger.info("Rejection mail send successfully to user :: [{}] with message :: [{}]",
						appointment.getUserName(), rejectMessage);
			}
			return message;
		} catch (Exception ex) {
			logger.error("Error occured while providing the appointment :: [{}]", ex.getMessage());
			throw new ResourceExistsException(ex.getMessage());
		}
	}

}
