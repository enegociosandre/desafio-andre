package com.alelo.api.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.alelo.api.entity.Stadium;

@RepositoryRestResource
public interface StadiumRepository extends CrudRepository<Stadium, Integer> {
	
	Stadium findByName(String name);

}
