package com.example.SumulaEletronica;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/atleta")
public final class AtletaService
{
	AtletaController atletaController;
	AuthenticationController authController;
	
	AtletaService(AtletaController atletaController, AuthenticationController authController)
	{
		this.atletaController = atletaController;
		this.authController = authController;
	}
	
	@Autowired
	private HttpServletRequest request;
			

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<AtletaDTO>> list()
	{
		if(this.authController.verificaToken(request) == AuthenticationDTO.NULL_VALUE)
		{
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		return new ResponseEntity<>( this.atletaController.list(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<AtletaDTO> getAtleta(@PathVariable long id) 
	{
		if(this.authController.verificaToken(request) == AuthenticationDTO.NULL_VALUE)
		{
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		final AtletaDTO atletaDTO = this.atletaController.getAtletaById(id);
		if(atletaDTO == AtletaDTO.NULL_VALUE)
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(atletaDTO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}/clube", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<AtletaDTO>> getAtletaByClube(@PathVariable long id)
	{
		if(this.authController.verificaToken(request) == AuthenticationDTO.NULL_VALUE)
		{
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<>(this.atletaController.findByClube(id), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/post", method = RequestMethod.POST)
	public ResponseEntity<Long> novoAtleta(@RequestBody AtletaDTO novoAtleta) 
	{		
		if(this.authController.verificaToken(request) == AuthenticationDTO.NULL_VALUE)
		{
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<>(this.atletaController.saveAtleta(novoAtleta), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/put/{id}", method = RequestMethod.PUT)
    public ResponseEntity<AtletaDTO> replaceAtleta(@RequestBody AtletaDTO novoAtleta, @PathVariable long id) 
	{	
		if(this.authController.verificaToken(request) == AuthenticationDTO.NULL_VALUE)
		{
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		final AtletaDTO atletaDTO = this.atletaController.replaceAtleta(novoAtleta, id);
		
		if(atletaDTO == AtletaDTO.NULL_VALUE)
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(atletaDTO, HttpStatus.OK);	
	}
	
}
