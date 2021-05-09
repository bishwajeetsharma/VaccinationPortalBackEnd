/**
 * 
 */
package com.example.demo.testcontroller;

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

import com.example.demo.controller.DoctorController;
import com.example.demo.model.DoctorResponse;
import com.example.demo.service.DoctorService;

/**
 * @author PRATAP
 *
 */
@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(MockitoExtension.class)
public class UnitTestDoctorController {

	private DoctorController doctorController;

	@Mock
	private DoctorService doctorService;

	@Before
	public void beforeMethod() {
		doctorController = new DoctorController(doctorService);
	}

	@Test
	public void testGetByCity() throws Exception {

		List<DoctorResponse> resp = new ArrayList<DoctorResponse>();
		resp.add(new DoctorResponse(1, "Tapas", "Dhar", "6666666666", "744589999999"));
		resp.add(new DoctorResponse(2, "Mammen", "Chandy", "8871164646", "146571162454"));
		when(doctorService.getDoctorsByCity("pratapkumarchandra.96@gmail.com")).thenReturn(resp);
//		List<DoctorResponse> docResp = this.doctorService.getDoctorsByCity("pratapkumarchandra.96@gmail.com");
		@SuppressWarnings("unchecked")
		List<DoctorResponse> docResp = (List<DoctorResponse>) doctorController
				.getDoctorsByCity("pratapkumarchandra.96@gmail.com").getBody();
		verify(doctorService).getDoctorsByCity("pratapkumarchandra.96@gmail.com");
		assertEquals(resp, docResp);
	}
}
