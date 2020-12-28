package com.example.SumulaEletronica;

public final class ClubeDTO 
{
	private final String nome, dataFundacao, estadio, cep;
	private final long id;
	
	public static final ClubeDTO NULL_VALUE = new ClubeDTO(0, "null", "", "", "");

	public ClubeDTO(long id, String nome, String dataFundacao, String estadio, String cep) {
		super();
		this.id = id;
		this.nome = nome;
		this.dataFundacao = dataFundacao;
		this.estadio = estadio;
		this.cep = cep;
	}

	public String getNome() {
		return nome;
	}

	public String getDataFundacao() {
		return dataFundacao;
	}

	public String getEstadio() {
		return estadio;
	}

	public String getCep() {
		return cep;
	}
	
	public long getId() {
		return id;
	}
	
	
}
