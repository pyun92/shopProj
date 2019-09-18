package com.encore.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.encore.domain.Bucket;
import com.encore.domain.ProductOrder;

public interface MyinfoService {
	
	Page<ProductOrder> listpage(Long seq,Pageable pageable);
	
	List<ProductOrder> orderlist(Long seq);
	
	List<Bucket> jumoondetail(Long seq);
	
	void cancel(Long seq);
	
	void complete(Long seq);
	
	void receive(Long seq);
	
	Page<Bucket> jumoonmanager(Long num,Pageable pageable);
	
	List<Bucket> jumoonsize(Long num);
	
}
