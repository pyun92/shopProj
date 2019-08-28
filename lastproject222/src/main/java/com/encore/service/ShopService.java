package com.encore.service;

import com.encore.domain.Store;

public interface ShopService {
	
	int namecheck(String data);
	void insertStore(Store store);
}
