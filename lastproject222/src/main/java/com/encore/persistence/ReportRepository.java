package com.encore.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.encore.domain.Product;
import com.encore.domain.Report;
import com.encore.domain.Store;

import java.lang.String;

public interface ReportRepository extends CrudRepository<Report, Long>{
//	List<Report> findByReportdivi(int num);
	Optional<Report> findByReportname(String reportname);
	
	/* Optional<Product> findByStoreseq(Long seq); */
}
