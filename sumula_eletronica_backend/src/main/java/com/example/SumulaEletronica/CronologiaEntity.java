package com.example.SumulaEletronica;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity(name = "cronologia")
public class CronologiaEntity 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(length = 40)
	private String entradaMandante, entradaVisitante, inicioPartida, fimPrimeiroTempo;
	@Column(length = 40)
	private String retornoMandante, retornoVisitante, reinicioParida, fimSegundoTempo;
	
	@OneToOne(mappedBy = "cronologia")
    private SumulaEntity sumulaCronologia;
	
	public CronologiaEntity() {
		
	}
	
	
	
	public CronologiaEntity(long id) {
		super();
		this.id = id;
	}



	public CronologiaEntity(long id, String entradaMandante, String entradaVisitante, String inicioPartida,
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
