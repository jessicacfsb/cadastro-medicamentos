package br.tec.ici.saude.medicamento.application.patient.dao;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import br.tec.ici.saude.medicamento.application.patient.entity.Patient;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class PatientDaoTest {

    @InjectMocks
    private PatientDao patientDao;

    @Mock
    private EntityManager entityManager;

    @Mock
    private TypedQuery<Patient> query;

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

        when(entityManager.createQuery("SELECT p FROM Patient p ORDER BY p.fullName", Patient.class)).thenReturn(query);
        when(query.getResultList()).thenReturn(Arrays.asList(patient1, patient2));

        List<Patient> result = patientDao.findAll();

        assertEquals(2, result.size());
        assertEquals("John Doe", result.get(0).getFullName());
        assertEquals("Jane Doe", result.get(1).getFullName());
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        Patient patient = new Patient();
        patient.setId(id);
        
        when(entityManager.find(Patient.class, id)).thenReturn(patient);

        Patient result = patientDao.findById(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
    }

    @Test
    public void testSaveNewPatient() {
        Patient patient = new Patient();
        patient.setId(null);

        patientDao.save(patient);

        verify(entityManager).persist(patient);
    }

    @Test
    public void testSaveExistingPatient() {
        Patient patient = new Patient();
        patient.setId(1L);

        patientDao.save(patient);

        verify(entityManager).merge(patient);
    }

    @Test
    public void testDelete() {
        Patient patient = new Patient();
        patient.setId(1L);

        when(entityManager.contains(patient)).thenReturn(false);
        when(entityManager.merge(patient)).thenReturn(patient);

        patientDao.delete(patient);

        verify(entityManager).remove(patient);
    }

    @Test
    public void testCalculateAge() {
        Long patientId = 1L;
        Integer expectedAge = 30;

        Query queryMock = mock(Query.class);
        
        when(entityManager.createNativeQuery("SELECT P_PATIENT_AGE(:patientId)")).thenReturn(queryMock);
        when(queryMock.setParameter("patientId", patientId)).thenReturn(queryMock);
        when(queryMock.getSingleResult()).thenReturn(expectedAge);

        Integer actualAge = patientDao.calculateAge(patientId);

        assertEquals(expectedAge, actualAge);

        verify(entityManager).createNativeQuery("SELECT P_PATIENT_AGE(:patientId)");
        verify(queryMock).setParameter("patientId", patientId);
        verify(queryMock).getSingleResult();
    }
}