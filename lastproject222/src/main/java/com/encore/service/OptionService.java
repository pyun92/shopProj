package com.encore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;

import com.encore.domain.Option;

public interface OptionService {
	void insertOption(Option option);
	List<Option> selectOption(Long seq);//
	
	List<Option> findoption();
	
}
