package com.encore.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Review {

	@Id
	private Long reviewseq;
	
	private Long productseq;
	
	private Long userseq;//session값에 저장된 작성자 아이디 넣기
	
	private String reviewdetail;//리뷰내용
	
	private Date reviewdate;
	
	private String reviewimg;//리뷰사진
	
}
