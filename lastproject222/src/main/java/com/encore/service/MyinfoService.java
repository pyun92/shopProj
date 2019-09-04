package com.encore.service;

import java.util.List;

import com.encore.domain.ProductOrder;

public interface MyinfoService {

	List<ProductOrder> orderlist(Long seq);
	
}
