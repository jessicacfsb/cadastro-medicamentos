package br.tec.ici.saude.medicamento.application.patient.service;

import java.util.List;

import br.tec.ici.saude.medicamento.application.patient.dao.PatientDao;
import br.tec.ici.saude.medicamento.application.patient.entity.Patient;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;

@Stateless
public class PatientService {
	@Inject
	private PatientDao patientDao;
	@PersistenceContext(unitName = "medicamento")
	private EntityManager entityManager;

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

		Object result = entityManager.createNativeQuery("SELECT P_PATIENT_AGE(:patientId)")
				.setParameter("patientId", patientId).getSingleResult();

		return ((Number) result).intValue();
	}

}

