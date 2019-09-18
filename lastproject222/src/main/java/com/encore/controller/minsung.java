package com.encore.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.DocFlavor.STRING;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.encore.domain.Advertising;
import com.encore.domain.Product;
import com.encore.domain.ProductImg;
import com.encore.domain.Timetable;
import com.encore.domain.Userdata;
import com.encore.service.AdvertisingService;
import com.encore.service.ProductImgService;
import com.encore.service.ProductService;
import com.encore.service.TimetableService;

@SessionAttributes("data")
@Controller
public class minsung {

	@Autowired
	private ProductService service;
	@Autowired
	private ProductImgService imgService;
	@Autowired
	private AdvertisingService adservice;
	@Autowired
	private TimetableService tservice;
	
	
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
		String url="";
		Advertising adv = new Advertising();
		adv.setBigad(Integer.parseInt(mtfRequest.getParameter("ad1")));
		adv.setSmallad(Integer.parseInt(mtfRequest.getParameter("ad2")));
		adv.setProdseq(Long.parseLong(mtfRequest.getParameter("prodseq")));
		if(mtfRequest.getParameter("vidurl").length()!=0) {
			url = "https://www.youtube.com/embed/" +mtfRequest.getParameter("vidurl").split("be/")[1];
		}
		adv.setVidurl(url);
		adv.setIntro(mtfRequest.getParameter("storedetail"));
		adv.setStatus("대기");
		adservice.insertAD(adv);
		return "redirect:welcome";
	}
	
	@GetMapping(value = "/gotable")
	public ModelAndView gotable(ModelAndView mav,  @RequestParam(value = "adseq") String adseq) { 
		Long seq = Long.parseLong(adseq);
		Timetable tb = new Timetable();
		tb.setProductseq(seq);
		tb.setVideourl(adservice.findAdvertising(seq).getVidurl());
		tservice.insertTB(tb);
		adservice.updatebigAD(seq);	//이거 상태만 바꾸고 그대로 둬서 조회는 안되게 만들어놓는게 좋을 듯
		mav.setViewName("admin_advertising");
		mav.addObject("list", adservice.findAll());
		return mav;
	}
	
	@GetMapping(value = "/gosmallad")
	public ModelAndView gosmallad(ModelAndView mav,  @RequestParam(value = "adseq") String adseq) { 
		Long seq = Long.parseLong(adseq);
		adservice.updateSmallAD(seq);	
		mav.setViewName("admin_advertising");
		mav.addObject("list", adservice.findAll());
		return mav;
	}

}
