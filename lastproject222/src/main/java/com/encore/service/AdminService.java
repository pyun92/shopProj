package com.encore.service;

import java.util.List;

import com.encore.domain.Product;
import com.encore.domain.Report;
import com.encore.domain.Store;
import com.encore.domain.Userdata;

public interface AdminService {
	List<Store> selectStore();//상점리스트
	void updateRegister(Long seq); 
	void updateLevel(Long seq);
	List<Userdata> selectUser();
	List<Report> selectReport();
	List<Product> selectProductseq();//이걸로 받아온값 저장하기
	int updateConfirm(Long seq,int day);
	Report selectReportuser(String name);//사용자가 신고테이블에 있는지
	
}
