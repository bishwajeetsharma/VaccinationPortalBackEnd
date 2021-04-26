/**
 * 
 */
package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.Exception.Details;
import com.example.demo.model.AppointmentRequest;
import com.example.demo.model.Auth;
import com.example.demo.model.DoctorRegistration;
import com.example.demo.model.DoctorResponse;
import com.example.demo.model.Hospital;
import com.example.demo.model.HospitalResponse;
import com.example.demo.model.ResponseFile;
import com.example.demo.service.DoctorService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author PRATAP
 *
 */
@RestController
@RequestMapping("/doctor")
@CrossOrigin("*")
public class DoctorController {

	private static final Logger logger = LogManager.getLogger(DoctorController.class);

	@Autowired
	private DoctorService doctorService;

	@RequestMapping(value = "/registerdoctor", method = RequestMethod.POST)
	@ApiOperation(value = "API for Registering the Doctors to MRA portal", response = Details.class)
	public ResponseEntity<Details> register(@RequestBody DoctorRegistration doctorRegData) {

		logger.info("Starting Doctor Registration Process for username :: [{}]", doctorRegData.getAuth().getUsername());
		String message = "Registration successful for the username : " + doctorRegData.getAuth().getUsername();
		try {
			int status = doctorService.registerDoctor(doctorRegData);
			if (status <= 0) {
				logger.info("Couldn't save Doctor Object.Please check the error");
			}
			Details details = new Details(new Date(), message, "/doctor/registerDoctor");
			return new ResponseEntity<Details>(details, HttpStatus.OK);
		} catch (Exception ex) {
			logger.error("Exception occured while persisting Doctor data :: [{}]", ex.getMessage());
			message = ex.getMessage();
		}
		Details details = new Details(new Date(), message, "/doctor/registerDoctor");
		return new ResponseEntity<Details>(details, HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/getDoctorsByCity", method = RequestMethod.GET)
	@ApiOperation(value = "API for Fetching all the doctors in a particular city", response = Details.class)
	public ResponseEntity<?> getDoctorsByCity(@RequestParam(required = true, value = "username") String username) {

		logger.info("Fetching the doctors located in the same city for the username :: [{}]", username);
		try {
			List<DoctorResponse> doctorsList = doctorService.getDoctorsByCity(username);
			return new ResponseEntity<List<DoctorResponse>>(doctorsList, HttpStatus.OK);
		} catch (Exception ex) {
			logger.error("Exception occured while persisting Doctor data :: [{}]", ex.getMessage());
			Details details = new Details(new Date(), ex.getMessage(), "/doctor/getDoctorsByCity");
			return new ResponseEntity<Details>(details, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/pendingApprovals/{username}", method = RequestMethod.GET)
	@ApiOperation(value = "API for fetching all the pending booking requests for a specific doctor's username")
	public ResponseEntity<?> getPendingApprovalsByDoctor(
			@PathVariable(name = "username", required = true) String userName) {
		logger.info("Fetching all the PENDING booking request for the doctor having username :: [{}]", userName);
		try {
			List<ResponseFile> files = doctorService.fetchPendingApprovals(userName).map(dbFile -> {
				String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/fc/files/")
						.path(dbFile.getFileDb().getId()).toUriString();

				return new ResponseFile(dbFile.getBookingId(), dbFile.getFileDb().getName(), fileDownloadUri,
						dbFile.getFileDb().getType(), dbFile.getFileDb().getData().length,
						dbFile.getUser().getFirstname(), dbFile.getUser().getLastname(), dbFile.getVaccine().getName(),
						dbFile.getBookingDate(), dbFile.getStatus(), dbFile.getUser().getAuth().getUsername(),
						dbFile.getVaccine().getDosage());
			}).collect(Collectors.toList());
			List<ResponseFile> pendingFiles = new ArrayList<ResponseFile>();
			for (ResponseFile file : files) {
				if (file.getStatus().equalsIgnoreCase("Pending")) {
					pendingFiles.add(file);
				}
			}
			return ResponseEntity.status(HttpStatus.OK).body(pendingFiles);
		} catch (Exception ex) {
			logger.error("Exception occured while persisting Doctor data :: [{}]", ex.getMessage());
			Details details = new Details(new Date(), ex.getMessage(), "/doctor/getPendingApprovals/{username}");
			return new ResponseEntity<Details>(details, HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/checkAvailability", method = RequestMethod.GET)
	@ApiOperation(value = "API for checking all the doctor's hospitals where he is working and the vaccine is available")
	public ResponseEntity<?> checkAvailability(
			@ApiParam(value = "The identifier of the doctor") @RequestParam(value = "userName", required = true) String doctorUserName,
			@ApiParam(value = "The identifier of the vaccine") @RequestParam(value = "vaccine", required = true) String vaccineName) {
		logger.info(
				"Proceeding to check whether the hospitals where the doctor ::[{}] works, the vaccine ::[{}] is available or not",
				doctorUserName, vaccineName);
		try {
			Auth auth = new Auth();
			auth.setUsername(doctorUserName);
			List<Hospital> hospitals = doctorService.checkAvailability(auth, vaccineName);
			List<HospitalResponse> hospitalResponse = hospitals.stream().map(hosp -> {
				return new HospitalResponse(hosp.getId(), hosp.getName());
			}).collect(Collectors.toList());
			return new ResponseEntity<List<HospitalResponse>>(hospitalResponse, HttpStatus.OK);
		} catch (Exception ex) {
			logger.error(
					"Exception occured while checking availability of the vaccines in the hospital where the corresponding doctor works :: [{}]",
					ex.getMessage());
			Details details = new Details(new Date(), ex.getMessage(), "/doctor/checkAvailability");
			return new ResponseEntity<Details>(details, HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/vaccineAppointment", method = RequestMethod.POST)
	@ApiOperation(value = "API for the appointment request approved or rejected by the doctor")
	public ResponseEntity<?> provideAppointment(
			@ApiParam("The request body for appointment approval or rejection") @RequestBody(required = true) AppointmentRequest appointment) {
		logger.info("Proceeding to check the appointment request by the doctor whether approved ::[{}]",
				appointment.getApproved());
		try {
			String message = doctorService.provideAppointment(appointment);
			Details details = new Details(new Date(), message, "/doctor/vaccineAppointment");
			return new ResponseEntity<Details>(details, HttpStatus.ACCEPTED);
		} catch (Exception ex) {
			logger.error("Exception occured while appointment transacion :: [{}]", ex.getMessage());
			Details details = new Details(new Date(), ex.getMessage(), "/doctor/vaccineAppointment");
			return new ResponseEntity<Details>(details, HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/approvedApprovals/{username}", method = RequestMethod.GET)
	@ApiOperation(value = "API for fetching all the approved booking requests for a specific doctor's username")
	public ResponseEntity<?> getApprovedApprovalsByDoctor(
			@PathVariable(name = "username", required = true) String userName) {
		logger.info("Fetching all the APPROVED booking request for the doctor having username :: [{}]", userName);
		try {
			List<ResponseFile> files = doctorService.fetchPendingApprovals(userName).map(dbFile -> {
				String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/fc/files/")
						.path(dbFile.getFileDb().getId()).toUriString();

				return new ResponseFile(dbFile.getBookingId(), dbFile.getFileDb().getName(), fileDownloadUri,
						dbFile.getFileDb().getType(), dbFile.getFileDb().getData().length,
						dbFile.getUser().getFirstname(), dbFile.getUser().getLastname(), dbFile.getVaccine().getName(),
						dbFile.getBookingDate(), dbFile.getStatus(), dbFile.getUser().getAuth().getUsername(),
						dbFile.getVaccine().getDosage());
			}).collect(Collectors.toList());
			List<ResponseFile> pendingFiles = new ArrayList<ResponseFile>();
			for (ResponseFile file : files) {
				if (file.getStatus().equalsIgnoreCase("APPROVED")) {
					pendingFiles.add(file);
				}
			}
			return ResponseEntity.status(HttpStatus.OK).body(pendingFiles);
		} catch (Exception ex) {
			logger.error("Exception occured while searching APPROVED approvals :: [{}]", ex.getMessage());
			Details details = new Details(new Date(), ex.getMessage(), "/doctor/getPendingApprovals/{username}");
			return new ResponseEntity<Details>(details, HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/rejectedApprovals/{username}", method = RequestMethod.GET)
	@ApiOperation(value = "API for fetching all the approved booking requests for a specific doctor's username")
	public ResponseEntity<?> getRejectedApprovalsByDoctor(
			@PathVariable(name = "username", required = true) String userName) {
		logger.info("Fetching all the REJECTED booking request for the doctor having username :: [{}]", userName);
		try {
			List<ResponseFile> files = doctorService.fetchPendingApprovals(userName).map(dbFile -> {
				String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/fc/files/")
						.path(dbFile.getFileDb().getId()).toUriString();

				return new ResponseFile(dbFile.getBookingId(), dbFile.getFileDb().getName(), fileDownloadUri,
						dbFile.getFileDb().getType(), dbFile.getFileDb().getData().length,
						dbFile.getUser().getFirstname(), dbFile.getUser().getLastname(), dbFile.getVaccine().getName(),
						dbFile.getBookingDate(), dbFile.getStatus(), dbFile.getUser().getAuth().getUsername(),
						dbFile.getVaccine().getDosage());
			}).collect(Collectors.toList());
			List<ResponseFile> pendingFiles = new ArrayList<ResponseFile>();
			for (ResponseFile file : files) {
				if (file.getStatus().equalsIgnoreCase("REJECTED")) {
					pendingFiles.add(file);
				}
			}
			return ResponseEntity.status(HttpStatus.OK).body(pendingFiles);
		} catch (Exception ex) {
			logger.error("Exception occured while searching REJECTED approvals :: [{}]", ex.getMessage());
			Details details = new Details(new Date(), ex.getMessage(), "/doctor/getPendingApprovals/{username}");
			return new ResponseEntity<Details>(details, HttpStatus.EXPECTATION_FAILED);
		}
	}

}
