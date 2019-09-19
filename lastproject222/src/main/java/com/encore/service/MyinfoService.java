package com.encore.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.encore.domain.Bucket;
import com.encore.domain.ProductOrder;
import com.encore.domain.Review;

public interface MyinfoService {
	
Page<ProductOrder> listpage(Long seq,Pageable pageable);
	
	List<ProductOrder> orderlist(Long seq);
	
	List<Bucket> jumoondetail(Long seq);
	
	void cancel(Long seq);
	
	void complete(Long seq);
	
	void receive(Long seq);
	
	Page<Bucket> jumoonmanager(Long num,Pageable pageable);
	
	List<Bucket> jumoonsize(Long num);
	
	List<Bucket> ordercompleteList(Long seq);
	
	Bucket reviewProduct(Long seq);
	
	void reviewSave(Review r,Long seq);
	
	List<Review> reviewList(Long seq);
	
	void updateReview(Review r);
	
	Review review(Long reviewseq);
	
	void deleteReview(Long seq);
}
