package com.encore.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.encore.domain.Option;

@Repository
public interface OptionRepository extends CrudRepository<Option, Long> {

	Optional<Option> findByOptionseq(Long optionseq );
	List<Option> findByProductseq(Long productseq);
}
