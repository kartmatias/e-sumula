package com.example.SumulaEletronica;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

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
@RequestMapping("/arbitro")
public final class ArbitroService 
{
	ArbitroController arbitroController;
	AuthenticationController authController;
	
	@Autowired
	private HttpServletRequest request;
	
	ArbitroService(ArbitroController arbitroController, AuthenticationController authController)
	{
		this.arbitroController = arbitroController;
		this.authController = authController;
	}
		
	//@GetMapping("/list")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<ArbitroDTO>> list()
	{
		//System.out.println("Headers:");
		//System.out.println(authController.getToken(request));
		if(this.authController.verificaToken(request) == AuthenticationDTO.NULL_VALUE)
		{
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<>(this.arbitroController.list(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/list/assistentes", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<ArbitroDTO>> listAssistentes()
	{
		if(this.authController.verificaToken(request) == AuthenticationDTO.NULL_VALUE)
		{
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<>(this.arbitroController.listAssistentes(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<ArbitroDTO> getArbitros(@PathVariable long id) 	
	{
		if(this.authController.verificaToken(request) == AuthenticationDTO.NULL_VALUE)
		{
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		final ArbitroDTO arbitroDTO = this.arbitroController.getArbitroById(id);
		if(arbitroDTO == ArbitroDTO.NULL_VALUE)
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(arbitroDTO, HttpStatus.OK);
	}
				
	
	@RequestMapping(value = "/post", method = RequestMethod.POST)
	public ResponseEntity<Long> postArbitro(@RequestBody ArbitroDTO novoArbitro) 
	{		
		if(this.authController.verificaToken(request) == AuthenticationDTO.NULL_VALUE)
		{
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		return new ResponseEntity<>(this.arbitroController.saveArbitro(novoArbitro), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/put/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ArbitroDTO> replaceArbitro(@RequestBody ArbitroDTO novoArbitro, @PathVariable long id) 
	{	
		if(this.authController.verificaToken(request) == AuthenticationDTO.NULL_VALUE)
		{
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		final ArbitroDTO arbitroDTO = this.arbitroController.replaceArbitro(novoArbitro, id);
		
		if(arbitroDTO == ArbitroDTO.NULL_VALUE)
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(arbitroDTO, HttpStatus.OK);		
	}
}
