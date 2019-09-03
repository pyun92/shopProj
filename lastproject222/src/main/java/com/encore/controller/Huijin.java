package com.encore.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.encore.domain.Bucket;
import com.encore.domain.Order;
import com.encore.domain.ProductOrder;
import com.encore.service.OptionService;
import com.encore.service.ProductImgService;
import com.encore.service.ProductService;


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
	public String paymentwindow(HttpServletRequest request,Model model) {
	ProductOrder order = new ProductOrder();
	List<Long> seq = new ArrayList<Long>();
	seq.add(Long.parseLong(request.getParameter("productseq")));

	order.setTotaldis(Integer.parseInt(request.getParameter("discount")));
	order.setDelivery(Integer.parseInt(request.getParameter("deliveryfee")));
	order.setTotalprice(Integer.parseInt(request.getParameter("itemsprice")));
	order.setCalprice(Integer.parseInt(request.getParameter("calprice")));
	model.addAttribute("order",order);
		return "baesong";
		
	}
}
