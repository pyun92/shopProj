package com.encore.service;

import java.util.List;

import com.encore.domain.Advertising;

public interface AdvertisingService {

	List<Advertising> findAll();
	void insertAD(Advertising ad);
}
