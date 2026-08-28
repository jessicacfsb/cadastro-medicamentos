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
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery("P_PATIENT_AGE");
		query.registerStoredProcedureParameter("p_patient_id", Long.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_age_years", Integer.class, ParameterMode.OUT);
		query.setParameter("p_patient_id", patientId);
		query.execute();
		Object result = query.getOutputParameterValue("p_age_years");
		return ((Number) result).intValue();
	}

}

