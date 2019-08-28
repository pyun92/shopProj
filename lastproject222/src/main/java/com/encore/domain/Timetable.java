package com.encore.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Timetable {
	
	@Id
	private Long timeseq;
	
	private Long productseq;//상품이름이랑 정보가져오려고
	
	private Date time1;
	
	private Date time2;
	
	private String videourl;//동영상 url
}
