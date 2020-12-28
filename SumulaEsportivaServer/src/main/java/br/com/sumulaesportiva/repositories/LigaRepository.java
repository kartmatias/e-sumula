package br.com.sumulaesportiva.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.com.sumulaesportiva.entities.Liga;

@RepositoryRestResource(collectionResourceRel = "liga", path = "liga")
public interface LigaRepository extends PagingAndSortingRepository<Liga, Long> {

}
