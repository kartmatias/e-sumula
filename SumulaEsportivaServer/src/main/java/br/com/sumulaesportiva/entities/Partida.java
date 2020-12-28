package br.com.sumulaesportiva.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Partida")
public class Partida implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;

	@ManyToOne
	@JoinColumn(name = "mandante_fk")
	private Equipe mandante;

	@ManyToOne
	@JoinColumn(name = "visitante_fk")
	private Equipe visitante;

	@ManyToOne
	@JoinColumn(name = "equipeArbitral_fk")
	private Equipe arbitral;

	@NotNull
	private String local;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Equipe getMandante() {
		return mandante;
	}

	public void setMandante(Equipe mandante) {
		this.mandante = mandante;
	}

	public Equipe getVisitante() {
		return visitante;
	}

	public void setVisitante(Equipe visitante) {
		this.visitante = visitante;
	}

	public Equipe getArbitral() {
		return arbitral;
	}

	public void setArbitral(Equipe arbitral) {
		this.arbitral = arbitral;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}


}
