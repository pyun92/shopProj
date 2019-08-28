package com.encore.domain;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Store {

	@Id
	private Long storeseq;//주인아이디시퀀스들어감
	
	@Column(nullable = false)
	private String storename;//상점이름

	@Column(nullable = false)
	private String storedetail;//상점설명
	
	@Column(nullable = false)
	private String storetype;//상점업종
	
	@Column(nullable = false)
	private String storefile;//사업자등록증
	
	@Column(nullable = false)
	private String storeemail;//사업자이메일
	
	@Column(nullable = false)
	private Date requestdate;//글등록시간
	
	@Column(nullable = true)
	private Date confirmdate;
	
	
	
	
	
 	
}
