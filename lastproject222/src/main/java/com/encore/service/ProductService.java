package com.encore.service;

import java.util.List;

import com.encore.domain.Bucket;
import com.encore.domain.Product;

public interface ProductService {
	void insertProd(Product prod);
	List<Product> getProdList(Long storeseq);
	List<Product> getProdListAll();
	Product getProd(Product prod);
	void insertBucket(Bucket bucket);
	List<Bucket> findallbucket(Long num);
	
	void quantitymodify(Bucket data);
}
