package com.encore.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.encore.domain.Bucket;

public interface  BucketRepository extends CrudRepository<Bucket, Long>{
	
	List<Bucket> findByUserseq(Long num);

}
