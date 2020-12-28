package com.example.SumulaEletronica;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity(name = "comissao")
public class ComissaoEntity 
{
	@Column(length = 50)
	private String tecnico, auxTecnico, prepGoleiro, medico, massagista;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@OneToOne(mappedBy = "comissaoMandante")
    private SumulaEntity sumulaMandante;
    @OneToOne(mappedBy = "comissaoVisitante")
    private SumulaEntity sumulaVisitante;
    
    public ComissaoEntity() {
    	
    }
    
    

	public ComissaoEntity(long id) {
		super();
		this.id = id;
	}



	public ComissaoEntity(String tecnico, String auxTecnico, String prepGoleiro, String medico, String massagista, long id) {
		super();
		this.tecnico = tecnico;
		this.auxTecnico = auxTecnico;
		this.prepGoleiro = prepGoleiro;
		this.medico = medico;
		this.massagista = massagista;
		this.id = id;
	}
	
	
	public long getId() {
		return id;
	}


	public String getTecnico() {
		return tecnico;
	}

	public String getAuxTecnico() {
		return auxTecnico;
	}

	public String getPrepGoleiro() {
		return prepGoleiro;
	}

	public String getMedico() {
		return medico;
	}

	public String getMassagista() {
		return massagista;
	}
	
	
}
