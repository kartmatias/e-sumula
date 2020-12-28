package com.example.SumulaEletronica;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name="auth")
public class AuthenticationEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(length = 200)
	private String token;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP) 
	private Date expTime;
	
	@Column
	private boolean logoff;
	
	@ManyToOne()
    private ArbitroEntity arbitro;
	
	public AuthenticationEntity() {
		super();
	}


	public AuthenticationEntity(Long id, String token, ArbitroEntity arbitro) {
		super();
		this.id = id;
		this.token = token;
		this.logoff = false;
		this.arbitro = arbitro;
		this.expTime = getDateTime();
	}


	public Date getExpTime() {
		return expTime;
	}


	public void setExpTime(Date expTime) {
		this.expTime = expTime;
	}


	public boolean isLogoff() {
		return logoff;
	}


	public void setLogoff(boolean logoff) {
		this.logoff = logoff;
	}


	public Long getId() {
		return id;
	}


	public String getToken() {
		return token;
	}
	
	
	private Date getDateTime()
	{		
		LocalDateTime localDateTime = LocalDateTime.now();
		Date date = Date.from( localDateTime.atZone( ZoneId.systemDefault()).toInstant());
		return date;
	}


	public ArbitroEntity getArbitro() {
		return arbitro;
	}
	
	
	
}
