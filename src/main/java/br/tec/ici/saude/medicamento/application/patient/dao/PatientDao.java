package br.tec.ici.saude.medicamento.application.patient.dao;

import java.util.List;

import br.tec.ici.saude.medicamento.application.patient.entity.Patient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@ApplicationScoped
public class PatientDao {

	@PersistenceContext(unitName = "medicamento")
	private EntityManager entityManager;

	public List<Patient> findAll() {
		return entityManager.createQuery("SELECT p FROM Patient p ORDER BY p.fullName", Patient.class).getResultList();
	}

	public Patient findById(Long id) {
		return entityManager.find(Patient.class, id);
	}

	public void save(Patient patient) {
		if (patient.getId() == null) {
			entityManager.persist(patient);
		} else {
			entityManager.merge(patient);
		}
	}

	public void delete(Patient patient) {
		Patient managed = entityManager.contains(patient) ? patient : entityManager.merge(patient);
		entityManager.remove(managed);
	}

	public Integer calculateAge(Long patientId) {

		Object result = entityManager.createNativeQuery("SELECT P_PATIENT_AGE(:patientId)")
				.setParameter("patientId", patientId).getSingleResult();

		return ((Number) result).intValue();
	}
}
