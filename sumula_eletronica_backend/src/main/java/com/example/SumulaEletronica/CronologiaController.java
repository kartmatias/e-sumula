package com.example.SumulaEletronica;

import java.util.Optional;

import org.springframework.stereotype.Controller;

@Controller
public class CronologiaController {
	
	CronologiaRepository cronologiaRepository;
	
	CronologiaController(CronologiaRepository cronologiaRepository)
	{
		this.cronologiaRepository = cronologiaRepository;
	}
	
	public CronologiaEntity getCronologiaEntityById(long id)
	{
		final Optional<CronologiaEntity> cronologiaEntity = this.cronologiaRepository.findById(id);
						
		if(cronologiaEntity.isPresent())
		{			
			return cronologiaEntity.get();
		}
						
		return toEntity(CronologiaDTO.NULL_VALUE);
	}
	
	public long saveCronologia(CronologiaDTO cronologiaDTO)
	{
		return this.cronologiaRepository.save(toEntity(cronologiaDTO)).getId();
	}
		
	protected CronologiaEntity toEntity(CronologiaDTO cronologiaDTO)
	{	
		return new CronologiaEntity(cronologiaDTO.getId(), cronologiaDTO.getEntradaMandante(), cronologiaDTO.getEntradaVisitante(), 
				cronologiaDTO.getInicioPartida(), cronologiaDTO.getFimPrimeiroTempo(), cronologiaDTO.getRetornoMandante(),
				cronologiaDTO.getRetornoVisitante(), cronologiaDTO.getReinicioParida(), cronologiaDTO.getFimSegundoTempo());
	}
}
