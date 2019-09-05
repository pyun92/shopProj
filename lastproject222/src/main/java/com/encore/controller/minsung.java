package com.encore.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.encore.domain.Product;
import com.encore.domain.ProductImg;
import com.encore.domain.Userdata;
import com.encore.service.ProductImgService;
import com.encore.service.ProductService;

@SessionAttributes("data")
@Controller
public class minsung {
	
	@Autowired
	private ProductService service;
	@Autowired
	private ProductImgService imgService;
	
	
	@RequestMapping("/advertisereg")
	public void advertisereg(@ModelAttribute("data") Userdata user, Model model) {
		
		List<Product> prodlist = service.getProdList(user.getUserseq());
		
		model.addAttribute("prodlist",prodlist);
	}
	
	@RequestMapping("/adimg")
	@ResponseBody
	public Map<Object, Object> adimg(@ModelAttribute("data") Userdata user,@RequestBody Long data) {
		
		List<ProductImg> prodimg = imgService.getDetailNum();
		for(ProductImg ob : prodimg) {
			System.out.println(ob.getFileName());
		}
		Map<Object, Object> map = new HashMap<Object, Object>();
		
		return map;
	}

}
