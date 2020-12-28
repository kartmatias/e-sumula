package com.example.SumulaEletronica;

public class SumulaCreateDTO {
	
	private final long id;
	private final String data;
	private final String local;
	private final long clubeMandante, clubeVisitante, arbitro, assistente1, assistente2,
	cronologia, comissaoMandante, comissaoVisitante, escalacaoMandante, escalacaoVisitante;
	private final String relatorioObservacoes, relatorioExpulsoes;
	
	public SumulaCreateDTO(long id, String data, String local, long clubeMandante, long clubeVisitante, long arbitro,
			long assistente1, long assistente2, long cronologia, long comissaoMandante, long comissaoVisitante,
			long escalacaoMandante, long escalacaoVisitante, String relatorioObservacoes, String relatorioExpulsoes) {
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

	public String getData() {
		return data;
	}

	public String getLocal() {
		return local;
	}

	public long getClubeMandante() {
		return clubeMandante;
	}

	public long getClubeVisitante() {
		return clubeVisitante;
	}

	public long getArbitro() {
		return arbitro;
	}

	public long getAssistente1() {
		return assistente1;
	}

	public long getAssistente2() {
		return assistente2;
	}

	public long getCronologia() {
		return cronologia;
	}

	public long getComissaoMandante() {
		return comissaoMandante;
	}

	public long getComissaoVisitante() {
		return comissaoVisitante;
	}

	public long getEscalacaoMandante() {
		return escalacaoMandante;
	}

	public long getEscalacaoVisitante() {
		return escalacaoVisitante;
	}

	public String getRelatorioObservacoes() {
		return relatorioObservacoes;
	}

	public String getRelatorioExpulsoes() {
		return relatorioExpulsoes;
	}
	
	
	

}
