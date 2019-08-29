package com.encore.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.encore.domain.Store;
import com.encore.persistence.ShopRepository;

@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	private ShopRepository shopRepository;

	@Override
	public List<Store> selectStore() {
		return (List<Store>) shopRepository.findAll();
	}

	@Override
	public void updateRegister(String email) {
		Optional<Store> list=shopRepository.findByStoreemail(email);
		Store oregisto=list.get();
		Store nregisto=new Store();
		nregisto.setStoreseq(oregisto.getStoreseq());//시퀀스
		nregisto.setConfirmdate(new SimpleDateFormat("yyyy/MM/dd").format(new Date()));//오늘날짜
		nregisto.setRequestdate(oregisto.getRequestdate());//신청날짜
		nregisto.setStoredetail(oregisto.getStoredetail());//상세설명
		nregisto.setStoreemail(oregisto.getStoreemail());//이메일주소
		nregisto.setStorefile(oregisto.getStorefile());//file이름
		nregisto.setStorename(oregisto.getStorename());//상점이름
		nregisto.setStoretype(oregisto.getStoretype());//타입
		shopRepository.save(nregisto);
	}
	
	
	
}
