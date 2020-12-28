package br.com.sumulaesportiva.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.com.sumulaesportiva.entities.Pessoa;

@RepositoryRestResource(collectionResourceRel = "pessoa", path = "pessoa")
public interface PessoaRepository extends PagingAndSortingRepository<Pessoa, Long> {

}
