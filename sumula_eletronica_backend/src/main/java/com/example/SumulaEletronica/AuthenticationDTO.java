package com.example.SumulaEletronica;

import java.util.Date;

public class AuthenticationDTO {
	private final Long id;
	private final String token;
	private final Date expTime;
	private final boolean logoff;
	private final ArbitroDTO arbitro;
	
	public static final AuthenticationDTO NULL_VALUE = new AuthenticationDTO(0L, "", new Date(), true, null);
	
	
	public AuthenticationDTO(Long id, String token, Date expTime, boolean logoff, ArbitroDTO arbitro) {
		super();
		this.id = id;
		this.token = token;
		this.expTime = expTime;
		this.logoff = logoff;
		this.arbitro = arbitro;
	}

	public Long getId() {
		return id;
	}

	public String getToken() {
		return token;
	}

	public Date getExpTime() {
		return expTime;
	}

	public boolean isLogoff() {
		return logoff;
	}

	public ArbitroDTO getArbitro() {
		return arbitro;
	}
	
	
}
