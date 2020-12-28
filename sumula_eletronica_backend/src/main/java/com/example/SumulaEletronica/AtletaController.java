package com.example.SumulaEletronica;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Controller;

@Controller
public class AtletaController 
{
	AtletaRepository atletaRepository;
	
	AtletaController(AtletaRepository atletaRepository)
	{
		this.atletaRepository = atletaRepository;
	}
	
	public ArrayList<AtletaDTO> findByClube(long idClube)
	{
		ArrayList<AtletaDTO> listaAtletas = new ArrayList<AtletaDTO>();
		this.atletaRepository.findByClubeId(idClube).forEach(arbitroEntity -> listaAtletas.add(toDTO(arbitroEntity)));
		return listaAtletas;
	}
	
	
	public ArrayList<AtletaDTO> list()
	{
		ArrayList<AtletaDTO> lsitaAtletas = new ArrayList<AtletaDTO>();
		
		this.atletaRepository.findAll().forEach(arbitroEntity -> lsitaAtletas.add(toDTO(arbitroEntity)));		
		
		return lsitaAtletas;	
	}
	
	public AtletaDTO getAtletaById(long id)
	{
		final Optional<AtletaEntity> atletaEntity = this.atletaRepository.findById(id);
						
		if(atletaEntity.isPresent())
		{			
			return toDTO(atletaEntity.get());
		}
						
		return AtletaDTO.NULL_VALUE;
	}
	
	public AtletaEntity getAtletaEntityById(long id)
	{
		final Optional<AtletaEntity> atletaEntity = this.atletaRepository.findById(id);
						
		if(atletaEntity.isPresent())
		{			
			return atletaEntity.get();
		}
						
		return toEntity(AtletaDTO.NULL_VALUE);
	}
	
	public long saveAtleta(AtletaDTO atletaDTO)
	{
		return toDTO(this.atletaRepository.save(toEntity(atletaDTO))).getId();
	}
	
	public AtletaDTO replaceAtleta(AtletaDTO atletaDTO, long id)
	{
		final Optional<AtletaEntity> atletaEntity = this.atletaRepository.findById(id);
		
		if(atletaEntity.isPresent())
		{
			return toDTO(this.atletaRepository.save(toEntity(atletaDTO)));
		}
		
		return AtletaDTO.NULL_VALUE;
	}
	
		
	private AtletaDTO toDTO(AtletaEntity atletaEntity)
	{	
		return new AtletaDTO(atletaEntity.getId(), atletaEntity.getNome(), atletaEntity.getDataNascimento(),
			atletaEntity.getCpf(), atletaEntity.getBid(), atletaEntity.getClube().getNome(), atletaEntity.getClube().getId());
		
	}
	
	private AtletaEntity toEntity(AtletaDTO atletaDTO)
	{	
		return new AtletaEntity(atletaDTO.getId(), atletaDTO.getNome(), atletaDTO.getDataNascimento(),
				atletaDTO.getCpf(), atletaDTO.getBid(), new ClubeEntity(atletaDTO.getIdClube(), "", "", "", ""));
	}
}
