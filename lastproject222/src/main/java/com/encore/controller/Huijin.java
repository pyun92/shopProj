package com.encore.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.encore.domain.Bucket;
import com.encore.domain.Option;
import com.encore.domain.Product;
import com.encore.domain.ProductOrder;
import com.encore.domain.Store;
import com.encore.domain.Userdata;
import com.encore.service.MyinfoServiceImpl;
import com.encore.service.OptionService;
import com.encore.service.ProductImgService;
import com.encore.service.ProductService;
import com.encore.service.ShopService;

@SessionAttributes("data")
@Controller
public class Huijin {
	@Autowired
	private ProductService service;
	
	@Autowired
	private MyinfoServiceImpl myinfo;
	
	@Autowired
	private ShopService shopservice;
	
	@Autowired
	private OptionService optionservice;
	
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
	
	@RequestMapping("/paymentwindowdir")
	public String paymentwindowdir(HttpServletRequest request,Model model,@ModelAttribute("data") Userdata user,Long bucnum) {
	ProductOrder order = new ProductOrder();
	System.out.println(bucnum);
	Bucket buc = new Bucket();
	buc =service.findByseq(bucnum);
	Option op= optionservice.findopforbuc(buc.getOptionseq());
	order.setTotaldis(buc.getDiscount()*buc.getQuantity());
	order.setDelivery(buc.getDeliveryfee());
	order.setTotalprice(buc.getPrice()*buc.getQuantity());
	order.setCalprice((buc.getPrice()*buc.getQuantity())-(buc.getDiscount()*buc.getQuantity())+buc.getDeliveryfee()-(Integer.parseInt(op.getOptionprice())*buc.getQuantity()));
	model.addAttribute("order",order);
	model.addAttribute("bucketinfo",buc);
		return "baesong";
		
	}
	
	@RequestMapping("baesong_sj")
	public String beasongsj(@ModelAttribute("data") Userdata user,Model model,Integer pgno) {
		
		Page<Bucket> listjumoon= myinfo.jumoonmanager(user.getUserseq(),PageRequest.of(pgno-1, 5, new Sort(Direction.DESC, "bucketseq")));
		
		List<Bucket> listsize = myinfo.jumoonsize(user.getUserseq());
		System.out.println(listjumoon+"222222222222222222222222222");
		
		List<Integer> bucketsize= new ArrayList<Integer>();
		bucketsize.add(listsize.size());
		bucketsize.add(pgno);
		model.addAttribute("ordersize",bucketsize);
		model.addAttribute("options",optionservice.findoption());
		model.addAttribute("list", listjumoon);
		model.addAttribute("store", shopservice.findbyid(user.getUserseq()));
		return "baesong_sj";
		
	}
	
	
		@RequestMapping("/bucconditioncancel")
		@ResponseBody
		public String buccondition(@RequestBody Long seq) {
			myinfo.cancel(seq);
			return "cancel";
			
		}
		@RequestMapping("/bucconditioncomplete")
		@ResponseBody
		public void bucconditioncomplete(@RequestBody Long seq) {
			System.out.println(seq);
			myinfo.complete(seq);
		}
		
		@RequestMapping("/bucconditioncompletereceive")
		@ResponseBody
		public void bucconditioncompletereceive(@RequestBody Long seq) {
			System.out.println(seq);
			myinfo.receive(seq);
		}
		
		@GetMapping("/product_insert")
		public String product_insert(@ModelAttribute("data") Userdata user,Model model) {
			model.addAttribute("store", shopservice.findbyid(user.getUserseq()));
			return "product_insert";
		
		}
		
}
