package com.encore.service;

import java.util.List;

import com.encore.domain.Bucket;
import com.encore.domain.Product;
import com.encore.domain.ProductOrder;

public interface ProductService {
	void insertProd(Product prod);
	
	List<Product> getProdList(Long storeseq);
	
	List<Product> getProdListAll();
	
	Product getProd(Product prod);
	
	void insertBucket(Bucket bucket);
	
	void delbucketlist(Long num);
	
	List<Bucket> findallbucket(Long num);
	
	void quantitymodify(Bucket data);
	
	void checked(Bucket data);
	
	void productorder(ProductOrder order);
	
	void afterpaymnet(Long seq,Long idnum);
	
	void deleteProduct(Long seq);
	Product getProd(Long seq);
}
