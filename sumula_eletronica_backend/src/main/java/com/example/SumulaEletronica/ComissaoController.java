package com.example.SumulaEletronica;

import java.util.Optional;

import org.springframework.stereotype.Controller;

@Controller
public class ComissaoController {
	
	ComissaoRepository comissaoRepository;

	ComissaoController(ComissaoRepository comissaoRepository) 
	{
		this.comissaoRepository = comissaoRepository;
	}
	
	public ComissaoEntity getComissaoEntityById(long id)
	{
		final Optional<ComissaoEntity> comissaoEntity = this.comissaoRepository.findById(id);
						
		if(comissaoEntity.isPresent())
		{			
			return comissaoEntity.get();
		}
						
		return toEntity(ComissaoDTO.NULL_VALUE);
	}
	
	public long saveComissao(ComissaoDTO comissaoDTO)
	{
		return this.comissaoRepository.save(toEntity(comissaoDTO)).getId();
	}
		
	private ComissaoEntity toEntity(ComissaoDTO comissaDTO)
	{	
		return new ComissaoEntity(comissaDTO.getTecnico(), comissaDTO.getAuxTecnico(), comissaDTO.getPrepGoleiro(), 
				comissaDTO.getMedico(), comissaDTO.getMassagista(), comissaDTO.getId());
	}
}
