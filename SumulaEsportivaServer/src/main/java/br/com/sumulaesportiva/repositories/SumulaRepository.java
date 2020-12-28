package br.com.sumulaesportiva.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.com.sumulaesportiva.entities.Sumula;

@RepositoryRestResource(collectionResourceRel = "sumula", path = "sumula")
public interface SumulaRepository extends PagingAndSortingRepository<Sumula, Long> {

}
