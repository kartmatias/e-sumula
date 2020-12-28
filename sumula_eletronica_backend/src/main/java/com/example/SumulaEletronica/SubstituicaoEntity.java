package com.example.SumulaEletronica;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "substituicao")
public class SubstituicaoEntity
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(length = 10)
	private String periodo;
	@Column
	private int tempo, entra, sai;
	@Column
	private boolean equipeMandante;
	@ManyToOne()
	private SumulaEntity sumula;
	
	public SubstituicaoEntity(){
		
	}
	
	
	
	public SubstituicaoEntity(Long id, String periodo, int tempo, int entra, int sai, boolean equipeMandante,
			SumulaEntity sumula) {
		super();
		this.id = id;
		this.periodo = periodo;
		this.tempo = tempo;
		this.entra = entra;
		this.sai = sai;
		this.equipeMandante = equipeMandante;
		this.sumula = sumula;
	}



	public SubstituicaoEntity(String periodo, int tempo, int entra, int sai, boolean equipeMandante, Long id) {
		super();
		this.periodo = periodo;
		this.tempo = tempo;
		this.entra = entra;
		this.sai = sai;
		this.equipeMandante = equipeMandante;
		this.id = id;
	}
	
	
	public Long getId() {
		return id;
	}


	public String getPeriodo() {
		return periodo;
	}

	public int getTempo() {
		return tempo;
	}

	public int getEntra() {
		return entra;
	}

	public int getSai() {
		return sai;
	}

	public boolean isEquipeMandante() {
		return equipeMandante;
	}
	
	
}
