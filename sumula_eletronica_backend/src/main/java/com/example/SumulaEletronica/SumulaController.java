package com.example.SumulaEletronica;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Controller;

@Controller
public class SumulaController 
{
	SumulaRepository sumulaRepository;
	AtletaController atletaController;
	ClubeController clubeController;
	ArbitroController arbitroController;
	CronologiaController cronologiaController;
	ComissaoController comissaoController;
	EscalacaoController escalacaoController;
		
	
	public SumulaController(SumulaRepository sumulaRepository, AtletaController atletaController,
			ClubeController clubeController, ArbitroController arbitroController,
			CronologiaController cronologiaController, ComissaoController comissaoController,
			EscalacaoController escalacaoController) 
	{
		this.sumulaRepository = sumulaRepository;
		this.atletaController = atletaController;
		this.clubeController = clubeController;
		this.arbitroController = arbitroController;
		this.cronologiaController = cronologiaController;
		this.comissaoController = comissaoController;
		this.escalacaoController = escalacaoController;
	}

	public SumulaDTO getSumulaById(long id)
	{
		final Optional<SumulaEntity> sumulaEntity = this.sumulaRepository.findById(id);
		
		if(sumulaEntity.isPresent())
		{			
			return toDTO(sumulaEntity.get());
		}
						
		return SumulaDTO.NULL_VALUE;
	}
	
	public SumulaEntity getSumulaEntityById(long id)
	{
		final Optional<SumulaEntity> sumulaEntity = this.sumulaRepository.findById(id);
		
		if(sumulaEntity.isPresent())
		{			
			return sumulaEntity.get();
		}
						
		return toEntity(SumulaDTO.NULL_VALUE);
	}
	
	public ArrayList<SumulaListDTO> list()
	{
		ArrayList<SumulaListDTO> listaSumulas = new ArrayList<SumulaListDTO>();
		
		this.sumulaRepository.findAll().forEach(sumulaEntity -> listaSumulas.add(toListDTO(sumulaEntity)));
		
		return listaSumulas;	
	}
	
	public long saveSumula(SumulaCreateDTO sumulaCreateDTO)
	{
		SumulaEntity novaSumula = toEntity(sumulaCreateDTO);
		
		if(checkObjects(novaSumula))
		{
			return this.sumulaRepository.save(novaSumula).getId();
		}
		else
		{
			return 0;
		}
	}
	
	private SumulaEntity toEntity(SumulaCreateDTO sumulaCreateDTO)
	{
		return new SumulaEntity(sumulaCreateDTO.getId(), sumulaCreateDTO.getData(), sumulaCreateDTO.getLocal(), 
				this.clubeController.getClubeEntityById(sumulaCreateDTO.getClubeMandante()), 
				this.clubeController.getClubeEntityById(sumulaCreateDTO.getClubeVisitante()),
				this.arbitroController.getArbitroEntityById(sumulaCreateDTO.getArbitro()), 
				this.arbitroController.getArbitroEntityById(sumulaCreateDTO.getAssistente1()), 
				this.arbitroController.getArbitroEntityById(sumulaCreateDTO.getAssistente2()), 
				this.cronologiaController.getCronologiaEntityById(sumulaCreateDTO.getCronologia()),
				this.comissaoController.getComissaoEntityById(sumulaCreateDTO.getComissaoMandante()), 
				this.comissaoController.getComissaoEntityById(sumulaCreateDTO.getComissaoVisitante()), 
				this.escalacaoController.getEscalacaoEntityById(sumulaCreateDTO.getEscalacaoMandante()), 
				this.escalacaoController.getEscalacaoEntityById(sumulaCreateDTO.getEscalacaoVisitante()), 
				sumulaCreateDTO.getRelatorioObservacoes(),
				sumulaCreateDTO.getRelatorioExpulsoes());				
	}
	
	private SumulaEntity toEntity(SumulaDTO sumulaDTO)
	{
		return new SumulaEntity(sumulaDTO.getId(), sumulaDTO.getData(), sumulaDTO.getLocal(),
				sumulaDTO.getClubeMandante(), sumulaDTO.getClubeVisitante(), sumulaDTO.getArbitro(),
				sumulaDTO.getAssistente1(), sumulaDTO.getAssistente2(), sumulaDTO.getCronologia(), 
				sumulaDTO.getComissaoMandante(), sumulaDTO.getComissaoVisitante(), sumulaDTO.getEscalacaoMandante(),
				sumulaDTO.getEscalacaoVisitante(), sumulaDTO.getRelatorioObservacoes(), sumulaDTO.getRelatorioExpulsoes());				
	}
	
	private boolean checkObjects(SumulaEntity sumula)
	{
		if(sumula.getClubeMandante().getId() == 0 ||
				sumula.getClubeVisitante().getId() == 0 ||
				sumula.getArbitro().getId() == 0 ||
				sumula.getAssistente1().getId() == 0||
				sumula.getAssistente2().getId() == 0 ||
				sumula.getCronologia().getId() == 0 ||
				sumula.getEscalacaoMandante().getId() == 0 ||
				sumula.getEscalacaoVisitante().getId() == 0)
			return false;
			
		return true;
	}
	
	
	private SumulaDTO toDTO(SumulaEntity sumulaEntity)
	{
		return new SumulaDTO(sumulaEntity.getId(), sumulaEntity.getData(), sumulaEntity.getLocal(), sumulaEntity.getClubeMandante()
				, sumulaEntity.getClubeVisitante(),
				sumulaEntity.getArbitro(), sumulaEntity.getAssistente1(), sumulaEntity.getAssistente2(), 
				sumulaEntity.getCronologia(),
				sumulaEntity.getComissaoMandante(), sumulaEntity.getComissaoVisitante(), sumulaEntity.getSubstituicoes(),
				sumulaEntity.getEscalacaoMandante(), sumulaEntity.getEscalacaoVisitante(), sumulaEntity.getRelatorioObservacoes(), 
				sumulaEntity.getRelatorioExpulsoes());
	}
	
	private SumulaListDTO toListDTO(SumulaEntity sumulaEntity)
	{
		return new SumulaListDTO(sumulaEntity.getId(), sumulaEntity.getClubeMandante(), sumulaEntity.getClubeVisitante(),
				sumulaEntity.getData());
	}
}
