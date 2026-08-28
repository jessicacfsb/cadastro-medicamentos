package br.tec.ici.saude.medicamento.application.patient.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "PATIENT")
public class Patient {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "O nome completo é obrigatório.")
    @Size(max = 255, message = "O nome completo não pode ter mais de 255 caracteres.")
    @Column(name = "FULL_NAME", nullable = false, length = 255)
    private String fullName;

    @NotNull(message = "A data de nascimento é obrigatória.")
    @Past(message = "A data de nascimento deve ser uma data passada.")
    @Column(name = "DATE_OF_BIRTH", nullable = false)
    private LocalDate dateOfBirth;

	public String getDateOfBirthFormatted() {
		if (dateOfBirth == null) {
			return "";
		}

		return dateOfBirth.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}

}
