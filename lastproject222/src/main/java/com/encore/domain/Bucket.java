package com.encore.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;


@Data
@Entity
public class Bucket {

	@Id
	@GeneratedValue
	private Long bucketseq;
	
	private Long bucketowner;
	
	private String imgname;
	
	private String bucketoption;
	
	private int quantity;
	
	private int price;
	
	private int discount;
	
	private int deliveryfee;
	
	private String sellername;
	
	
	
	
	
}
