package br.com.sumulaesportiva.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.com.sumulaesportiva.entities.Equipe;

@RepositoryRestResource(collectionResourceRel = "equipe", path = "equipe")
public interface EquipeRespository  extends PagingAndSortingRepository<Equipe, Long> {

}
