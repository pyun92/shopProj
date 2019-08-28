package com.encore.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
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
	@SequenceGenerator(name = "bucketseq", sequenceName = "TEAM_SEQ", initialValue = 1, allocationSize = 1)
	private Long bucketseq;

	private Long userseq;
	
	private String imgname;
	
	private String bucketoption;
	
	private int quantity;
	
	private int price;
	
	private int discount;
	
	private int deliveryfee;
	
	private String sellername;
	
	
	
	
	
}
