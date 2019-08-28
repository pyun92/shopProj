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
public class ProductOrder {
	
	@Id
	@SequenceGenerator(name = "PRODUCTORDER", sequenceName = "PRODUCTORDER_SEQ", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "PRODUCTORDER")
	private Long orderseq;
	
	private Long userseq;
	
	private Long productseq;
	
	private String orderdetail;
	
	private String orderdate;
	
	private String totalmoney;
	
	private String orderstate;
	
}
