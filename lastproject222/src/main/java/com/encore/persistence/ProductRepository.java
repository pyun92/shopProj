package com.encore.persistence;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.encore.domain.Product;
import com.encore.domain.Userdata;
import java.lang.Long;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

	List<Product> findByStoreseq(Long searchKeyword);
	
	Optional<Product> findByProductseq(Long productseq);//상품시퀀스로 하나씩받아오기
	
	@Query(value ="select * from bucket where productseq=(select productseq from product where storeseq=1) ",nativeQuery = true)
	void cancelbucket(Long num);
	
}
