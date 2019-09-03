package com.encore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.encore.domain.ProductImg;
import com.encore.service.ProductImgService;
import com.encore.service.SearchService;

@Controller
public class SearchController {

	@Autowired
	private SearchService service;
	
	@Autowired
	private ProductImgService imgservice;
	
	@RequestMapping("/search_getList")
	public String search_getList(@RequestParam ("searchword") String word, Model model) {
		model.addAttribute("prodlist", service.searchProd(word).size()==0?null:service.searchProd(word));
		System.out.println("service.searchProd(word)"+service.searchProd(word));
		System.out.println("service.searchStore(word)"+service.searchStore(word));
		model.addAttribute("pagesu", service.getTotalPage());
		
		model.addAttribute("prodimglist", imgservice.getDetailNum());
		model.addAttribute("storelist", service.searchStore(word).size()==0?null:service.searchStore(word));
		
		return "search_getList";
	}
	
	
}
