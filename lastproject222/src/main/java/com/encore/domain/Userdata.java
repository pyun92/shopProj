package com.encore.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.hibernate.validator.constraints.UniqueElements;

import lombok.Data;

@Data
@Entity
public class Userdata {

	@Id
	@SequenceGenerator(name = "USERDATA", sequenceName = "USERDATA_SEQ", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "USERDATA")
	private Long userseq;
	
	@Column(unique = true)
	private String userid;
	
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private String username;
	
	@Column(nullable = false)
	private Long birthday;
	
	private String address1;
	
	private String address2;
	
	private String phone;
	
	private Long manage_level;
	
	
}
