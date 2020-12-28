package com.example.SumulaEletronica;

public class LoginDTO 
{
	private String cpf, senha;
	private String token;
	private ArbitroDTO arbitro;
	
	public static final LoginDTO NULL_VALUE = new LoginDTO("0", "0");
	
	public LoginDTO() {
	
	}

	public LoginDTO(String cpf, String senha, String token, ArbitroDTO arbitro) {
		super();
		this.cpf = cpf;
		this.senha = senha;
		this.token = token;
		this.arbitro = arbitro;
	}

	public LoginDTO(String cpf, String senha) {
		super();
		this.cpf = cpf;
		this.senha = senha;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public ArbitroDTO getArbitro() {
		return arbitro;
	}

	public void setArbitro(ArbitroDTO arbitro) {
		this.arbitro = arbitro;
	}

	public String getCpf() {
		return cpf;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
}
