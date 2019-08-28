package com.encore.persistence;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.encore.domain.Userdata;



public interface LoginRepository extends CrudRepository<Userdata, Long>{
	Optional<Userdata> findByUserid(String searchKeyword);
	
}

