package com.encore.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name ="options")
public class Option {
	
	@Id
	@SequenceGenerator(name = "OPTION", sequenceName = "OPTION_SEQ", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "OPTION")
	private Long optionseq;
	private String optioncontent;
	private String optionname;
	private String optionprice;
	private Long productseq;

}
