package br.tec.ici.saude.medicamento.application.patient.bean;

import java.io.Serializable;
import java.util.List;

import br.tec.ici.saude.medicamento.application.patient.entity.Patient;
import br.tec.ici.saude.medicamento.application.patient.service.PatientService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ViewScoped
public class PatientBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private PatientService patientService;

	private List<Patient> patients;
	private Patient patient;
	private Integer calculatedAge;

	@PostConstruct
	public void init() {
		patient = new Patient();
		loadPatients();
	}

	public void loadPatients() {
		patients = patientService.findAll();
	}

	public void save() {
		try {
			patientService.save(patient);

			addMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Paciente salvo com sucesso.");

			patient = new Patient();
			loadPatients();

		} catch (Exception e) {
			addMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Não foi possível salvar o paciente.");

			e.printStackTrace();
		}
	}

	public void calculateAge() {
		try {
			if (patient == null || patient.getId() == null) {
				addMessage(FacesMessage.SEVERITY_WARN, "Atenção", "Selecione um paciente.");
				return;
			}

			calculatedAge = patientService.calculateAge(patient.getId());

			addMessage(FacesMessage.SEVERITY_INFO, "Idade calculada", "Idade: " + calculatedAge + " anos.");

		} catch (Exception e) {
			e.printStackTrace();

			addMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Erro ao calcular idade.");
		}
	}

	public void edit(Patient selectedPatient) {
		if (selectedPatient != null) {
			this.patient = selectedPatient;
		}
	}

	private void addMessage(FacesMessage.Severity severity, String summary, String detail) {

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
	}

	public List<Patient> getPatients() {
		return patients;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Integer getCalculatedAge() {
		return calculatedAge;
	}
}