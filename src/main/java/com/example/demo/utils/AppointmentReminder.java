/**
 * 
 */
package com.example.demo.utils;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.demo.dao.AppointmentDao;
import com.example.demo.model.Appointment;

/**
 * @author PRATAP
 *
 */
@Component
public class AppointmentReminder {

	private static final Logger logger = LogManager.getLogger(AppointmentReminder.class);
	private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

	@Autowired
	private AppointmentDao appointmentDao;

	@Scheduled(fixedRate = 24 * 60 * 60 * 1000)
	public void scheduleTaskWithFixedRate() {
		logger.info("Proceeding to send reminder to all the Patients one day before vacciantion");
		try {
			List<Appointment> appointments = (List<Appointment>) appointmentDao.findAll();
			logger.info("The total number of appointments to be checked for date are :: [{}]", appointments.size());
			java.util.Date date = new java.util.Date();
			String dateToString = formatter.format(date);
			for (Appointment apt : appointments) {
				Date aptDate = apt.getAppointmentDate();
				Date today = java.sql.Date.valueOf(dateToString);
				long days = TimeUnit.DAYS.convert(aptDate.getTime() - today.getTime(), TimeUnit.MILLISECONDS);
				if (days == 1) {
					String approvedMessage = String.format(Constants.approvedMessage,
							apt.getVaccine().getName() + " Dose " + apt.getDoseNo(), apt.getAppointmentDate(),
							apt.getTime(), apt.getHospital().getName());
					Mail.sendMail(new String[] { apt.getUser().getAuth().getUsername() },
							"Vaccination Request Approved", approvedMessage);
					logger.info("Approved mail sent for vaccination :: [{}]", approvedMessage);
				}
			}
		} catch (Exception ex) {
			logger.error("Exception occured while sending reminder mail to user :: [{}]", ex.getMessage());
		}

	}

}
