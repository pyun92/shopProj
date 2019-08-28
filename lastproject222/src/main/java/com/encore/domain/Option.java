package com.encore.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name ="options")
public class Option {
	
	@Id
	@GeneratedValue
	private Long optionseq;
	private String optioncontent;
	private String optionname;
	private String optionprice;
	private Long productseq;

}
