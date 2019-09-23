package com.encore.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Review {

	@Id
	@SequenceGenerator(name = "REVIEW", sequenceName = "REVIEW_SEQ", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "REVIEW")
	private Long reviewseq;
	
	private Long productseq;
	
	private Long bucketseq;
	
	private String userid;//session값에 저장된 작성자 아이디 넣기
	
	private String reviewdetail;//리뷰내용
	
	private String reviewdate;
	
	private String reviewimg;//리뷰사진
	
}
