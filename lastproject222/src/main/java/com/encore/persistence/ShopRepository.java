package com.encore.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.encore.domain.Store;

@Repository
public interface ShopRepository extends CrudRepository<Store, Long> {

	Optional<Store> findByStorename(String data);
	Store findByStoreseq(Long storeseq);
	Optional<Store> findByStoreemail(String email);
}
