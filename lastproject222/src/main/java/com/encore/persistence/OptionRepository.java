package com.encore.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.encore.domain.Option;

@Repository
public interface OptionRepository extends CrudRepository<Option, Long> {

	Optional<Option> findAllByOptionseq(Long optionseq );
	List<Option> findByProductseq(Long productseq);//상품번호받아오기
	Option findByOptionseq(Long num);
	
}
