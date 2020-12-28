package com.example.SumulaEletronica;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginService 
{
	
	LoginController loginController;
	AuthenticationController authController;
	
	@Autowired
	private HttpServletRequest request;
	
	LoginService(LoginController loginController, AuthenticationController authController)
	{
		this.loginController = loginController;
		this.authController = authController;
	}
	
	@PostMapping("")
	public ResponseEntity<LoginDTO> logaArbitro(@RequestBody LoginDTO loginDTO) 
	{
		loginDTO = this.loginController.logaArbitro(loginDTO);
		
		if(loginDTO == LoginDTO.NULL_VALUE)
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(loginDTO, HttpStatus.OK);	
	}
	
	@GetMapping("/logoff")
	public ResponseEntity<Boolean> deslogaArbitro() 
	{
		//System.out.println("Token da requisição: " + this.authController.getToken(request));
		if(this.loginController.deslogaArbitro(this.authController.getToken(request)))
		{
			return new ResponseEntity<>(true, HttpStatus.OK);	
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/loginByToken")
	public ResponseEntity<LoginDTO> logaArbitroPorToken() 
	{
		//System.out.println("Token da requisição: " + this.authController.getToken(request));
		LoginDTO loginDTO = this.loginController.logaArbitroPorToken(request);
		
		if(loginDTO == LoginDTO.NULL_VALUE)
		{
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); //Unauthorized
		}
		
		return new ResponseEntity<LoginDTO>(loginDTO, HttpStatus.OK);	
	}

}
