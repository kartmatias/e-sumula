package com.example.SumulaEletronica;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity(name = "sumula")
public class SumulaEntity 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(length = 40)
	private String data, local;	
	
	@ManyToOne()
	private ClubeEntity clubeMandante;
	
	@ManyToOne()
	private ClubeEntity clubeVisitante;
	
	@ManyToOne()
	private ArbitroEntity arbitro;
	
	@ManyToOne()
	private ArbitroEntity assistente1;
	
	@ManyToOne()
	private ArbitroEntity assistente2;
	
	@OneToOne
	@JoinColumn(unique = false)
	private CronologiaEntity cronologia;
	
	@OneToOne
	@JoinColumn(unique = false)
	private  ComissaoEntity comissaoMandante;
	
	@OneToOne
	@JoinColumn(unique = false)
	private  ComissaoEntity comissaoVisitante;
	
	@OneToMany(mappedBy="sumula")  
    private List<SubstituicaoEntity> substituicoes = new ArrayList<>();
	
	@OneToOne
	@JoinColumn(unique = false)
	private  EscalacaoEntity escalacaoMandante;
	
	@OneToOne
	@JoinColumn(unique = false)
	private  EscalacaoEntity escalacaoVisitante;
	
	@Column(length = 800)
	private String relatorioObservacoes, relatorioExpulsoes;
	
	//Construtor Padrão
	public SumulaEntity() {
		
	}
	
	//Construtor List
	public SumulaEntity(long id, String data, String local) 
	{
		super();
		this.id = id;
		this.data = data;
		this.local = local;
	}
	
	//Construtor Completo
	public SumulaEntity(long id, String data, String local, ClubeEntity clubeMandante, ClubeEntity clubeVisitante,
			ArbitroEntity arbitro, ArbitroEntity assistente1, ArbitroEntity assistente2, CronologiaEntity cronologia,
			ComissaoEntity comissaoMandante, ComissaoEntity comissaoVisitante, List<SubstituicaoEntity> substituicoes,
			EscalacaoEntity escalacaoMandante, EscalacaoEntity escalacaoVisitante, String relatorioObservacoes,
			String relatorioExpulsoes) {
		super();
		this.id = id;
		this.data = data;
		this.local = local;
		this.clubeMandante = clubeMandante;
		this.clubeVisitante = clubeVisitante;
		this.arbitro = arbitro;
		this.assistente1 = assistente1;
		this.assistente2 = assistente2;
		this.cronologia = cronologia;
		this.comissaoMandante = comissaoMandante;
		this.comissaoVisitante = comissaoVisitante;
		this.substituicoes = substituicoes;
		this.escalacaoMandante = escalacaoMandante;
		this.escalacaoVisitante = escalacaoVisitante;
		this.relatorioObservacoes = relatorioObservacoes;
		this.relatorioExpulsoes = relatorioExpulsoes;
	}
	
	//Construtor Create
	public SumulaEntity(long id, String data, String local, ClubeEntity clubeMandante, ClubeEntity clubeVisitante,
			ArbitroEntity arbitro, ArbitroEntity assistente1, ArbitroEntity assistente2, CronologiaEntity cronologia,
			ComissaoEntity comissaoMandante, ComissaoEntity comissaoVisitante,
			EscalacaoEntity escalacaoMandante, EscalacaoEntity escalacaoVisitante, String relatorioObservacoes,
			String relatorioExpulsoes) {
		super();
		this.id = id;
		this.data = data;
		this.local = local;
		this.clubeMandante = clubeMandante;
		this.clubeVisitante = clubeVisitante;
		this.arbitro = arbitro;
		this.assistente1 = assistente1;
		this.assistente2 = assistente2;
		this.cronologia = cronologia;
		this.comissaoMandante = comissaoMandante;
		this.comissaoVisitante = comissaoVisitante;
		this.escalacaoMandante = escalacaoMandante;
		this.escalacaoVisitante = escalacaoVisitante;
		this.relatorioObservacoes = relatorioObservacoes;
		this.relatorioExpulsoes = relatorioExpulsoes;
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

	public String getLocal() {
		return local;
	}

	public ArbitroEntity getArbitro() {
		return arbitro;
	}


	public ArbitroEntity getAssistente1() {
		return assistente1;
	}

	public ArbitroEntity getAssistente2() {
		return assistente2;
	}


	public ComissaoEntity getComissaoMandante() {
		return comissaoMandante;
	}

	public ComissaoEntity getComissaoVisitante() {
		return comissaoVisitante;
	}
	
	public String getRelatorioObservacoes() {
		return relatorioObservacoes;
	}

	public String getRelatorioExpulsoes() {
		return relatorioExpulsoes;
	}

	public CronologiaEntity getCronologia() {
		return cronologia;
	}

	public List<SubstituicaoEntity> getSubstituicoes() {
		return substituicoes;
	}

	public EscalacaoEntity getEscalacaoMandante() {
		return escalacaoMandante;
	}

	public EscalacaoEntity getEscalacaoVisitante() {
		return escalacaoVisitante;
				
	}
	
	
	
}
