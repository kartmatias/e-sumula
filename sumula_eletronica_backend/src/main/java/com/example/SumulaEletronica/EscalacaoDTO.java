package com.example.SumulaEletronica;

public class EscalacaoDTO {
	private long id;
	
	public static final EscalacaoDTO NULL_VALUE = new EscalacaoDTO(0);
	
	public EscalacaoDTO()
	{
		
	}

	public EscalacaoDTO(long id) {
		super();
		this.id = id;
	}

	public long getId() {
		return id;
	}
	
	
}
