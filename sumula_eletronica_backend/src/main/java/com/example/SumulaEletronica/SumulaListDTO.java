package com.example.SumulaEletronica;

public class SumulaListDTO 
{
	private final long id;
	private final ClubeEntity clubeMandante, clubeVisitante;
	private final String data;
	
	public SumulaListDTO(long id, ClubeEntity clubeMandante, ClubeEntity clubeVisitante, String data)
	{
		this.id = id;
		this.clubeMandante = clubeMandante;
		this.clubeVisitante = clubeVisitante;
		this.data = data;
	}

	public long getId() {
		return id;
	}

	public ClubeEntity getClubeMandante() {
		return clubeMandante;
	}

	public ClubeEntity getClubeVisitante() {
		return clubeVisitante;
	}

	public String getData() {
		return data;
	}
	
	
}
