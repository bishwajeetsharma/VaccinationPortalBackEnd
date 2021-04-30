/**
 * 
 */
package com.example.demo.controller;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.Exception.Details;
import com.example.demo.model.Cities;
import com.example.demo.model.States;
import com.example.demo.utils.Constants;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author PRATAP
 *
 */
@RestController
@RequestMapping("/external")
@CrossOrigin("*")
public class ExternalApis {

	private static final Logger logger = LogManager.getLogger(ExternalApis.class);

	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping(value = "/getStates", method = RequestMethod.GET)
	@ApiOperation(value = "API for getting the states for Sign Up", response = Details.class)
	public ResponseEntity<?> getStates() {
		logger.info("Proceeding to fetch all the states from the external API");
		try {
			States[] states = restTemplate.getForObject(Constants.statesAPI + "?key=" + Constants.apiKey,
					States[].class);
			logger.info("No of states fetched from external API :: [{}]", states.length);
			return new ResponseEntity<States[]>(states, HttpStatus.OK);
		} catch (Exception ex) {
			logger.error("Exception occured while fetching states from external API :: [{}]", ex.getMessage());
			Details details = new Details(new Date(), ex.getMessage(), "/external/getStates");
			return new ResponseEntity<Details>(details, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(value = "/getCities/{state}", method = RequestMethod.GET)
	@ApiOperation(value = "API for getting the cities for Sign Up corresponding to a particular State", response = Details.class)
	public ResponseEntity<?> getCities(
			@ApiParam(value = "Enter the state name") @PathVariable(name = "state") String state) {
		logger.info("Proceeding to fetch all the cities from the external API for the state :: [{}]", state);
		try {
			Cities[] cities = restTemplate
					.getForObject(Constants.citiesAPI + "region=" + state + "&key=" + Constants.apiKey, Cities[].class);
			logger.info("No of cities fetched from external API for state :: [{}] is :: [{}]", state, cities.length);
			return new ResponseEntity<Cities[]>(cities, HttpStatus.OK);
		} catch (Exception ex) {
			logger.error("Exception occured while fetching cities from external API :: [{}]", ex.getMessage());
			Details details = new Details(new Date(), ex.getMessage(), "/external/getCities");
			return new ResponseEntity<Details>(details, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
