package com.encore.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.encore.domain.Bucket;
import com.encore.domain.ProductOrder;
import com.encore.domain.Review;
import com.encore.persistence.BucketRepository;
import com.encore.persistence.PagingRepository;
import com.encore.persistence.ProductOrderRepository;
import com.encore.persistence.ReviewRepository;

@Service
public class MyinfoServiceImpl implements MyinfoService{
	
	@Autowired
	private ProductOrderRepository por;
	
	@Autowired
	private BucketRepository buc;
	
	@Autowired
	private PagingRepository prs;
	
	@Autowired
	private ReviewRepository review;
	
	@Override
	public List<ProductOrder> orderlist(Long seq) {
		 List<ProductOrder> orderlist= por.findByUserseq(seq);
		
		return orderlist;
	}
	
	
	@Override
	public List<Bucket> jumoondetail(Long seq) {
	
		List<Bucket> jumoondetail = buc.findByOrderseq(seq);
		System.out.println(jumoondetail);
		return jumoondetail;
	}

	@Override
	public void cancel(Long seq) {
		buc.cancelbucket(seq);
		
	}
	
	@Override
	public void complete(Long seq) {
		buc.completebucket(seq);
		
	}
	
	@Override
	public void receive(Long seq) {
		buc.receivebucket(seq);
		
	}
	//판매자 물품 판매 내용
	@Override
	public Page<Bucket> jumoonmanager(Long num,Pageable pageable){
		Page<Bucket> list = buc.findByStoreseq(num,pageable);
		return list;
	}
	
	//주문 내용
	@Override
	public Page<ProductOrder> listpage(Long seq,Pageable pageable) {
		Page<ProductOrder> orderlist= prs.findByUserseq(seq, pageable);
		
		return orderlist;
	}


	@Override
	public List<Bucket> jumoonsize(Long num) {
		List<Bucket> jumoonsize = buc.findByStoreseq(num);
		return jumoonsize;
	}


	@Override
	public List<Bucket> ordercompleteList(Long seq) {
		System.out.println("userseq="+seq);
		return buc.orderComplete(seq);
	}


	@Override
	public Bucket reviewProduct(Long seq) {
		return buc.findByBucketseq(seq).get();
	}


	@Override
	public void reviewSave(Review r,Long seq) {
		Bucket b=buc.findByBucketseq(seq).get();
		b.setReviewcheck(1);
		buc.save(b);//리뷰상태바꾸기
		review.save(r);
	}


	@Override
	public List<Review> reviewList(Long seq) {
		return review.findByUserseq(seq);
	}


	@Override
	public void updateReview(Review r) {
		review.save(r);
	}

	@Override
	public Review review(Long reviewseq) {
		return review.findById(reviewseq).get();
	}


	@Override
	public void deleteReview(Long seq,Long buckseq) {
		Bucket b=buc.findByBucketseq(buckseq).get();
		b.setReviewcheck(0);
		buc.save(b);//리뷰상태바꾸기
		review.deleteById(seq);
		System.out.println("삭제!"+seq);
	}

}
