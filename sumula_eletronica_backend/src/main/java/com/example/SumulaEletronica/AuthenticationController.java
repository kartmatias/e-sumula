package com.example.SumulaEletronica;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;

@Controller
public class AuthenticationController 
{
	private String token;
	
	private static final Integer TEMPO_TOKEN = 20; //Tempo de expiração do token, em minutos
	
	AuthenticationRepository authRepository;
	ArbitroController arbitroContoller;
			
	public AuthenticationController(AuthenticationRepository authRepository, ArbitroController arbitroContoller) {
		this.authRepository = authRepository;
		this.arbitroContoller = arbitroContoller;
	}
	
	public Long saveToken(LoginDTO login)
	{
		return authRepository.save(new AuthenticationEntity(null, login.getToken(), arbitroContoller.toEntity(login.getArbitro()))).getId();
	}
	
	public Long saveToken(AuthenticationEntity authEntity)
	{
		return authRepository.save(authEntity).getId();
	}
	
	public AuthenticationDTO verificaToken(HttpServletRequest request)
	{
		String token = getToken(request);
		System.out.println("Token Solicitado: " + token);
		
		final Optional<AuthenticationEntity> authEntity = this.authRepository.findByToken(removeBearer(token));
			
		if(authEntity.isPresent())
		{
			if(!authEntity.get().isLogoff()) //Verifica se o token não recebeu um logoff
			{
				if(verificaExpericaoToken(authEntity.get().getExpTime())) //Verifica se não expirou
				{
					AuthenticationEntity salvaAuthEntity = authEntity.get(); //Busca entidade
					salvaAuthEntity.setExpTime(this.agora()); //Renova o token
					
					this.saveToken(salvaAuthEntity); //Salva
					return toDTO(salvaAuthEntity);
				}
			}
		}
		
		return AuthenticationDTO.NULL_VALUE;
	}
	
	public Boolean logoffToken(String token)
	{
		System.out.println("Token para deslogar: " + token);
		final Optional<AuthenticationEntity> authEntity = this.authRepository.findByToken(removeBearer(token));
		
		if(authEntity.isPresent())
		{
			AuthenticationEntity saveAuthEntity = authEntity.get();
			System.out.println("Auth Entity encontrada: " + authEntity.get().toString());
			saveAuthEntity.setLogoff(true);
			saveToken(saveAuthEntity);
			return true;
		}
		
		System.out.println("Token não encontrado");
		
		
		return false;
	}

	public String getToken(HttpServletRequest request) 
	{
		return request.getHeader("authorization");
	}
	
	
	private String removeBearer(String token)
	{
		if(token != null)
		{
			Integer ponteiro = token.indexOf("Bearer ");
			
			if(ponteiro >= 0)
			{
				return token.substring(ponteiro + 7);
			}
		}
		
		return token;
	}
	
	private Date agora()
	{
		LocalDateTime localDateTime = LocalDateTime.now();
		Date dataAgora = Date.from( localDateTime.atZone( ZoneId.systemDefault()).toInstant());
		return dataAgora;
	}
	
	private boolean verificaExpericaoToken(Date date)
	{
		Date agora = this.agora();
		
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);

		gc.add(Calendar.MINUTE, TEMPO_TOKEN);
		date = gc.getTime();
		
		if(agora.after(date)) return false;
		
		return true;
	}
	
	protected AuthenticationDTO toDTO (AuthenticationEntity authEntity)
	{
		return new AuthenticationDTO(authEntity.getId(), authEntity.getToken(), authEntity.getExpTime(), authEntity.isLogoff(), 
				arbitroContoller.toDTO(authEntity.getArbitro()));
	}	
}
