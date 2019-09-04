package com.encore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.encore.domain.Userdata;
import com.encore.service.MyinfoServiceImpl;

@SessionAttributes("data")
@Controller
public class MyinfoController {
	
	@Autowired
	private MyinfoServiceImpl myinfoservice;
	
	
	@GetMapping("/jumoon")
	public String Jumoon(@ModelAttribute("data") Userdata user,Model model) {
			System.out.println(user.getUserseq());
	model.addAttribute("jumoonlist",myinfoservice.orderlist(user.getUserseq()));	
		return "jumoon";
	}
}
