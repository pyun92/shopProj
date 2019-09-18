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
public class Timetable {
	
	@Id
	@SequenceGenerator(name = "TIMETABLE", sequenceName = "TIMETABLE_SEQ", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "TIMETABLE")
	private Long timeseq;
	
	private Long productseq;//상품이름이랑 정보가져오려고
	
	private Date time1;
	
	private Date time2;
	
	private String videourl;//동영상 url
}
