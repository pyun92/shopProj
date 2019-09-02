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
	List<Report> selectReport(int num);
}
