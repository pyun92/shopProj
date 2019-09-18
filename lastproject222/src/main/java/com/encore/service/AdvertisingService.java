package com.encore.service;


import java.util.List;

import com.encore.domain.Advertising;
import com.encore.domain.Product;

public interface AdvertisingService {

	List<Advertising> findAll();
	void insertAD(Advertising ad);
	void deleteAD(Long num);
	void updateADstat(Long num);
	void updatebigAD(Long num);
	Advertising findAdvertising(Long num);
	List<Product> findAdProduct();
	void updateSmallAD(Long seq);
	
}
