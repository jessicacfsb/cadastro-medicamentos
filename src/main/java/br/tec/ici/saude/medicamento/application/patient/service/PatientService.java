package br.tec.ici.saude.medicamento.application.patient.service;

import java.util.List;

import br.tec.ici.saude.medicamento.application.patient.dao.PatientDao;
import br.tec.ici.saude.medicamento.application.patient.entity.Patient;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class PatientService {
	
	@Inject
	private PatientDao patientDao;


	public List<Patient> findAll() {
		return patientDao.findAll();
	}

	public Patient findById(Long id) {
		return patientDao.findById(id);
	}

	public void save(Patient patient) {
		patientDao.save(patient);
	}

	public Integer calculateAge(Long patientId) {
		return patientDao.calculateAge(patientId);
	}

}

