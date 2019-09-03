package com.encore.controller;




import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.encore.domain.Bucket;
import com.encore.domain.ProductOrder;
import com.encore.domain.Userdata;
import com.encore.service.KaKaoPay;
import com.encore.service.ProductService;

import lombok.Setter;
import lombok.extern.java.Log;


@SessionAttributes("data")
@Log
@Controller
public class KaKaoController {
		@Autowired
		private ProductService service;
	
	  @Setter(onMethod_ = @Autowired)
	    private KaKaoPay kakaopay;
	    
	    
	    
	    @PostMapping("/kakaoPay")
	    public String kakaoPay(HttpServletRequest request,ProductOrder order,@ModelAttribute("data") Userdata user,Bucket bucket) {
	        log.info("kakaoPay post............................................");
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/mm/dd");
	        Calendar cal =Calendar.getInstance();
	        String[] arrayParam = request.getParameterValues("itemname");
	        System.out.println(arrayParam);
	        StringBuilder sb = new StringBuilder();
	        
	    	for (int i = 0; i < arrayParam.length; i++) {
	    		sb.append(arrayParam[i]+",");
	    		}
	    	
	    	order.setItemname(sb.toString());
	        order.setOrderdate("2019-0-03");
	        order.setOrderstate("발송 준비중");
	        order.setUserseq(user.getUserseq());
	        order.setTotalprice(Integer.parseInt(request.getParameter("totalprice")));
	        order.setTotaldis(Integer.parseInt(request.getParameter("discount")));
	        order.setOrderdetail(request.getParameter("detail"));
	        order.setDelivery(Integer.parseInt(request.getParameter("delivery")));
	        order.setCalprice(Integer.parseInt(request.getParameter("calprice")));
	        order.setAddressnum(request.getParameter("addressnum"));
	        order.setAddress2(request.getParameter("address2"));
	        order.setAddress1(request.getParameter("address1"));
	        order.setOrdername(request.getParameter("received"));
	      
	        service.productorder(order);
	        Long oseq=(order.getOrderseq()); service.afterpaymnet(oseq);
	        
	        return "redirect:" + kakaopay.kakaoPayReady(order);
	 
	    }
	    
	    @GetMapping("/kakaoPaySuccess")
	    public String kakaoPaySuccess(@RequestParam("pg_token") String pg_token, Model model) {
	    	
	    	
	        log.info("kakaoPaySuccess get............................................");
	        log.info("kakaoPaySuccess pg_token : " + pg_token);
			return "redirect:welcome";
	        
	        
	    }
}
