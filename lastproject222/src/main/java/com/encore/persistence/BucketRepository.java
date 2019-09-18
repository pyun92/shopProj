package com.encore.persistence;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.encore.domain.Bucket;

public interface  BucketRepository extends CrudRepository<Bucket, Long>{
	
	List<Bucket> findByUserseq(Long num);
	
	List<Bucket> findByOrderseq(Long num);
	
	
	@Modifying
	@Transactional
	@Query(value ="update bucket set condition='payment', bucketdate=?1 , orderseq=?2 where condition='bucket' and checked=1 and userseq=?3",nativeQuery = true)
	void afterpayment(String date,Long num,Long idnum);
	
	@Modifying
	@Transactional
	@Query(value ="update product_order set orderstate='주문취소',canceldate=sysdate where orderseq=?1",nativeQuery = true)
	void cancelproduct_order(Long num);
	
	@Modifying
	@Transactional
	@Query(value ="update bucket set condition='cancel' where bucketseq=?1",nativeQuery = true)
	void cancelbucket(Long num);
	
	@Modifying
	@Transactional
	@Query(value ="update bucket set condition='complete' where bucketseq=?1",nativeQuery = true)
	void completebucket(Long num);
	
	@Modifying
	@Transactional
	@Query(value ="update bucket set condition='receive' where bucketseq=?1",nativeQuery = true)
	void receivebucket(Long num);
	
	Page<Bucket> findByStoreseq(Long seq,Pageable pageable);
	
	List<Bucket> findByStoreseq(Long seq);
}
