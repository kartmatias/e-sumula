package com.example.SumulaEletronica;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sumula")
public class SumulaService 
{
	
	SumulaController sumulaController;
	AuthenticationController authController;
	
	@Autowired
	private HttpServletRequest request;
	
	SumulaService(SumulaController sumulaController, AuthenticationController authController)
	{
		this.sumulaController = sumulaController;
		this.authController = authController;
	}
		
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<SumulaListDTO>> list()
	{
		if(this.authController.verificaToken(request) == AuthenticationDTO.NULL_VALUE)
		{
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<>(this.sumulaController.list(), HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<SumulaDTO> getSumula(@PathVariable int id) 
	{
		if(this.authController.verificaToken(request) == AuthenticationDTO.NULL_VALUE)
		{
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		final SumulaDTO sumulaDTO = this.sumulaController.getSumulaById(id);
		if(sumulaDTO == SumulaDTO.NULL_VALUE)
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(sumulaDTO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/post", method = RequestMethod.POST)
	public ResponseEntity<Long> novaSumula(@RequestBody SumulaCreateDTO novaSumula) 
	{	
		if(this.authController.verificaToken(request) == AuthenticationDTO.NULL_VALUE)
		{
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<>(this.sumulaController.saveSumula(novaSumula), HttpStatus.OK);
	}
}
