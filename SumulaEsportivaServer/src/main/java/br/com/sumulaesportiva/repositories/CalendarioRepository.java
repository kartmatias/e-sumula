package br.com.sumulaesportiva.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.com.sumulaesportiva.entities.Calendario;

@RepositoryRestResource(collectionResourceRel = "calendario", path = "calendario")
public interface CalendarioRepository extends PagingAndSortingRepository<Calendario, Long> {

}
