package com.example.SumulaEletronica;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;

@Controller
public class SubstituicaoController 
{
	SubstituicaoRepository substituicaoRepository;
	SumulaController sumulaController;

	SubstituicaoController(SubstituicaoRepository substituicaoRepository, SumulaController sumulaController) 
	{
		this.substituicaoRepository = substituicaoRepository;
		this.sumulaController = sumulaController;
	}
		
	public SubstituicaoEntity getSubstituicaoEntityById(long id)
	{
		final Optional<SubstituicaoEntity> substituicaoEntity = this.substituicaoRepository.findById(id);
						
		if(substituicaoEntity.isPresent())
		{			
			return substituicaoEntity.get();
		}
						
		return toEntity(SubstituicaoDTO.NULL_VALUE);
	}
	
	public List<Long> saveSubstituicao(List<SubstituicaoCreateDTO> novasSubstituicoes)
	{
		List<Long> ids = new ArrayList<>();
		
		novasSubstituicoes.forEach(substituicaoDTO -> {
			ids.add(this.substituicaoRepository.save(toEntity(substituicaoDTO)).getId());
		});
				
		return ids;
	}
		
	private SubstituicaoEntity toEntity(SubstituicaoCreateDTO substituicaoDTO)
	{	
		if(this.sumulaController.getSumulaEntityById(substituicaoDTO.getSumula()).getId() == 0)
		{
			return new SubstituicaoEntity();
		}
		
		return new SubstituicaoEntity(substituicaoDTO.getId(), substituicaoDTO.getPeriodo(), substituicaoDTO.getTempo(), 
				substituicaoDTO.getEntra(), substituicaoDTO.getSai(), substituicaoDTO.isEquipeMandante(),				
				this.sumulaController.getSumulaEntityById(substituicaoDTO.getSumula()));
	}
	
	private SubstituicaoEntity toEntity(SubstituicaoDTO substituicaoDTO)
	{	
		return new SubstituicaoEntity(substituicaoDTO.getPeriodo(), substituicaoDTO.getTempo(), 
				substituicaoDTO.getEntra(), substituicaoDTO.getSai(), substituicaoDTO.isEquipeMandante(),				
				substituicaoDTO.getId());
	}
}
