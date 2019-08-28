package com.encore.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Order {

	@Id
	@GeneratedValue
	private Long seq;
	
	private int quantity;
}
