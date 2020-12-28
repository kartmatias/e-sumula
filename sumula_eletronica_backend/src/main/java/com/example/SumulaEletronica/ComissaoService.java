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
@RequestMapping("/comissao")
public class ComissaoService {
	
	ComissaoController comissaoController;
	AuthenticationController authController;
	
	@Autowired
	private HttpServletRequest request;

	ComissaoService(ComissaoController comissaoController, AuthenticationController authController) 
	{
		this.comissaoController = comissaoController;
		this.authController = authController;
	}
	
	@RequestMapping(value = "/post", method = RequestMethod.POST)
	public ResponseEntity<Long> novaComissao(@RequestBody ComissaoDTO novaComissao) 
	{		
		if(this.authController.verificaToken(request) == AuthenticationDTO.NULL_VALUE)
		{
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<>(this.comissaoController.saveComissao(novaComissao), HttpStatus.OK);
	}	
	

}
