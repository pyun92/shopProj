package com.encore.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.encore.domain.Product;
import com.encore.domain.Report;
import com.encore.domain.Store;
import com.encore.domain.Userdata;
import com.encore.persistence.AdvertisingRepository;
import com.encore.persistence.LoginRepository;
import com.encore.persistence.ProductRepository;
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
	
	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public List<Store> selectStore() {
		return (List<Store>) shopRepository.findAll();
	}

	@Override
	public void updateRegister(Long seq) {
		Store oregisto=shopRepository.findByStoreseq(seq);
		//Store nregisto=new Store();
		oregisto.setConfirmdate(new SimpleDateFormat("yyyy/MM/dd").format(new Date()));//오늘날짜
//		nregisto.setStoreseq(oregisto.getStoreseq());//시퀀스
//		nregisto.setConfirmdate(new SimpleDateFormat("yyyy/MM/dd").format(new Date()));//오늘날짜
//		nregisto.setRequestdate(oregisto.getRequestdate());//신청날짜
//		nregisto.setStoredetail(oregisto.getStoredetail());//상세설명
//		nregisto.setStoreemail(oregisto.getStoreemail());//이메일주소
//		nregisto.setStorefile(oregisto.getStorefile());//file이름
//		nregisto.setStorename(oregisto.getStorename());//상점이름
//		nregisto.setStoretype(oregisto.getStoretype());//타입
		shopRepository.save(oregisto);
	}

	@Override
	public void updateLevel(Long seq) {
		Userdata ouser=loginRepository.findById(seq).get();
		ouser.setManage_level(1L);
		loginRepository.save(ouser);
	}

	@Override
	public List<Userdata> selectUser() {
		return (List<Userdata>)loginRepository.findAll();
	}


	//신고처리하고 날짜 변경하는거
	@Override
	public int updateConfirm(Long seq, int day) {
		Report re=reportRepository.findById(seq).get();
		/*
		 * Report newre=new Report(); newre.setReportseq(re.getReportseq());//시퀀스
		 * newre.setReportdate(re.getReportdate());//접수날짜
		 * newre.setReportdetail(re.getReportdetail());//상세내용
		 * newre.setReportname(re.getReportname());//상점인지사람인지
		 * newre.setReportsubject(re.getReportsubject());//주제
		 * newre.setProductseq(re.getProductseq());//상품시퀀스
		 */		//날짜계산
		Calendar c=Calendar.getInstance();
		c.setTime(new Date());
		if(day==15) {
			c.add(Calendar.DATE,15);
			re.setConfirmdate(new SimpleDateFormat("yyyy/MM/dd").format(c.getTime()));
		}else {
			c.add(Calendar.DATE,30);
			re.setConfirmdate(new SimpleDateFormat("yyyy/MM/dd").format(c.getTime()));
		}
		return (reportRepository.save(re).getReportseq()!=null)?1:0;
	}

	@Override
	public Report selectReportuser(String name) {
		System.out.println("받아온 이름"+name);
		Optional<Report> r=reportRepository.findByReportname(name);
		if(r.isPresent()) {
			System.out.println("객체존재o");
			return r.get();
		}
		return null;
		
	}

	@Override
	public List<Report> selectReport() {
		return (List<Report>) reportRepository.findAll();
	}

	@Override
	public List<Product> selectProductseq() {//여기추가
		List<Report> list=selectReport();
		List<Product> products=new ArrayList<Product>();
		for(int i=0;i<list.size();i++) {
			products.add(productRepository.findByProductseq(list.get(i).getProductseq()).get());
		}
		return products;
	}


	
}
