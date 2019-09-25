package com.encore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.encore.domain.Product;
import com.encore.domain.Report;
import com.encore.domain.Review;
import com.encore.persistence.ReportRepository;
import com.encore.persistence.ReviewRepository;

@Service
public class ReviewServiceImpl implements ReviewService {
	
	@Autowired
	private ReviewRepository rep;

	@Autowired
	private ReportRepository reportrep;
	
	@Override
	public List<Review> findReview(Product prod) {
		List<Review> list=  rep.findByProductseq(prod.getProductseq());
		//System.out.println(list.get(0).getReviewdetail());
		return list.size()==0?null:list;
	}


	@Override
	public void insertReport(Report report) {
		reportrep.save(report);
	}
	
	
}
