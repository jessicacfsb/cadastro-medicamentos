package br.tec.ici.saude.medicamento.application;

import java.io.Serializable;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

@ViewScoped
@Named
public class IndexBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String descricao;

	@PostConstruct
	public void postConstruct() {
		this.descricao = "Cadastrar um novo paciente.";
	}

	public String redirectPacientes() {
		return "/pages/patient-list.xhtml?faces-redirect=true";
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}