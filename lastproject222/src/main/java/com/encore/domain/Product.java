package com.encore.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Entity
@Table(name = "product")
public class Product {
	@Id
	@GeneratedValue
	private Long productseq;//num->seq변경
	private String Name;
	private int price;
	private int jaego;
	private String editor;
	private Long optionseq;
	private int discount;
	private String categorybig;
	private String categorysmall;
	
	private Long storeseq;   
}
