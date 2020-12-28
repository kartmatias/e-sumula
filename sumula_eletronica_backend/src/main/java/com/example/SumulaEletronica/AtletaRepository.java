package com.example.SumulaEletronica;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository 
public interface AtletaRepository extends CrudRepository<AtletaEntity, Long>{
		
	ArrayList<AtletaEntity> findByClubeId(Long id);
}
