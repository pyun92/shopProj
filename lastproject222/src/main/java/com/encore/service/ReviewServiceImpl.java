package com.encore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.encore.domain.Product;
import com.encore.domain.Review;
import com.encore.persistence.ReviewRepository;

@Service
public class ReviewServiceImpl implements ReviewService {
	
	@Autowired
	private ReviewRepository rep;

	
	@Override
	public List<Review> findReview(Product prod) {
		List<Review> list=  rep.findByProductseq(prod.getProductseq());
		return list.size()==0?null:list;
	}
	
	
}
