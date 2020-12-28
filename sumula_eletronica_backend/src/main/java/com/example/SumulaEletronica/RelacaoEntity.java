package com.example.SumulaEletronica;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "Relacao")
public class RelacaoEntity 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	private int numero;
	private int gol;
	
	@Column(length=30)
	private String titular;
	private String cartoes;
	
	@ManyToOne()
	private AtletaEntity atleta;
	
	@ManyToOne()
	private EscalacaoEntity escalacao;
	
	public RelacaoEntity() {
		
	}
	
		
	public RelacaoEntity(Long id) {
		super();
		this.id = id;
	}

	public RelacaoEntity(Long id, int numero, int gol, String titular, String cartoes) {
		super();
		this.id = id;
		this.gol = gol;
		this.numero = numero;
		this.titular = titular;
		this.cartoes = cartoes;
	}
	
		
	public RelacaoEntity(Long id, int numero, int gol, String titular, String cartoes, AtletaEntity atleta,
			EscalacaoEntity escalacao) {
		super();
		this.id = id;
		this.numero = numero;
		this.gol = gol;
		this.titular = titular;
		this.cartoes = cartoes;
		this.atleta = atleta;
		this.escalacao = escalacao;
	}


	public Long getId() {
		return id;
	}

	public int getGol() {
		return gol;
	}

	public int getNumero() {
		return numero;
	}

	public String getTitular() {
		return titular;
	}
	public String getCartoes() {
		return cartoes;
	}

	public AtletaEntity getAtleta() {
		return atleta;
	}	
	
	
	
}
