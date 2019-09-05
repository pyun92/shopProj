package com.encore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.encore.domain.Bucket;
import com.encore.domain.ProductOrder;
import com.encore.persistence.BucketRepository;
import com.encore.persistence.ProductOrderRepository;

@Service
public class MyinfoServiceImpl implements MyinfoService{
	
	@Autowired
	private ProductOrderRepository por;
	
	@Autowired
	private BucketRepository buc;
	
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
		buc.cancelproduct_order(seq);
		
		
	}

	@Override
	public List<Bucket> jumoonmanager(Long num) {
		List<Bucket> list = buc.findByStoreseq(num);
		return list;
	}
	

	
	

}
