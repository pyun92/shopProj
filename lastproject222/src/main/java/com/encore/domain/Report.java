package com.encore.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name ="report")
public class Report {
	@Id
	@SequenceGenerator(name = "reportseq", sequenceName = "REPORT_SEQ", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "reportseq")
	private Long reportseq;
	
	private String reportname;//상점 or 회원아이디
	
	private Long productseq;//

	private String reportsubject;//신고사유
	
	private String reportdetail;//신고내용
	
	private String reportdate;
	
	private String confirmdate;
	
}
