package com.encore.service;

import java.util.List;

import com.encore.domain.Option;

public interface OptionService {
	void insertOption(Option option);
	List<Option> selectOption(Long seq);//
}
