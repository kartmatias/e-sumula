package br.com.sumulaesportiva.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Equipe")
public class Equipe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	private String nome;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "modalidade_fk")
	private Modalidade modalidade;

	@OneToMany(mappedBy = "equipe")
	private List<Pessoa> integrantes = new ArrayList<>();

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		this.Id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Modalidade getModalidade() {
		return modalidade;
	}

	public void setModalidade(Modalidade modalidade) {
		this.modalidade = modalidade;
	}

	public List<Pessoa> getIntegrantes() {
		return integrantes;
	}

	public void setIntegrantes(List<Pessoa> integrantes) {
		this.integrantes = integrantes;
	}

}
