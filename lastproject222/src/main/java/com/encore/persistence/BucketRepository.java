package com.encore.persistence;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.encore.domain.Bucket;

public interface  BucketRepository extends CrudRepository<Bucket, Long>{
	
	List<Bucket> findByUserseq(Long num);
	

	@Modifying
	@Transactional
	@Query(value ="update bucket set condition='payment', orderseq=?1 where condition='bucket' and checked=1 ",nativeQuery = true)
	void afterpayment(Long num);
}
