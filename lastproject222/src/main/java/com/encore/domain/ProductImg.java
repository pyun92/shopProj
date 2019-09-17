package com.encore.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
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
	@SequenceGenerator(name = "PRODUCTIMG", sequenceName = "PRODUCTIMG_SEQ", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "PRODUCTIMG")
	private Long fno;
	private Long imgnum;
	private String fileName; // 저장할 파일
	private String fileOriginalName;//원래 파일 이름
	private String fileUrl;
	private Long detailnum;//이미지 상세번호
	
	
}
