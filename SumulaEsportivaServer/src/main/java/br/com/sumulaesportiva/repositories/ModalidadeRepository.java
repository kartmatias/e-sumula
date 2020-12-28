package br.com.sumulaesportiva.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.com.sumulaesportiva.entities.Modalidade;

@RepositoryRestResource(collectionResourceRel = "modalidade", path = "modalidade")
public interface ModalidadeRepository extends PagingAndSortingRepository<Modalidade, Long> {

}