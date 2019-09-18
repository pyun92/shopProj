package com.encore.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.encore.domain.Bucket;
import com.encore.domain.ProductOrder;
import com.encore.domain.Userdata;
import com.encore.service.MyinfoServiceImpl;
import com.encore.service.OptionService;
import com.encore.service.ProductServiceImpl;

@SessionAttributes("data")
@Controller
public class MyinfoController {
	
	@Autowired
	private MyinfoServiceImpl myinfoservice;
	
	@Autowired
	private OptionService optionservice;
	
	@Autowired
	private ProductServiceImpl psl;
	
	
	
	@RequestMapping("/jumoon")
	public String Jumoon(@ModelAttribute("data") Userdata user,Model model,Integer pgno) {
		
	List<ProductOrder> listsize = myinfoservice.orderlist(user.getUserseq());
	Page<ProductOrder> page= myinfoservice.listpage(user.getUserseq(),PageRequest.of(pgno-1, 5, new Sort(Direction.DESC, "orderseq")));
	
	List<Integer> pagenum =new ArrayList<Integer>();
	pagenum.add(listsize.size());
	pagenum.add(pgno);
	
	model.addAttribute("jumoonsize",pagenum);		
	model.addAttribute("jumoonlist",page);
	model.addAttribute("options",optionservice.findoption());
	model.addAttribute("jumoondetail",psl.findallbucket(user.getUserseq()));
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
	
	@GetMapping("/myreview")
	public String myReview(@ModelAttribute("data") Userdata data) {
		
		return "myreview";
	}
	
	@GetMapping("/review")
	public String review() {
		return "review";
	}
	
	@GetMapping("/myinfoside")
	public void myinfoside() {
	
	}
}
