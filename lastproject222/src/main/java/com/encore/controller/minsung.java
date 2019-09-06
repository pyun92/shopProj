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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.encore.domain.Advertising;
import com.encore.domain.Product;
import com.encore.domain.ProductImg;
import com.encore.domain.Userdata;
import com.encore.service.AdvertisingService;
import com.encore.service.ProductImgService;
import com.encore.service.ProductService;

@SessionAttributes("data")
@Controller
public class minsung {

	@Autowired
	private ProductService service;
	@Autowired
	private ProductImgService imgService;
	@Autowired
	private AdvertisingService adservice;

	@RequestMapping("/advertisereg")
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
	
	@RequestMapping(value = "/adregform" ,method = RequestMethod.POST)
	public String adregform(MultipartHttpServletRequest mtfRequest) {
		Advertising adv = new Advertising();
		adv.setBigad(Integer.parseInt(mtfRequest.getParameter("ad1")));
		adv.setSmallad(Integer.parseInt(mtfRequest.getParameter("ad2")));
		adv.setProdseq(Integer.parseInt(mtfRequest.getParameter("prodseq")));
		adv.setVidurl(mtfRequest.getParameter("vidurl"));
		adv.setStatus("대기");
		adservice.insertAD(adv);
		return "redirect:welcome";
	}

}
