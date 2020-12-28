package com.example.SumulaEletronica;

public class SubstituicaoCreateDTO 
{
	
	private long id;
	private String periodo;
	private int tempo, entra, sai;
	private boolean equipeMandante;
	private final long sumula;
	
	public SubstituicaoCreateDTO(long id, String periodo, int tempo, int entra, int sai, boolean equipeMandante, long sumula) {
		super();
		this.id = id;
		this.periodo = periodo;
		this.tempo = tempo;
		this.entra = entra;
		this.sai = sai;
		this.equipeMandante = equipeMandante;
		this.sumula = sumula;
	}
	
	

	public long getSumula() {
		return sumula;
	}


	public long getId() {
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
