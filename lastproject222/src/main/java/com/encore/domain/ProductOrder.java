package com.encore.domain;

import java.sql.Date;

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
	private Long orderseq; // 장바구니에서  사용 함
	
	private Long userseq;
	
	private Long productseq;   // 장바구니 넘버 사용 
	
	private String orderdetail;
	
	private String orderdate;
	
	private String orderstate;
	
	//--------------------

	
	private String ordername;    //주문자 이름 으로 변경 하기 
	
	private String itemname;
	
	private String addressnum;
	
	private String address1;
	
	private String address2;
	
	private int totalprice;
	
	private int delivery;
	
	private int totaldis;
	
	private int calprice;
	
	private Date canceldate;
	
	private Date expectedtime;
	
	private int quantity; //필요 없을듯
	
	
	
}
