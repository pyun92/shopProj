package com.encore.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.validator.constraints.UniqueElements;

import lombok.Data;

@Data
@Entity
public class Userdata {

	@Id
	@GeneratedValue
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
