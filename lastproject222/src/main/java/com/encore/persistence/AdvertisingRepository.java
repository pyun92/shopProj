package com.encore.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.encore.domain.Advertising;

@Repository
public interface AdvertisingRepository extends CrudRepository<Advertising, Long>{

}
