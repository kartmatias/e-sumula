package com.example.SumulaEletronica;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Controller;

@Controller
final class ArbitroController 
{
	ArbitroRepository arbitroRepository;
	
	ArbitroController(ArbitroRepository arbitroRepository)
	{
		this.arbitroRepository = arbitroRepository;
	}
	
	public ArrayList<ArbitroDTO> listAssistentes()
	{
		ArrayList<ArbitroDTO> listaArbitros = new ArrayList<ArbitroDTO>();
		
		this.arbitroRepository.findAll().forEach(arbitroEntity -> {			
			if(arbitroEntity.getFuncao().equals("Assistente"))
			{
				listaArbitros.add(toDTO(arbitroEntity));
			}
		});
		
		return listaArbitros;
		
	}
	
	public ArrayList<ArbitroDTO> list()
	{
		ArrayList<ArbitroDTO> listaArbitros = new ArrayList<ArbitroDTO>();
		
		this.arbitroRepository.findAll().forEach(arbitroEntity -> listaArbitros.add(toDTO(arbitroEntity)));
		
		return listaArbitros;
		
	}
	
	public ArbitroDTO getArbitroById(long id)
	{
		final Optional<ArbitroEntity> arbitroEntity = this.arbitroRepository.findById(id);
						
		if(arbitroEntity.isPresent())
		{			
			return toDTO(arbitroEntity.get());
		}
						
		return ArbitroDTO.NULL_VALUE;
	}
	
	public ArbitroEntity getArbitroEntityById(long id)
	{
		final Optional<ArbitroEntity> arbitroEntity = this.arbitroRepository.findById(id);
						
		if(arbitroEntity.isPresent())
		{			
			return arbitroEntity.get();
		}
						
		return toEntity(ArbitroDTO.NULL_VALUE);
	}
	
	public long saveArbitro(ArbitroDTO arbitroDTO)
	{
		return toDTO(this.arbitroRepository.save(toEntity(arbitroDTO))).getId();
	}
	
	public ArbitroDTO replaceArbitro(ArbitroDTO arbitroDTO, long id)
	{
		final Optional<ArbitroEntity> arbitroEntity = this.arbitroRepository.findById(id);
		if(arbitroEntity.isPresent())
		{
			return toDTO(this.arbitroRepository.save(toEntity(arbitroDTO)));
		}
		
		return ArbitroDTO.NULL_VALUE;
	}
	
	protected ArbitroDTO toDTO(ArbitroEntity arbitroEntity)
	{	
		return new ArbitroDTO(arbitroEntity.getNome(), arbitroEntity.getSenha(), arbitroEntity.getCpf(), arbitroEntity.getDataNascimento(),
				arbitroEntity.getSexo(), arbitroEntity.getCategoria(), arbitroEntity.getFuncao(), arbitroEntity.getAltura(), arbitroEntity.getPeso(),
				arbitroEntity.getId());
	}
	
	protected ArbitroEntity toEntity(ArbitroDTO arbitroDTO)
	{	
		return new ArbitroEntity(arbitroDTO.getNome(), arbitroDTO.getSenha(), arbitroDTO.getCpf(), arbitroDTO.getDataNascimento(),
				arbitroDTO.getSexo(), arbitroDTO.getCategoria(), arbitroDTO.getFuncao(), arbitroDTO.getAltura(), arbitroDTO.getPeso(),
				arbitroDTO.getId());
	}
}
