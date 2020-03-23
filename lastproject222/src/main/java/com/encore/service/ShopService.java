package com.encore.service;

import java.util.List;

import com.encore.domain.Store;

public interface ShopService {
	
	int namecheck(String data);
	void insertStore(Store store);
	Store findbyid(Long seq);
<<<<<<< HEAD
	List<Store> AllstoreList();
=======
>>>>>>> master
}
