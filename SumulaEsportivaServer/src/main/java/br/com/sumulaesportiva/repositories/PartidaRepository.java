package br.com.sumulaesportiva.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.com.sumulaesportiva.entities.Partida;

@RepositoryRestResource(collectionResourceRel = "partida", path = "partida")
public interface PartidaRepository extends PagingAndSortingRepository<Partida, Long> {

}
