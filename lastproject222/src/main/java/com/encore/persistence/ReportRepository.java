package com.encore.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.encore.domain.Report;

public interface ReportRepository extends CrudRepository<Report, Long>{
	List<Report> findByReportdivi(int num);
}
