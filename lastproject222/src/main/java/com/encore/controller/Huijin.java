package com.encore.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.encore.domain.Bucket;
import com.encore.domain.ProductOrder;
import com.encore.domain.Userdata;
import com.encore.service.OptionService;
import com.encore.service.ProductImgService;
import com.encore.service.ProductService;

@SessionAttributes("data")
@Controller
public class Huijin {
	@Autowired
	private ProductService service;
	
	@Autowired
	private ProductImgService imgService;
	
	@Autowired
	private OptionService opService;
	
	//결제창 
	@RequestMapping("/paymentwindow")
	public String paymentwindow(HttpServletRequest request,Model model,@ModelAttribute("data") Userdata user,Bucket bucket) {
	ProductOrder order = new ProductOrder();
	
	System.out.println();
	order.setTotaldis(Integer.parseInt(request.getParameter("bucketseq")));
	order.setDelivery(Integer.parseInt(request.getParameter("deliveryfee")));
	order.setTotalprice(Integer.parseInt(request.getParameter("itemsprice")));
	order.setCalprice(Integer.parseInt(request.getParameter("calprice")));
	model.addAttribute("order",order);
	model.addAttribute("bucketinfo",service.findallbucket(user.getUserseq()));
		return "baesong";
		
	}
}
