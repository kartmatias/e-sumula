package com.example.SumulaEletronica;

import java.util.List;

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
@RequestMapping("/relacao")
public class RelacaoService 
{
	RelacaoController relacaoController;
	AuthenticationController authController;
	
	@Autowired
	private HttpServletRequest request;

	RelacaoService(RelacaoController relacaoController, AuthenticationController authController)
	{
		this.relacaoController = relacaoController;
		this.authController = authController;
	}
	
	@RequestMapping(value = "/post", method = RequestMethod.POST)
	public ResponseEntity<List<Long>> novaRelacao(@RequestBody List<RelacaoDTO> novasRelacoes) 
	{		
		if(this.authController.verificaToken(request) == AuthenticationDTO.NULL_VALUE)
		{
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<>(this.relacaoController.saveRelacoes(novasRelacoes), HttpStatus.OK);
	}	
	
	
}
