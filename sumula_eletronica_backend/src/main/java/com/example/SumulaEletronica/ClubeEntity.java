package com.example.SumulaEletronica;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "clube")
public final class ClubeEntity 
{
	@Column(length = 50)
	private String nome;
	@Column(length = 20)
	private String dataFundacao;
	@Column(length = 50)
	private String estadio;
	@Column(length = 10)
	private String cep;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@OneToMany(mappedBy="clube")  
    private List<AtletaEntity> atletas = new ArrayList<>();
	
	@OneToMany(mappedBy="clubeMandante")  
    private List<SumulaEntity> sumulaMandante = new ArrayList<>();
	
	@OneToMany(mappedBy="clubeVisitante")  
    private List<SumulaEntity> sumulaVisitante = new ArrayList<>();
	
	public ClubeEntity() {
		
	}
	
	
	public ClubeEntity(long id) {
		super();
		this.id = id;
	}


	public ClubeEntity(long id, String nome, String dataFundacao, String estadio, String cep) {
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

	/*public List<AtletaEntity> getAtletas() {
		return atletas;
	}*/
	
	
	
}
