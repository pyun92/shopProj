package com.encore.service;


import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
 

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.encore.domain.KaKao;
import com.encore.domain.ProductOrder;

import lombok.extern.java.Log;
 
@Service
public class KaKaoPay {
	 private static final String HOST = "https://kapi.kakao.com";
	    
	    private KaKao kakaoPayReadyVO;
	    
	    public String kakaoPayReady(ProductOrder order) {
	 
	        RestTemplate restTemplate = new RestTemplate();
	        
	        // 서버로 요청할 Header
	        HttpHeaders headers = new HttpHeaders();
	        headers.add("Authorization", "KakaoAK " + "242388b65644c20e2687e29b7f8cb428");
	        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
	        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");
	        
	        // 서버로 요청할 Body
	        MultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object >();
	        params.add("cid", "TC0ONETIME");
	        params.add("partner_order_id", "1001");
	        params.add("partner_user_id", "gorany");
	        params.add("item_name", order.getItemname());
	        params.add("quantity", "1");
	        params.add("total_amount", order.getCalprice());
	        params.add("tax_free_amount", "100");
	        params.add("approval_url", "http://192.168.113.60:8080/kakaoPaySuccess");
	        params.add("cancel_url", "http://192.168.113.60:8080/kakaoPayCancel");
	        params.add("fail_url", "http://192.168.113.60:8080/kakaoPaySuccessFail");
	 
	         HttpEntity<MultiValueMap<String, Object>> body = new HttpEntity<MultiValueMap<String, Object>>(params, headers);
	 
	        try {
	            kakaoPayReadyVO = restTemplate.postForObject(new URI(HOST + "/v1/payment/ready"), body, KaKao.class);
//	            kakaoPayReadyVO.setNext_redirect_pc_url("http://192.168.113.60:8080/");
	           // log.info("" + kakaoPayReadyVO);
	            
	            return kakaoPayReadyVO.getNext_redirect_pc_url();
//	            return "/kakaoPaySuccess";
	            
	        } catch (RestClientException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (URISyntaxException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        
	        return "/pay";
	        
	    }
	    
	
}
