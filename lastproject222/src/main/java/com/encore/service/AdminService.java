package com.encore.service;

import java.util.List;

import com.encore.domain.Report;
import com.encore.domain.Store;
import com.encore.domain.Userdata;

public interface AdminService {
	List<Store> selectStore();
	void updateRegister(Long seq); 
	void updateLevel(Long seq);
	List<Userdata> selectUser();
	List<Report> selectReport(int num);//회원,상점 구분하는거
	int updateConfirm(Long seq,int day);
	Report selectReportuser(String name,int divi);//사용자가 신고테이블에 있는지
}
