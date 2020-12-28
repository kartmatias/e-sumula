package com.example.SumulaEletronica;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;

@Controller
public class RelacaoController {
	
	RelacaoRepository relacaoRepository;
	AtletaController atletaController;
	EscalacaoController escalacaoController;

	
	RelacaoController(RelacaoRepository relacaoRepository, AtletaController atletaController,
			EscalacaoController escalacaoController) 
	{
		this.relacaoRepository = relacaoRepository;
		this.atletaController = atletaController;
		this.escalacaoController = escalacaoController;
	}

	public RelacaoEntity getRelacaoEntityById(long id)
	{
		final Optional<RelacaoEntity> relacaoEntity = this.relacaoRepository.findById(id);
						
		if(relacaoEntity.isPresent())
		{			
			return relacaoEntity.get();
		}
						
		return toEntity(RelacaoDTO.NULL_VALUE);
	}
	
	public List<Long> saveRelacoes(List<RelacaoDTO> novasRelacoes)
	{
		List<Long> ids = new ArrayList<>();
		
		novasRelacoes.forEach(relacaoDTO ->{
			ids.add(this.relacaoRepository.save(toEntity(relacaoDTO)).getId());
		});		
		
		return ids;		
	}
		
	private RelacaoEntity toEntity(RelacaoDTO relacaoDTO)
	{	
		if(this.escalacaoController.getEscalacaoEntityById(relacaoDTO.getEscalacao()).getId() == 0 ||
				this.atletaController.getAtletaEntityById(relacaoDTO.getAtleta()).getId() == 0)
			return new RelacaoEntity();
		
		return new RelacaoEntity(relacaoDTO.getId(), relacaoDTO.getNumero(), relacaoDTO.getGol(), relacaoDTO.getTitular(),
				relacaoDTO.getCartoes(), this.atletaController.getAtletaEntityById(relacaoDTO.getAtleta()),
				this.escalacaoController.getEscalacaoEntityById(relacaoDTO.getEscalacao()));
	}
}
