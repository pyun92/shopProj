package com.encore.domain;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Data;

@Data
@Entity

public class Order {

	@Id
	@SequenceGenerator(name = "ORDER", sequenceName = "ORDER_SEQ", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "ORDER")
	private Long seq;
	
	private String itemsname; 
	
	private String storename;
	
	private String addressnum;
	
	private String address1;
	
	private String address2;
	
	private int totalpricae;
	
	private int delivery;
	
	private int totaldis;
	
	private int calprice;
	
	private Long userseq;
	
	private Long producseq;
	
	private Date orderdate;
	
	private Date canceldate;
	
	private Date expectedtime;
	
	private String condition;
	
	private int quantity;
}
