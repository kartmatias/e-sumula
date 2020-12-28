package com.example.SumulaEletronica;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Controller;

@Controller
public class ClubeController 
{
	ClubeRepository clubeRepository;
	
	ClubeController(ClubeRepository clubeRepository)
	{
		this.clubeRepository = clubeRepository;
	}
	
	public ArrayList<ClubeDTO> list()
	{
		ArrayList<ClubeDTO> listaClubes = new ArrayList<ClubeDTO>();
		
		this.clubeRepository.findAll().forEach(clubeEntity -> listaClubes.add(toDTO(clubeEntity)));
		
		return listaClubes;	
	}
	
	public ClubeDTO getClubeById(long id)
	{
		final Optional<ClubeEntity> clubeEntity = this.clubeRepository.findById(id);
						
		if(clubeEntity.isPresent())
		{			
			return toDTO(clubeEntity.get());
		}
						
		return ClubeDTO.NULL_VALUE;
	}
	
	public ClubeEntity getClubeEntityById(long id)
	{
		final Optional<ClubeEntity> clubeEntity = this.clubeRepository.findById(id);
						
		if(clubeEntity.isPresent())
		{			
			return clubeEntity.get();
		}
						
		return toEntity(ClubeDTO.NULL_VALUE);
	}
	
	public long saveClube(ClubeDTO clubeDTO)
	{
		return toDTO(this.clubeRepository.save(toEntity(clubeDTO))).getId();
	}
	
	public ClubeDTO replaceClube(ClubeDTO clubeDTO, long id)
	{
		final Optional<ClubeEntity> clubeEntity = this.clubeRepository.findById(id);
		
		if(clubeEntity.isPresent())
		{
			return toDTO(this.clubeRepository.save(toEntity(clubeDTO)));
		}
		
		return ClubeDTO.NULL_VALUE;
	}
	
		
	protected ClubeDTO toDTO(ClubeEntity clubeEntity)
	{	
		return new ClubeDTO(clubeEntity.getId(), clubeEntity.getNome(), clubeEntity.getDataFundacao(),
				clubeEntity.getEstadio(), clubeEntity.getCep());
	}
	
	protected ClubeEntity toEntity(ClubeDTO clubeDTO)
	{	
		return new ClubeEntity(clubeDTO.getId(), clubeDTO.getNome(), clubeDTO.getDataFundacao(),
				clubeDTO.getEstadio(), clubeDTO.getCep());
	}
}
