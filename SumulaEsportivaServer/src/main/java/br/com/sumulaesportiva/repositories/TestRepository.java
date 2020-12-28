package br.com.sumulaesportiva.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.com.sumulaesportiva.entities.Test;

@RepositoryRestResource(collectionResourceRel = "test", path = "test")
public interface TestRepository extends PagingAndSortingRepository<Test, Long> {
	
	List<Test> findByName(@Param("name") String name);
	
}
