package com.encore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.encore.domain.Option;
import com.encore.persistence.OptionRepository;

@Service
public class OptionImplService implements OptionService {

	@Autowired
	private OptionRepository rep;

	@Override
	public void insertOption(Option option) {
		rep.save(option);
	}

	@Override
	public List<Option> selectOption(Long seq) {
		return rep.findByProductseq(seq);
	}
	
	

}
