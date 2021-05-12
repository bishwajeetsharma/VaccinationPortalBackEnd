/**
 * 
 */
package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.dao.AuthDao;
import com.example.demo.dao.DoctorDao;
import com.example.demo.dao.HospitalDao;
import com.example.demo.dao.LocationDao;
import com.example.demo.dao.RolesDao;
import com.example.demo.model.Auth;
import com.example.demo.model.Doctor;
import com.example.demo.model.DoctorRegistration;
import com.example.demo.model.Hospital;
import com.example.demo.model.Location;

/**
 * @author PRATAP
 *
 */
@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(MockitoExtension.class)
public class TestDoctorService {

	private DoctorService doctorService;

	@Mock
	private DoctorDao doctorDao;

	@Mock
	private AuthDao authDao;

	@Mock
	private RolesDao rolesDao;

	@Mock
	private LocationDao locationDao;
	
	@Mock
	private HospitalDao hospitalDao;

	@Before
	public void beforeMethod() {
		doctorService = new DoctorService(doctorDao, authDao, rolesDao, locationDao, hospitalDao);
	}

	/*
	 * @Test public void testRetrieveDoctorsWithMockRepository() throws Exception {
	 * 
	 * Optional<Doctor> doctor = Optional .of(new Doctor("Tapas", "1974-04-03",
	 * "Dhar", "744589999999", "6666666666", "Male"));
	 * 
	 * when(doctorDao.findById(1)).thenReturn(doctor); List<DoctorResponse> docResp
	 * = doctorService.getDoctorsByCity("pratapkumarchandra.96@gmail.com");
	 * Responses.doctorGetByCityResponse = new Gson().toJson(docResp).to();
	 * Optional<Doctor> doc = doctorDao.findById(1); verify(doctorDao).findById(1);
	 * assertEquals(doctor, doc); JSONAssert.assertEquals(new
	 * JSONArray(Responses.doctorGetByCityResponse), new JSONArray(new
	 * Gson().toJson(docResp)), false); }
	 */

	@Test
	public void testSaveDoctor() {
		Doctor doc = new Doctor("Pratap", "27-09-2019", "Chandra", "123456789089", "0987654321", "Male");
		Auth auth = new Auth("pratap@gmail.com", "Jetha@123");
		Location loc = new Location("West Bengal", "Kolkata");
		doc.setAuth(auth);
		List<Hospital> hospitals = new ArrayList<Hospital>();
		hospitals.add(new Hospital("Hospital1", "1234567890"));
		hospitals.add(new Hospital("Hospital2", "1414145233"));
		doc.setHospital(hospitals);
		DoctorRegistration docReg = new DoctorRegistration(doc, loc, auth);
		Doctor savedDoc = new Doctor();
		savedDoc.setId(1);
		when(doctorDao.save(doc)).thenReturn(savedDoc);
		int serviceSavedDoc = doctorService.registerDoctor(docReg);
		verify(doctorDao).save(doc);
		assertEquals(serviceSavedDoc, savedDoc.getId());
	}

}
