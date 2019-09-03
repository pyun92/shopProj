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
	
	private String bucketoption;
	
	private int quantity;
	
	private int price;
	
	private int discount;
	
	private int checked;
	
	private int deliveryfee;
	
	private String sellername;
	
	private String condition;
	
	private Long orderseq;
	
}
