package com.example.SumulaEletronica;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository 
public interface AuthenticationRepository extends CrudRepository<AuthenticationEntity, Long> {
	
	Optional<AuthenticationEntity> findByToken(String token);

}
