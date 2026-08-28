package br.tec.ici.saude.medicamento.application.patient.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import br.tec.ici.saude.medicamento.application.patient.dao.PatientDao;
import br.tec.ici.saude.medicamento.application.patient.entity.Patient;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class PatientServiceTest {

    @InjectMocks
    private PatientService patientService;

    @Mock
    private PatientDao patientDao;

    @Mock
    private EntityManager entityManager;

    @Mock
    private Query query;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        Patient patient1 = new Patient();
        patient1.setFullName("John Doe");
        
        Patient patient2 = new Patient();
        patient2.setFullName("Jane Doe");

        when(patientDao.findAll()).thenReturn(Arrays.asList(patient1, patient2));

        List<Patient> result = patientService.findAll();

        assertEquals(2, result.size());
        assertEquals("John Doe", result.get(0).getFullName());
        assertEquals("Jane Doe", result.get(1).getFullName());
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        Patient patient = new Patient();
        patient.setId(id);
        
        when(patientDao.findById(id)).thenReturn(patient);

        Patient result = patientService.findById(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
    }

    @Test
    public void testSave() {
        Patient patient = new Patient();
        patient.setId(1L);

        patientService.save(patient);

        verify(patientDao).save(patient);
    }

    @Test
    public void testCalculateAge() {
        Long patientId = 1L;
        int expectedAge = 30;

        when(entityManager.createNativeQuery("SELECT P_PATIENT_AGE(:patientId)")).thenReturn(query);
        when(query.setParameter("patientId", patientId)).thenReturn(query);
        when(query.getSingleResult()).thenReturn(expectedAge);

        Integer result = patientService.calculateAge(patientId);

        assertEquals(expectedAge, result);
        verify(entityManager).createNativeQuery("SELECT P_PATIENT_AGE(:patientId)");
        verify(query).setParameter("patientId", patientId);
        verify(query).getSingleResult();
    }
}