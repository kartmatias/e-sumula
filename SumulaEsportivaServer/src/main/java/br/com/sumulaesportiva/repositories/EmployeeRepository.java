package br.com.sumulaesportiva.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.com.sumulaesportiva.entities.Employee;

@RepositoryRestResource(collectionResourceRel = "employee", path = "employee")
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long> {

	Employee findByFirstName(String firstName);

	List<Employee> findByLastName(String lastName);
}