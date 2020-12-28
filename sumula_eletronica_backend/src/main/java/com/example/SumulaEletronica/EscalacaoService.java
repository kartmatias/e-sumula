package com.example.SumulaEletronica;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/escalacao")
public class EscalacaoService 
{
	EscalacaoController escalacaoController;
	AuthenticationController authController;
	
	@Autowired
	private HttpServletRequest request;

	EscalacaoService(EscalacaoController escalacaoController, AuthenticationController authController) 
	{
		this.escalacaoController = escalacaoController;
		this.authController = authController;
	}
	
	@RequestMapping(value = "/post", method = RequestMethod.POST)
	public ResponseEntity<Long> novaEscalacao(@RequestBody EscalacaoDTO novaEscalacao) 
	{		
		if(this.authController.verificaToken(request) == AuthenticationDTO.NULL_VALUE)
		{
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<>(this.escalacaoController.saveEscalacao(novaEscalacao), HttpStatus.OK);
	}
	

}
