package com.encore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.support.SessionStatus;

import com.encore.domain.Userdata;
import com.encore.persistence.LoginRepository;


@Service
public class LoginDataServiceImpl implements LoginDataService {
	
	@Autowired
	private LoginRepository loginRepository;
	

	@Override
	public void insertId(Userdata data) {
		data.setManage_level(1L);
		loginRepository.save(data);
 }
	@Override
	public Userdata login(Userdata data) {
		
		Optional<Userdata> findMember = loginRepository.findByUserid(data.getUserid());
		if(findMember.isPresent())
			return findMember.get();
		else return null;
	
	
	}
	@Override
	public Userdata getuserdata(Userdata data) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int idcheck(String data) {

		Optional<Userdata> findMember = loginRepository.findByUserid(data);
		
		if(findMember.isPresent()) {
			return 1;
		}else {
			return 0;
		}
		
	}
	
}


