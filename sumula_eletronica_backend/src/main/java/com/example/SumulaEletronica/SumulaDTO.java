package com.example.SumulaEletronica;

import java.util.List;


public class SumulaDTO 
{
	private final long id;
	private final String data;
	private String local;
	private ClubeEntity clubeMandante, clubeVisitante;
	private ArbitroEntity arbitro, assistente1, assistente2;
	private CronologiaEntity cronologia;
	private ComissaoEntity comissaoMandante, comissaoVisitante;
	private List<SubstituicaoEntity> substituicoes;
	private EscalacaoEntity escalacaoMandante, escalacaoVisitante;
	private String relatorioObservacoes, relatorioExpulsoes;
	
	public static final SumulaDTO NULL_VALUE = new SumulaDTO(0, "");
			
	public SumulaDTO(long id, String data) {
		super();
		this.id = id;
		this.data = data;
	}
	
	public SumulaDTO(long id, String data, long idClubeMandante, long IdClubeVisitante,
			long idArbitro, long idAssistente1, long idAssistente2, long idCronologia,
			long idComissaoMandante, long idComissaoVisitante, long idEscalacaoMandante, long idEscalacaoVisitante, 
			String relatorioObservacoes, String relatorioExpulsoes)
	{
		this.id = id;
		this.data = data;
		this.relatorioObservacoes = relatorioObservacoes;
		this.relatorioExpulsoes = relatorioExpulsoes;
		
	}

	public SumulaDTO(long id, String data, String local, ClubeEntity clubeMandante, ClubeEntity clubeVisitante,
			ArbitroEntity arbitro, ArbitroEntity assistente1, ArbitroEntity assistente2, CronologiaEntity cronologia,
			ComissaoEntity comissaoMandante, ComissaoEntity comissaoVisitante, List<SubstituicaoEntity> substituicoes,
			EscalacaoEntity escalacaoMandante, EscalacaoEntity escalacaoVisitante, String relatorioObservacoes, String relatorioExpulsoes) {
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

	public long getId() {
		return id;
	}

	public String getData() {
		return data;
	}

	public String getLocal() {
		return local;
	}

	public ClubeEntity getClubeMandante() {
		return clubeMandante;
	}

	public ClubeEntity getClubeVisitante() {
		return clubeVisitante;
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

	public CronologiaEntity getCronologia() {
		return cronologia;
	}

	public ComissaoEntity getComissaoMandante() {
		return comissaoMandante;
	}

	public ComissaoEntity getComissaoVisitante() {
		return comissaoVisitante;
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

	public String getRelatorioObservacoes() {
		return relatorioObservacoes;
	}

	public String getRelatorioExpulsoes() {
		return relatorioExpulsoes;
	}
	
	
	
}