package com.encore.service;


import java.util.List;

import com.encore.domain.Advertising;
import com.encore.domain.Product;
import com.encore.domain.ProductImg;
import com.encore.domain.Store;

public interface AdvertisingService {

	List<Product> findbigadprod();
	List<ProductImg> findbigadimg();
	List<Advertising> findAll();
	List<Store> findadstore();
	void insertAD(Advertising ad);
	void deleteAD(Long num);
	void updateADstat(Long num);
	Advertising findAdvertising(Long num);
	List<Product> findAdProduct();
	List<Advertising> findbigad();
	void updateSmallAD(Long seq);
	void cancleSmallAD(Long seq);
	void updatebigAD(Long num);
	void cancelbigAD(Long num);
}
