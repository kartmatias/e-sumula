package br.com.sumulaesportiva.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "Modalidade")
public class Modalidade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	@NotNull
	private String descricao;

	@PrimaryKeyJoinColumn
	@Cascade(value = CascadeType.ALL)
	@OneToOne(orphanRemoval = true)
	private Tempo tempo;

	@Cascade(CascadeType.ALL)
	@OneToMany(mappedBy = "modalidade", orphanRemoval = true)
	private List<Ponto> tiposPonto;

	@Cascade(CascadeType.ALL)
	@OneToMany(mappedBy = "modalidade", orphanRemoval = true)
	private List<Punicao> tiposPunicao;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Tempo getTempo() {
		return tempo;
	}

	public void setTempo(Tempo tempo) {
		this.tempo = tempo;
	}

	public List<Ponto> getTiposPonto() {
		return tiposPonto;
	}

	public void setTiposPonto(List<Ponto> tiposPonto) {
		tiposPonto.forEach(tipoPonto -> tipoPonto.setModalidade(this));
		this.tiposPonto = tiposPonto;
	}

	public List<Punicao> getTiposPunicao() {
		return tiposPunicao;
	}

	public void setTiposPunicao(List<Punicao> tiposPunicao) {
		tiposPunicao.forEach(tipoPunicao -> tipoPunicao.setModalidade(this));
		this.tiposPunicao = tiposPunicao;
	}
}