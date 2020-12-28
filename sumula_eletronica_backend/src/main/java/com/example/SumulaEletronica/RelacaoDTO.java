package com.example.SumulaEletronica;


public class RelacaoDTO 
{
	public static final RelacaoDTO NULL_VALUE = new RelacaoDTO(0, 0, 0, "", "", 0, 0);
	
	private long id;	
	private int numero;
	private int gol;	
	private String titular;
	private String cartoes;	
	private final long atleta, escalacao;
		

	public RelacaoDTO(long id, int numero, int gol, String titular, String cartoes, long atleta, long escalacao) {
		super();
		this.id = id;
		this.numero = numero;
		this.gol = gol;
		this.titular = titular;
		this.cartoes = cartoes;
		this.atleta = atleta;
		this.escalacao = escalacao;
	}

	public long getId() {
		return id;
	}

	public int getNumero() {
		return numero;
	}

	public int getGol() {
		return gol;
	}

	public String getTitular() {
		return titular;
	}

	public String getCartoes() {
		return cartoes;
	}

	public static RelacaoDTO getNullValue() {
		return NULL_VALUE;
	}

	public long getAtleta() {
		return atleta;
	}

	public long getEscalacao() {
		return escalacao;
	}	
	
	
	
}
