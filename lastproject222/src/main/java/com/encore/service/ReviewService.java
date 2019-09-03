package com.encore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.encore.domain.Product;
import com.encore.domain.Review;

public interface ReviewService {
	List<Review> findReview(Product prod);

}
