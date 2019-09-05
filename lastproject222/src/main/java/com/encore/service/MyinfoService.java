package com.encore.service;

import java.util.List;

import com.encore.domain.Bucket;
import com.encore.domain.ProductOrder;

public interface MyinfoService {

	List<ProductOrder> orderlist(Long seq);
	
	List<Bucket> jumoondetail(Long seq);
	
	void cancel(Long seq);
	
	List<Bucket> jumoonmanager(Long num);
	
	
}
