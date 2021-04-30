/**
 * 
 */
package com.example.demo.utils;

/**
 * @author PRATAP
 *
 */
public class Constants {

	public final static String rejectMessage = "Dear User, \nYour request for Vaccination of %s has been declined with the following comment from the doctor : %s \n Regards,\n MRA";
	public final static String approvedMessage = "Dear User, \nYour request for Vaccination of %s has been approved. \n Appointment Date : %s \n Appointment Time: %s \n Hospital : %s \n Regards,\n MRA";
	public final static String statesAPI = "http://battuta.medunes.net/api/region/in/all/";
	public final static String citiesAPI = "http://battuta.medunes.net/api/city/in/search/?";
	public final static String apiKey = "b6a17e138fe85188d9ce5c7c457bc56e";
}
