package com.example.SumulaEletronica;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clube")
public final class ClubeService 
{
	ClubeController clubeController;
	AuthenticationController authController;
	
	@Autowired
	private HttpServletRequest request;
	
	ClubeService(ClubeController clubeController, AuthenticationController authController)
	{
		this.clubeController = clubeController;
		this.authController = authController;
	}
		
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<ClubeDTO>> list()
	{
		if(this.authController.verificaToken(request) == AuthenticationDTO.NULL_VALUE)
		{
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<>(this.clubeController.list(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<ClubeDTO> getProduct(@PathVariable long id) 
	{
		if(this.authController.verificaToken(request) == AuthenticationDTO.NULL_VALUE)
		{
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		final ClubeDTO clubeDTO = this.clubeController.getClubeById(id);
		if(clubeDTO == ClubeDTO.NULL_VALUE)
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(clubeDTO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/post", method = RequestMethod.POST)
	public ResponseEntity<Long> novoClube(@RequestBody ClubeDTO novoClube) 
	{		
		if(this.authController.verificaToken(request) == AuthenticationDTO.NULL_VALUE)
		{
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

		return new ResponseEntity<>(this.clubeController.saveClube(novoClube), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/put/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ClubeDTO> replaceClube(@RequestBody ClubeDTO novoClube, @PathVariable long id) 
	{	
		if(this.authController.verificaToken(request) == AuthenticationDTO.NULL_VALUE)
		{
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		final ClubeDTO clubeDTO = this.clubeController.replaceClube(novoClube, id);
		
		if(clubeDTO == ClubeDTO.NULL_VALUE)
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(clubeDTO, HttpStatus.OK);	
	}
}
