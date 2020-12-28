package com.example.SumulaEletronica;

import java.util.ArrayList;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;

@Controller
public class LoginController {
	
	ArbitroRepository arbitroRepository;
	AuthenticationController authController;
	
	LoginController(ArbitroRepository arbitroRepository, AuthenticationController authController)
	{
		this.arbitroRepository = arbitroRepository;
		this.authController = authController;
	}
	
	public LoginDTO logaArbitro(LoginDTO arbitroLogin)
	{
		ArrayList<ArbitroDTO> listaArbitros = new ArrayList<ArbitroDTO>();		
		this.arbitroRepository.findAll().forEach(arbitroEntity -> listaArbitros.add(toDTO(arbitroEntity)));		
		
		for(ArbitroDTO arbitroEncontrado : listaArbitros)
		{
			if(arbitroEncontrado.getCpf().equals(arbitroLogin.getCpf()))
			{
				if(arbitroEncontrado.getSenha().equals(arbitroLogin.getSenha()))
				{
					if(arbitroEncontrado.getFuncao().equals("Arbitro"))
					{
						Random random = new Random();
						String token = String.valueOf(Long.toHexString(random.nextLong())) +
								String.valueOf(Long.toHexString(random.nextLong())) +
								String.valueOf(Long.toHexString(random.nextLong())) +
								String.valueOf(Long.toHexString(random.nextLong()));	
												
						arbitroLogin.setArbitro(removeSenha(arbitroEncontrado));
						arbitroLogin.setToken(token);
						arbitroLogin.setSenha("");
						
						this.authController.saveToken(arbitroLogin);
											
						return arbitroLogin;
					}
				}
			}
		}
		
		return LoginDTO.NULL_VALUE;
	}
	
	public LoginDTO logaArbitroPorToken(HttpServletRequest request)
	{
		AuthenticationDTO authDTO = this.authController.verificaToken(request);
		
		if(authDTO == AuthenticationDTO.NULL_VALUE)
		{
			return LoginDTO.NULL_VALUE;
		}
		
		return new LoginDTO("", "", authDTO.getToken(), authDTO.getArbitro());
	}
	
	public Boolean deslogaArbitro(String token)
	{
		return this.authController.logoffToken(token);
	}
		
	private ArbitroDTO toDTO(ArbitroEntity arbitroEntity)
	{	
		return new ArbitroDTO(arbitroEntity.getNome(), arbitroEntity.getSenha(), arbitroEntity.getCpf(), arbitroEntity.getDataNascimento(),
				arbitroEntity.getSexo(), arbitroEntity.getCategoria(), arbitroEntity.getFuncao(), arbitroEntity.getAltura(), arbitroEntity.getPeso(),
				arbitroEntity.getId());
	}
	
	private ArbitroDTO removeSenha(ArbitroDTO arbitroDTO)
	{
		ArbitroDTO arbitroSemSenha;
		
		arbitroSemSenha = new ArbitroDTO(arbitroDTO.getNome(), "", arbitroDTO.getCpf(), arbitroDTO.getDataNascimento(), 
				arbitroDTO.getSexo(), arbitroDTO.getCategoria(),
				arbitroDTO.getFuncao(), arbitroDTO.getAltura(), arbitroDTO.getPeso(), arbitroDTO.getId());
		
		return arbitroSemSenha;
	}
	

}
