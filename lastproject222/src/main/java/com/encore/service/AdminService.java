package com.encore.service;

import java.util.List;

import com.encore.domain.Store;

public interface AdminService {
	List<Store> selectStore();
	void updateRegister(String email); 
	void updateLevel(Long seq);
}
