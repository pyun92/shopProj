package com.encore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.encore.domain.Bucket;
import com.encore.domain.ProductOrder;
import com.encore.domain.Userdata;
import com.encore.service.MyinfoServiceImpl;
import com.encore.service.OptionService;

@SessionAttributes("data")
@Controller
public class MyinfoController {
	
	@Autowired
	private MyinfoServiceImpl myinfoservice;
	
	@Autowired
	private OptionService optionservice;
	
	@GetMapping("/jumoon")
	public String Jumoon(@ModelAttribute("data") Userdata user,Model model) {
			System.out.println(user.getUserseq());
	model.addAttribute("jumoonlist",myinfoservice.orderlist(user.getUserseq()));	
		return "jumoon";
	}
	@GetMapping("/cancellist")
	public String cancellist(@ModelAttribute("data") Userdata user,Model model) {
			
	model.addAttribute("jumoonlist",myinfoservice.orderlist(user.getUserseq()));	
		return "cancel";
	}
	
	@RequestMapping("/jumoondetail")
	public String jumoondetail(ProductOrder porder,Model model) {
		System.out.println("111111111111111111111111111");
		List<Bucket> jumoondetail =myinfoservice.jumoondetail(porder.getOrderseq());
		model.addAttribute("options",optionservice.findoption());
		model.addAttribute("jumoondetail",jumoondetail);
		
		return "jumoondetail";
	}
	
	@RequestMapping("/jumooncancel")
	public String jumooncancel(ProductOrder order) {
		myinfoservice.cancel(order.getOrderseq());
		return "redirect:jumoon";
		
	}
}
