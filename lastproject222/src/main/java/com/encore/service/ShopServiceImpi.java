package com.encore.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.encore.domain.Store;
import com.encore.persistence.ShopRepository;

@Service
public class ShopServiceImpi implements ShopService {

	@Autowired
	private ShopRepository repository;

	@Override
	public int namecheck(String data) {
		Optional<Store> findStoreName = repository.findByStorename(data);
		if (findStoreName.isPresent()) {
			System.out.println("이름체크가능");
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public void insertStore(Store store) {
		repository.save(store);
		System.out.println("데이터베이스 저장완료");
	}

	@Override
	public Store findbyid(Long seq) {
		Store store = repository.findByStoreseq(seq);
		return store;
	}

}
