package br.com.sumulaesportiva.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Tempo")
public class Tempo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	private int quantidadePeriodos;
	private int duracaoPeriodos;
	private int quantidadeTemposExtras;
	private int duracaoExtra;
	private int quantidadeTimeout;
	private int duracaoTimeout;

	@OneToOne(mappedBy = "tempo")
	private Modalidade modalidade;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		this.Id = id;
	}

	public int getQuantidadePeriodos() {
		return quantidadePeriodos;
	}

	public void setQuantidadePeriodos(int quantidadePeriodos) {
		this.quantidadePeriodos = quantidadePeriodos;
	}

	public int getDuracaoPeriodos() {
		return duracaoPeriodos;
	}

	public void setDuracaoPeriodos(int duracaoPeriodos) {
		this.duracaoPeriodos = duracaoPeriodos;
	}

	public int getQuantidadeTemposExtras() {
		return quantidadeTemposExtras;
	}

	public void setQuantidadeTemposExtras(int quantidadeTemposExtras) {
		this.quantidadeTemposExtras = quantidadeTemposExtras;
	}

	public int getDuracaoExtra() {
		return duracaoExtra;
	}

	public void setDuracaoExtra(int duracaoExtra) {
		this.duracaoExtra = duracaoExtra;
	}

	public int getQuantidadeTimeout() {
		return quantidadeTimeout;
	}

	public void setQuantidadeTimeout(int quantidadeTimeout) {
		this.quantidadeTimeout = quantidadeTimeout;
	}

	public int getDuracaoTimeout() {
		return duracaoTimeout;
	}

	public void setDuracaoTimeout(int duracaoTimeout) {
		this.duracaoTimeout = duracaoTimeout;
	}

	public Modalidade getModalidade() {
		return modalidade;
	}

	public void setModalidade(Modalidade modalidade) {
		this.modalidade = modalidade;
	}
}