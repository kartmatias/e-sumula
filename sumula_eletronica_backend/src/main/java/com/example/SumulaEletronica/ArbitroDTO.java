package com.example.SumulaEletronica;

public final class ArbitroDTO 
{
	private final String nome, senha, cpf, dataNascimento, sexo, categoria, funcao;
	private final double altura, peso;
	private final long id;
	
	public static final ArbitroDTO NULL_VALUE = new ArbitroDTO("null", "", "", "", "", "", "", 0, 0, 0);
	
	public ArbitroDTO(String nome, String senha, String cpf, String dataNascimento, String sexo, String categoria,
			String funcao, double altura, double peso, long id) {
		super();
		this.id = id;
		this.nome = nome;
		this.senha = senha;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.sexo = sexo;
		this.categoria = categoria;
		this.funcao = funcao;
		this.altura = altura;
		this.peso = peso;
	}
	public String getNome() {
		return nome;
	}
	public String getSenha() {
		return senha;
	}
	public String getCpf() {
		return cpf;
	}
	public String getDataNascimento() {
		return dataNascimento;
	}
	public String getSexo() {
		return sexo;
	}
	public String getCategoria() {
		return categoria;
	}
	public String getFuncao() {
		return funcao;
	}
	public double getAltura() {
		return altura;
	}
	public double getPeso() {
		return peso;
	}
	public long getId() {
		return id;
	}
	
	
	
	
}
