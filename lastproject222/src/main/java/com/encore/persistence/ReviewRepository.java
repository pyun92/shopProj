package com.encore.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.encore.domain.Product;
import com.encore.domain.Review;
@Repository
public interface ReviewRepository extends CrudRepository<Review, Long>{
	
	List<Review> findByProductseq(Long productseq);
}
