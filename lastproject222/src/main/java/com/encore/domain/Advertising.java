package com.encore.domain;

import java.util.Date;

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
@Table(name="advertising")
public class Advertising {

	@Id
	@SequenceGenerator(name = "ADVERTISING", sequenceName = "ADVERTISING_SEQ", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "ADVERTISING")
	private Long adseq;
	
	private int bigad;    //대문광고 선택인가(0-미신청,1신청,2-진행중 3-완료(3번은 일단 안 넣음))
	
	private int smallad;  //추천상품광고선택인가
	
	private Long prodseq;  //선택한 제품번호
	
	private String intro;	//광고등록할 때 쓴 소개글
	
	private String vidurl; //입력받은 url(or 동영상의 저장경로 주소)
	
	private String status; //승인,대기 상태
	
}
