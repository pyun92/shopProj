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

	@Override
	public void deleteAD(Long num) {
		rep.deleteById(num);
		
	}

	@Override
	public void updateADstat(Long num) {
		Advertising ad1 = rep.findById(num).get();
		 ad1.setStatus("승인");
		 rep.save(ad1);
	}

	@Override
	public Advertising findAdvertising(Long num) {
		return rep.findById(num).get();
	}
	
	
	
}
