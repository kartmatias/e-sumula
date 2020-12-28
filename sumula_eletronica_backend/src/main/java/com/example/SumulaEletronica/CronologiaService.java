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
@RequestMapping("/cronologia")
public class CronologiaService {
	
	CronologiaController cronologiaController;
	AuthenticationController authController;
	
	@Autowired
	private HttpServletRequest request;

	CronologiaService(CronologiaController cronologiaController, AuthenticationController authController) 
	{
		this.cronologiaController = cronologiaController;
		this.authController = authController;
	}
	
	@RequestMapping(value = "/post", method = RequestMethod.POST)
	public ResponseEntity<Long> novoClube(@RequestBody CronologiaDTO novaCronologia) 
	{	
		if(this.authController.verificaToken(request) == AuthenticationDTO.NULL_VALUE)
		{
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<>(this.cronologiaController.saveCronologia(novaCronologia), HttpStatus.OK);
	}	
	
}
