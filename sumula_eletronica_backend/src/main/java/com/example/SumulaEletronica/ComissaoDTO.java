package com.example.SumulaEletronica;


public class ComissaoDTO {

	public static final ComissaoDTO NULL_VALUE = new ComissaoDTO("", "", "", "", "", 0);
	
	private String tecnico, auxTecnico, prepGoleiro, medico, massagista;
	private long id;
	
	public ComissaoDTO(String tecnico, String auxTecnico, String prepGoleiro, String medico, String massagista,
			long id) {
		super();
		this.tecnico = tecnico;
		this.auxTecnico = auxTecnico;
		this.prepGoleiro = prepGoleiro;
		this.medico = medico;
		this.massagista = massagista;
		this.id = id;
	}

	public String getTecnico() {
		return tecnico;
	}

	public String getAuxTecnico() {
		return auxTecnico;
	}

	public String getPrepGoleiro() {
		return prepGoleiro;
	}

	public String getMedico() {
		return medico;
	}

	public String getMassagista() {
		return massagista;
	}

	public long getId() {
		return id;
	}
}
