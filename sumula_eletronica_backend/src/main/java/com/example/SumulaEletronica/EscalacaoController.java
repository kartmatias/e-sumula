package com.example.SumulaEletronica;

import java.util.Optional;

import org.springframework.stereotype.Controller;

@Controller
public class EscalacaoController {
	
	EscalacaoRepository escalacaoRepository;

	EscalacaoController(EscalacaoRepository escalacaoRepository) 
	{
		this.escalacaoRepository = escalacaoRepository;
	}
	
	
	public EscalacaoEntity getEscalacaoEntityById(long id)
	{
		final Optional<EscalacaoEntity> escalacaoEntity = this.escalacaoRepository.findById(id);
						
		if(escalacaoEntity.isPresent())
		{			
			return escalacaoEntity.get();
		}
						
		return toEntity(EscalacaoDTO.NULL_VALUE);
	}
	
	public long saveEscalacao(EscalacaoDTO escalacaoDTO)
	{
		return this.escalacaoRepository.save(toEntity(escalacaoDTO)).getId();
	}
		
	protected EscalacaoEntity toEntity(EscalacaoDTO escalacaoDTO)
	{	
		return new EscalacaoEntity(escalacaoDTO.getId());
	}
	
}
