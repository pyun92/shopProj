package com.encore.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.encore.domain.Report;
import java.lang.String;

public interface ReportRepository extends CrudRepository<Report, Long>{
	List<Report> findByReportdivi(int num);
	Optional<Report> findByReportnameAndReportdivi(String reportname,int divi);
}
