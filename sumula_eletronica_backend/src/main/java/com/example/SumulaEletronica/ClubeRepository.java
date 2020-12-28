package com.example.SumulaEletronica;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository 
public interface ClubeRepository extends CrudRepository<ClubeEntity, Long> {

}
