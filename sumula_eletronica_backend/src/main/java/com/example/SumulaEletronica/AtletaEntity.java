package com.example.SumulaEletronica;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity(name = "atleta")
public class AtletaEntity 
{
	@Column(length = 50)
	private String nome;
	@Column(length = 50)
	private String dataNascimento;
	@Column(length = 50)
	private String cpf;
	@Column(length = 50)
	private String bid;
	@Id	
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@ManyToOne()
    private ClubeEntity clube;	
	
	@OneToMany(mappedBy="atleta")  
    private List<RelacaoEntity> relacoes = new ArrayList<>();
	
	public AtletaEntity() {
		
	}
	
	

	public AtletaEntity(long id) {
		super();
		this.id = id;
	}


	public AtletaEntity(long id, String nome, String dataNascimento, String cpf, String bid, ClubeEntity clube) {
		super();
		this.id = id;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.cpf = cpf;
		this.bid = bid;
		this.clube = clube;
	}

	public String getNome() {
		return nome;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public String getCpf() {
		return cpf;
	}

	public String getBid() {
		return bid;
	}

	public long getId() {
		return id;
	}

	public ClubeEntity getClube() {
		return clube;
	}
	
	
}
