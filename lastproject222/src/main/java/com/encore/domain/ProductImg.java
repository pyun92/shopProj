package com.encore.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
@Table(name = "productImg")
public class ProductImg {
	@Id
	@GeneratedValue
	private Long fno;
	private Long imgnum;
	private String fileName; // 저장할 파일
	private String fileUrl;
	private Long detailnum;//이미지 상세번호
	
	
}
