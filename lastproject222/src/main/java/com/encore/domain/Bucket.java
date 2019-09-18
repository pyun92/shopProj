package com.encore.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.SequenceGenerators;
import javax.persistence.Table;

import lombok.Data;


@Data
@Entity
@Table(name="bucket")
public class Bucket {

	@Id
	@SequenceGenerator(name = "bucketseq", sequenceName = "BUCKET_SEQ", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "bucketseq")
	private Long bucketseq;

	private Long userseq;
	
	private String itemname;
	
	private String imgname;
	
	private Long productseq;
	
	private Long optionseq;   //선택 옵션 시퀀스
	
	private String bucketoption;   //선택 옵션 내용	
	
	private int quantity;
	
	private int price;
	
	private int discount;
	
	private int checked;        //장바구니 체크여부 
	
	private int deliveryfee;
	
	private String sellername;   //상점 이름
	
	private Long storeseq;        //상점 시퀀스 
	
	private String condition;     //결제,배송 상태 표시 
	
	private Long orderseq;       
	
	private String bucketdate;	
}
