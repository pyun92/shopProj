package com.encore.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.encore.domain.Review;
@Repository
public interface ReviewRepository extends CrudRepository<Review, Long>{
	
	List<Review> findByProductseq(Long productseq);

	List<Review> findByUserseq(Long userseq);
	
	@Query(value ="select b from Review b where b.userseq=?1 and b.productseq=?2 and bucketseq=?3")
	Review findMyReview(Long proseq,Long userseq,Long bucseq); 
}
