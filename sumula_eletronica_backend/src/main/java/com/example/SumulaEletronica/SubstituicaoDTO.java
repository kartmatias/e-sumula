package com.example.SumulaEletronica;


public class SubstituicaoDTO 
{
	public static final SubstituicaoDTO NULL_VALUE = new SubstituicaoDTO(0, "", 0, 0, 0, false);
	
	private long id;
	private String periodo;
	private int tempo, entra, sai;
	private boolean equipeMandante;
	
	public SubstituicaoDTO(long id, String periodo, int tempo, int entra, int sai, boolean equipeMandante) {
		super();
		this.id = id;
		this.periodo = periodo;
		this.tempo = tempo;
		this.entra = entra;
		this.sai = sai;
		this.equipeMandante = equipeMandante;
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
