package com.encore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.encore.domain.Advertising;
import com.encore.persistence.AdvertisingRepository;

@Service
public class AdvertisingServiceImpl implements AdvertisingService {

	@Autowired
	private AdvertisingRepository rep;

	@Override
	public List<Advertising> findAll() {
		List<Advertising> list=(List<Advertising>)rep.findAll();
		return list;
	}

	@Override
	public void insertAD(Advertising ad) {
		rep.save(ad);
	}
	
	
	
}
