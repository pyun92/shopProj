package com.encore.service;


import java.util.List;

import com.encore.domain.Advertising;

public interface AdvertisingService {

	List<Advertising> findAll();
	void insertAD(Advertising ad);
	void deleteAD(Long num);
	void updateADstat(Long num);
	Advertising findAdvertising(Long num);
	
}
