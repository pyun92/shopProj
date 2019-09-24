package com.encore.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.encore.service.AdvertisingService;
import com.encore.service.EmailChkService;
import com.encore.service.ProductImgService;
import com.encore.service.ProductService;
import com.encore.service.TimetableService;



@Controller
public class BoardController {
	
	@Autowired
	private ProductService service;
	
	@Autowired
	private EmailChkService eService;
	
	@Autowired
	private ProductImgService imgService;
	
	@Autowired
	private AdvertisingService adservice;
	
	@Autowired
	private TimetableService tservice;
	
	@GetMapping("/welcome")
	public ModelAndView product_getList(ModelAndView mav) {
					
		mav.setViewName("welcome");
		
		//대문광고용 
		mav.addObject("tblist", tservice.findAll().size()>0?tservice.findAll():null);	//타임테이블가져오기
		mav.addObject("bigadlist",adservice.findbigad());	//대문광고만 가져오기
		mav.addObject("bigprod",adservice.findbigadprod()); //대문광고한 제품가져오기
		mav.addObject("adstore",adservice.findadstore());  //대문광고한 상점가져오기
		mav.addObject("adprodimg",adservice.findbigadimg());  //대문광고한 상점가져오기
		//---------------------------------------
		mav.addObject("adlist", adservice.findAll());		//광고 다가져오기(intro 쓰려면)
		mav.addObject("list", adservice.findAdProduct());	//추천광고관련 상품 다가져오기
		mav.addObject("prodall", service.getProdListAll());
		mav.addObject("procimgdetail",imgService.getDetailNum());
		return mav;
	}

	@RequestMapping("/mailgo")
	@ResponseBody
	public Map<Object, Object> mailgo(@RequestBody String data) {
		System.out.println(data);
		Map<Object, Object> map = new HashMap<Object, Object>();
		StringBuffer temp = new StringBuffer();
		Random rnd = new Random();
		for (int i = 0; i < 8; i++) {
			int rIndex = rnd.nextInt(3);
			switch (rIndex) {
			case 0:
				// a-z
				temp.append((char) ((int) (rnd.nextInt(26)) + 97));
				break;
			case 1:
				// A-Z
				temp.append((char) ((int) (rnd.nextInt(26)) + 65));
				break;
			case 2:
				// 0-9
				temp.append((rnd.nextInt(10)));
				break;
			}
		}
		String certkey = temp.toString();
		try {
			eService.send("uncleminsung@gmail.com", "ynecplvxakafokod", data, "", "장날 이메일 인증키 요청",
					"전통을 사고팔다. 인터넷 쇼핑몰 장날에 오신것을 환영합니다."+
					"인증키 :  " + certkey);
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
			//메일 잘못 되면 여기서 잡힘
		}

		map.put("certkey", certkey);
		return map;
	}

	@GetMapping("/main")
	public void main() {
	
	}
	
	@GetMapping("/reg")
	public void reg() {
	
	}
	@GetMapping("/mydetail")
	public void detail() {
	
	}


}