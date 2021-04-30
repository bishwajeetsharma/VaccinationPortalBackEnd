package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Exception.ResourceExistsException;
import com.example.demo.dao.HospitalDao;
import com.example.demo.dao.VaccineDao;
import com.example.demo.dao.VaccineHospitalDao;
import com.example.demo.model.Hospital;
import com.example.demo.model.Vaccine;
import com.example.demo.model.VaccineDetail;
import com.example.demo.model.VaccineHospital;

@Service
@Transactional
public class VaccineService {

	private static final Logger logger = LogManager.getLogger(VaccineService.class);

	@Autowired
	private VaccineDao vaccinedao;

	@Autowired
	private HospitalDao hospitaldao;

	@Autowired
	private VaccineHospitalDao vHDao;

	public List<String> fetchVaccines() {
		List<String> vaccines = (List<String>) vaccinedao.fetchVaccines();
		return vaccines;
	}

	public void saveVaccine(VaccineDetail vaccinedetail) throws Exception {
//		Optional<Hospital> hospital = hospitaldao.findById(Integer.valueOf(hid));
//		if (vaccinedao.existsByName(vaccineName)) {
//			Vaccine vaccine = vaccinedao.findByName(vaccineName);
//			if (vaccine.getHospital().contains(hospital.get()))
//				throw new Exception("A vaccine with this name is already registered for this hospital.");
//			if (vaccine.getDosage().intValue() != dosage)
//				throw new Exception("Dosage for the same vaccine cannot be different.");
//			List<Hospital> listhospital = vaccine.getHospital();
//			listhospital.add(hospital.get());
//			vaccine.setHospital(listhospital);
//			vaccinedao.save(vaccine);
//			List<Vaccine> listvaccine = hospital.get().getVaccine();
//			listvaccine.add(vaccine);
//			hospital.get().setVaccine(listvaccine);
//			hospitaldao.save(hospital.get());
//		} else {
//			Vaccine newVaccine = new Vaccine(vaccineName, Integer.valueOf(dosage));
//			List<Hospital> hospitallist = new ArrayList<Hospital>();
//			hospitallist.add(hospital.get());
//			newVaccine.setHospital(hospitallist);
//			vaccinedao.save(newVaccine);
//			List<Vaccine> vaccinelist = hospital.get().getVaccine();
//			vaccinelist.add(newVaccine);
//			hospital.get().setVaccine(vaccinelist);
//			hospitaldao.save(hospital.get());
//		}
		logger.info("In service, proceeding to save the vaccine having name :: [{}]", vaccinedetail.getVaccineName());
		try {
			Hospital hospital = hospitaldao.findById(Integer.valueOf(vaccinedetail.getHid())).get();
			logger.info("Hospital successfully fetched with hospital id :: [{}]", hospital.getId());
			Vaccine vaccine = vaccinedao.findByName(vaccinedetail.getVaccineName());
			if (vaccine != null) {
				logger.info("Vaccine fetched successfully with vaccine id :: [{}]", vaccine.getId());
				VaccineHospital vH = vHDao.findByHidAndVid(hospital, vaccine);
				if (vH != null && vH.getVaccinesAvailable() == vaccinedetail.getNoOfVaccines())
					throw new Exception("A vaccine with this name is already registered for this hospital.");
				if (vaccine.getDosage() != vaccinedetail.getDosage())
					throw new Exception("Dosage for the same vaccine cannot be different.");
				if (vH == null) {
					VaccineHospital vHNew = new VaccineHospital();
					vHNew.setHid(hospital);
					vHNew.setVid(vaccine);
					vHNew.setVaccinesAvailable(vaccinedetail.getNoOfVaccines());
					logger.info("Proceeding to save the vaccine hospital");
					vHNew = vHDao.save(vHNew);
					logger.info("Vaccine Hospital saved successfully with id :: [{}]", vHNew.getId());
				} else {
					VaccineHospital vHNew = vH;
					vHNew.setVaccinesAvailable(vaccinedetail.getNoOfVaccines());
					logger.info("Proceeding to save the vaccine hospital");
					vHNew = vHDao.save(vHNew);
					logger.info("Vaccine Hospital saved successfully with id :: [{}]", vHNew.getId());
				}
			} else {
				Vaccine newVaccine = new Vaccine(vaccinedetail.getVaccineName(),
						Integer.valueOf(vaccinedetail.getDosage()));
				List<VaccineHospital> vHList = new ArrayList<VaccineHospital>();
				VaccineHospital vH = new VaccineHospital();
				vH.setHid(hospital);
				vH.setVid(newVaccine);
				vH.setVaccinesAvailable(vaccinedetail.getNoOfVaccines());
				vHList.add(vH);
				newVaccine.setVaccineHospital(vHList);
				logger.info("Proceeding to save the vaccine first");
				newVaccine = vaccinedao.save(newVaccine);
				logger.info("New vaccine saved successfully having name :: [{}] and id :: [{}]", newVaccine.getName(),
						newVaccine.getId());
			}
		} catch (Exception ex) {
			throw new ResourceExistsException(ex.getMessage());
		}

	}

	public List<VaccineDetail> fetchVaccineByHid(Integer hid) {
		Hospital hospital = hospitaldao.findById(hid).get();
		List<Vaccine> vaccinelist = new ArrayList<Vaccine>();
		for (VaccineHospital vh : hospital.getVaccineHospital()) {
			vaccinelist.add(vh.getVid());
		}
		int i = 0;
		List<VaccineDetail> vlist = new ArrayList<>();
		for (Vaccine v : vaccinelist) {
			vlist.add(new VaccineDetail(v.getName(), v.getId().intValue(), v.getDosage(),
					hospital.getVaccineHospital().get(i).getVaccinesAvailable()));
			i++;
		}
		return vlist;
	}

	public String deleteVaccine(int hid, int vid) {

		logger.info("In service, proceeding to delete the vaacine with id :: [{}] in hospital having id :: [{}]", vid,
				hid);
		/*
		 * Hospital hospital = hospitaldao.findById(Integer.valueOf(hid)).get(); Vaccine
		 * vaccine = vaccinedao.findById(Integer.valueOf(vid)).get(); List<Hospital>
		 * hospitalList = new ArrayList<Hospital>(); for (VaccineHospital vh :
		 * vaccine.getVaccineHospital()) { hospitalList.add(vh.getHid()); }
		 * hospitalList.remove(hospital); vaccine.setHospital(hospital_list);
		 * vaccinedao.save(vaccine); List<Vaccine> vaccine_list = hospital.getVaccine();
		 * vaccine_list.remove(vaccine); hospital.setVaccine(vaccine_list);
		 * hospitaldao.save(hospital);
		 */
		String msg = "";
		try {
			Hospital hospital = hospitaldao.findById(Integer.valueOf(hid)).get();
			logger.info("Hospital fetched successfully with hospital id :: [{}]", hospital.getId());
			Vaccine vaccine = vaccinedao.findById(Integer.valueOf(vid)).get();
			logger.info("Vaccine fetched successfully with vaccine id :: [{}]", vaccine.getId());
			VaccineHospital vH = vHDao.findByHidAndVid(hospital, vaccine);
			if (vH != null) {
				logger.info("Vaccine Hospital found having id :: [{}]", vH.getId());
				vHDao.delete(vH);
				logger.info("Vaccine Hospital deleted successfully from the table");
				msg = "Vaccine Deleted Successfully!!!";
			} else {
				logger.info(
						"There is no such entity present in Vaccine Hospital with the given Vaccine id and hospital id");
				msg = "There doesn't exist any hospital with the provided vaccine";
			}
		} catch (Exception ex) {
			throw new ResourceExistsException(ex.getMessage());
		}
		return msg;
	}
}
