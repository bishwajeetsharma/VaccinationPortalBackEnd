package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.HospitalDao;
import com.example.demo.dao.VaccineDao;
import com.example.demo.model.Hospital;
import com.example.demo.model.Vaccine;
import com.example.demo.model.VaccineDetail;

@Service
@Transactional
public class VaccineService {

	@Autowired
	private VaccineDao vaccinedao;

	@Autowired
	private HospitalDao hospitaldao;

	public List<String> fetchVaccines() {
		List<String> vaccines = (List<String>) vaccinedao.fetchVaccines();
		return vaccines;
	}

	public void saveVaccine(String vaccineName, int dosage, int hid) throws Exception {
		Optional<Hospital> hospital = hospitaldao.findById(Integer.valueOf(hid));
		if (vaccinedao.existsByName(vaccineName)) {
			Vaccine vaccine = vaccinedao.findByName(vaccineName);
			if (vaccine.getHospital().contains(hospital.get()))
				throw new Exception("A vaccine with this name is already registered for this hospital.");
			if (vaccine.getDosage().intValue() != dosage)
				throw new Exception("Dosage for the same vaccine cannot be different.");
			List<Hospital> listhospital = vaccine.getHospital();
			listhospital.add(hospital.get());
			vaccine.setHospital(listhospital);
			vaccinedao.save(vaccine);
			List<Vaccine> listvaccine = hospital.get().getVaccine();
			listvaccine.add(vaccine);
			hospital.get().setVaccine(listvaccine);
			hospitaldao.save(hospital.get());
		} else {
			Vaccine newVaccine = new Vaccine(vaccineName, Integer.valueOf(dosage));
			List<Hospital> hospitallist = new ArrayList<Hospital>();
			hospitallist.add(hospital.get());
			newVaccine.setHospital(hospitallist);
			vaccinedao.save(newVaccine);
			List<Vaccine> vaccinelist = hospital.get().getVaccine();
			vaccinelist.add(newVaccine);
			hospital.get().setVaccine(vaccinelist);
			hospitaldao.save(hospital.get());
		}

	}

	public List<VaccineDetail> fetchVaccineByHid(Integer hid) {
		Optional<Hospital> hospital = hospitaldao.findById(hid);
		List<Vaccine> vaccinelist = hospital.get().getVaccine();
		List<VaccineDetail> vlist = new ArrayList<>();
		for (Vaccine v : vaccinelist) {
			vlist.add(new VaccineDetail(v.getName(), v.getId().intValue(), v.getDosage()));
		}
		return vlist;
	}

	public void deleteVaccine(int hid, int vid) {

		Optional<Hospital> hospital = hospitaldao.findById(Integer.valueOf(hid));
		Optional<Vaccine> vaccine = vaccinedao.findById(Integer.valueOf(vid));
		List<Hospital> hospital_list = vaccine.get().getHospital();
		hospital_list.remove(hospital.get());
		vaccine.get().setHospital(hospital_list);
		vaccinedao.save(vaccine.get());
		List<Vaccine> vaccine_list = hospital.get().getVaccine();
		vaccine_list.remove(vaccine.get());
		hospital.get().setVaccine(vaccine_list);
		hospitaldao.save(hospital.get());
	}
}
