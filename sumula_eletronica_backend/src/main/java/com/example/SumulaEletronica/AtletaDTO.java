package com.example.SumulaEletronica;

public final class AtletaDTO 
{
	private final String nome, dataNascimento, cpf, bid, clube;
	private final long id, idClube;
	
	public static final AtletaDTO NULL_VALUE = new AtletaDTO (0, "null", "", "", "", "", 0);

	public AtletaDTO(long id, String nome, String dataNascimento, String cpf, String bid, String clube, long idClube) {
		super();
		this.id = id;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.cpf = cpf;
		this.bid = bid;
		this.clube = clube;
		this.idClube = idClube;
	}

	public String getNome() {
		return nome;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public String getCpf() {
		return cpf;
	}

	public String getBid() {
		return bid;
	}

	public String getClube() {
		return clube;
	}
	
	public long getId() {
		return id;
	}
	
	public long getIdClube() {
		return idClube;
	}
	
	
}
