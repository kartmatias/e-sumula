package com.example.SumulaEletronica;

public class CronologiaDTO {
	
	public static final CronologiaDTO NULL_VALUE = new CronologiaDTO(0, "", "", "", "", "", "", "", "");

	public static final String getEntradaM = null;
	
	private long id;
	
	private String entradaMandante, entradaVisitante, inicioPartida, fimPrimeiroTempo;
	private String retornoMandante, retornoVisitante, reinicioParida, fimSegundoTempo;
	
	public CronologiaDTO(long id, String entradaMandante, String entradaVisitante, String inicioPartida,
			String fimPrimeiroTempo, String retornoMandante, String retornoVisitante, String reinicioParida,
			String fimSegundoTempo) {
		super();
		this.id = id;
		this.entradaMandante = entradaMandante;
		this.entradaVisitante = entradaVisitante;
		this.inicioPartida = inicioPartida;
		this.fimPrimeiroTempo = fimPrimeiroTempo;
		this.retornoMandante = retornoMandante;
		this.retornoVisitante = retornoVisitante;
		this.reinicioParida = reinicioParida;
		this.fimSegundoTempo = fimSegundoTempo;
	}

	public long getId() {
		return id;
	}

	public String getEntradaMandante() {
		return entradaMandante;
	}

	public String getEntradaVisitante() {
		return entradaVisitante;
	}

	public String getInicioPartida() {
		return inicioPartida;
	}

	public String getFimPrimeiroTempo() {
		return fimPrimeiroTempo;
	}

	public String getRetornoMandante() {
		return retornoMandante;
	}

	public String getRetornoVisitante() {
		return retornoVisitante;
	}

	public String getReinicioParida() {
		return reinicioParida;
	}

	public String getFimSegundoTempo() {
		return fimSegundoTempo;
	}
	
	
	
}
