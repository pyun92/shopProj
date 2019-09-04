package com.encore.persistence;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.encore.domain.Report;

public interface ReportRepository extends CrudRepository<Report, Long>{
//	List<Report> findByReportdivi(int num);
	Optional<Report> findByReportname(String reportname);
	
	/* Optional<Product> findByStoreseq(Long seq); */
}
