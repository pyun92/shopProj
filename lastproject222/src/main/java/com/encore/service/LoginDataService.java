package com.encore.service;

import com.encore.domain.Userdata;

public interface LoginDataService {

	void insertId(Userdata data);

	Userdata login(Userdata data);
	
	Userdata getuserdata(Userdata data);
	
	int idcheck(String data);
}