package com.example.SumulaEletronica;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity(name="Escalacao")
public final class EscalacaoEntity 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@OneToOne(mappedBy = "escalacaoMandante")
    private SumulaEntity sumulaMandante;
	
	@OneToOne(mappedBy = "escalacaoVisitante")
    private SumulaEntity sumulaVisitante;
	
	@OneToMany(mappedBy="escalacao")  
    private List<RelacaoEntity> relacoes = new ArrayList<>();
	
	public EscalacaoEntity() {
		
	}


	public EscalacaoEntity(long id) {
		super();
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public List<RelacaoEntity> getRelacoes() {
		return relacoes;
	}
	
	
			
}
