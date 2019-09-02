package com.encore.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.encore.domain.Report;
import com.encore.domain.Store;
import com.encore.domain.Userdata;
import com.encore.persistence.LoginRepository;
import com.encore.persistence.ReportRepository;
import com.encore.persistence.ShopRepository;

@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	private ShopRepository shopRepository;

	@Autowired
	private LoginRepository loginRepository;
	
	@Autowired
	private ReportRepository reportRepository;
	
	@Override
	public List<Store> selectStore() {
		return (List<Store>) shopRepository.findAll();
	}

	@Override
	public void updateRegister(Long seq) {
		Store oregisto=shopRepository.findByStoreseq(seq);
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

	@Override
	public void updateLevel(Long seq) {
		Userdata ouser=loginRepository.findById(seq).get();
		Userdata nuser=new Userdata();
		nuser.setAddress1(ouser.getAddress1());
		nuser.setAddress2(ouser.getAddress2());
		nuser.setBirthday(ouser.getBirthday());
		nuser.setManage_level(1L);
		nuser.setPassword(ouser.getPassword());
		nuser.setPhone(ouser.getPhone());
		nuser.setUserid(ouser.getUserid());
		nuser.setUsername(ouser.getUsername());
		nuser.setUserseq(ouser.getUserseq());
		loginRepository.save(nuser);
	}

	@Override
	public List<Userdata> selectUser() {
		return (List<Userdata>)loginRepository.findAll();
	}

	@Override
	public List<Report> selectReport(int num) {
		return reportRepository.findByReportdivi(num);
	}
	
	
	
}
