package com.encore.persistence;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.encore.domain.Store;

@Repository
public interface ShopRepository extends CrudRepository<Store, Long>{

	Optional<Store> findByStorename(String data);
	
}
