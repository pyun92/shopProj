package com.encore.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.encore.domain.Product;
import com.encore.domain.Userdata;
import java.lang.Long;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

	List<Product> findByStoreseq(Long searchKeyword);
	
	Optional<Product> findByProductseq(Long productseq);//상품시퀀스로 하나씩받아오기

	
}
