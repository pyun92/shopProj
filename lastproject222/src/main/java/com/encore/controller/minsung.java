package com.encore.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.DocFlavor.STRING;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

	@RequestMapping(name = "/advertisereg")
	public void advertisereg(@ModelAttribute("data") Userdata user, Model model) {

		List<Product> prodlist = service.getProdList(user.getUserseq());

		model.addAttribute("prodlist", prodlist);
	}

	@RequestMapping("/adimg")
	@ResponseBody
	public Map<Object, Object> adimg(@RequestBody Map<String, Object> map) {
		String str = map.get("seq")+"";
		Long seq = Long.parseLong( str); 
		ProductImg img = imgService.getDetailNum(seq);
		Map<Object, Object> map1 = new HashMap<Object, Object>();
		map1.put("imgj", img);
		return map1;
	}

}
