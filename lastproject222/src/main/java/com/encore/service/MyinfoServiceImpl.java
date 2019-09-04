package com.encore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.encore.domain.ProductOrder;
import com.encore.persistence.ProductOrderRepository;

@Service
public class MyinfoServiceImpl implements MyinfoService{
	
	@Autowired
	private ProductOrderRepository por;
	
	@Override
	public List<ProductOrder> orderlist(Long seq) {
		 List<ProductOrder> orderlist= por.findByUserseq(seq);
		
		return orderlist;
	}

}
