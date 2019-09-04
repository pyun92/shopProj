package com.encore.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.encore.domain.Bucket;
import com.encore.domain.ProductOrder;
import com.encore.domain.Userdata;
import com.encore.service.MyinfoServiceImpl;
import com.encore.service.OptionService;
import com.encore.service.ProductImgService;
import com.encore.service.ProductService;

@SessionAttributes("data")
@Controller
public class Huijin {
	@Autowired
	private ProductService service;
	
	@Autowired
	private MyinfoServiceImpl myinfo;
	
	
	
	//결제창 
	@RequestMapping("/paymentwindow")
	public String paymentwindow(HttpServletRequest request,Model model,@ModelAttribute("data") Userdata user,Bucket bucket) {
	ProductOrder order = new ProductOrder();
	String[] arrayParam = request.getParameterValues("bucketseq");
	for (int i = 0; i < arrayParam.length; i++) {
		System.out.println(arrayParam[i]+"111");
		}
	order.setTotaldis(Integer.parseInt(request.getParameter("discount")));
	order.setDelivery(Integer.parseInt(request.getParameter("deliveryfee")));
	order.setTotalprice(Integer.parseInt(request.getParameter("itemsprice")));
	order.setCalprice(Integer.parseInt(request.getParameter("calprice")));
	model.addAttribute("order",order);
	model.addAttribute("bucketinfo",service.findallbucket(user.getUserseq()));
		return "baesong";
		
	}
	
	
	@RequestMapping("/jumoondetail")
	public String jumoondetail(ProductOrder porder,Model model) {
		System.out.println("111111111111111111111111111");
		List<Bucket> jumoondetail =myinfo.jumoondetail(porder.getOrderseq());
		model.addAttribute("jumoondetail",jumoondetail);
		
		return "jumoondetail";
	}
	
	@RequestMapping("/jumooncancel")
	public String jumooncancel(ProductOrder order) {
		myinfo.cancel(order.getOrderseq());
		return "redirect:jumoon";
		
	}
	
}
