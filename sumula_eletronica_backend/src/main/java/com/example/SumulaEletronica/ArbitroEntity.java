package com.example.SumulaEletronica;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity(name = "arbitro")
public class ArbitroEntity {
	
	@Column(length = 50)
	private String nome;
	@Column(length = 20)
	private String senha;
	@Column(length = 12)
	private String cpf;
	@Column(length = 12)
	private String dataNascimento;
	@Column(length = 20)
	private String sexo;
	@Column(length = 20)
	private String categoria;
	@Column(length = 20)
	private String funcao;
	
	@Column
	private double altura;
	@Column
	private double peso;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@OneToMany(mappedBy="arbitro")  
    private List<SumulaEntity> sumulaArbitro = new ArrayList<>();
	
	@OneToMany(mappedBy="assistente1")  
    private List<SumulaEntity> sumulaAssistente1 = new ArrayList<>();
	
	@OneToMany(mappedBy="assistente2")  
    private List<SumulaEntity> sumulaAssistente2 = new ArrayList<>();

	
	@OneToMany(mappedBy="arbitro")  
    private List<AuthenticationEntity> auths = new ArrayList<>();
	
	public ArbitroEntity() {
		
	}
	
		
	public ArbitroEntity(long id) {
		super();
		this.id = id;
	}


	public ArbitroEntity(String nome, String senha, String cpf, String dataNascimento, String sexo, String categoria,
			String funcao, double altura, double peso, long id) {
		super();
		this.nome = nome;
		this.senha = senha;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.sexo = sexo;
		this.categoria = categoria;
		this.funcao = funcao;
		this.altura = altura;
		this.peso = peso;
		this.id = id;
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
