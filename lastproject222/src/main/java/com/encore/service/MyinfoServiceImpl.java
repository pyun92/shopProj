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
import com.encore.persistence.BucketRepository;
import com.encore.persistence.PagingRepository;
import com.encore.persistence.ProductOrderRepository;

@Service
public class MyinfoServiceImpl implements MyinfoService{
	
	@Autowired
	private ProductOrderRepository por;
	
	@Autowired
	private BucketRepository buc;
	
	@Autowired
	private PagingRepository prs;
	
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
	

	
	

}
