package com.encore.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.encore.domain.Store;

@Repository
public interface SearchStoreRepository extends JpaRepository<Store, Long>{

}
