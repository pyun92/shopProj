package com.encore.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.encore.domain.Store;
import com.encore.service.AdminService;
import com.encore.service.EmailChkService;
import com.encore.service.ShopService;

@Controller
public class AdminController {

	@Autowired
	private AdminService adminService;

	@Autowired
	private EmailChkService eService;
	
	@GetMapping("/admin_jang")
	public String admin() {
		return "admin_login";
	}
	
	//로그인했을때 나오는 페이지
	@GetMapping("/admin_main")
	public void admin_main() {
		
	}
	
	@GetMapping("/admin_user")
	public void admin_user() {
		
	}
	
	@GetMapping("/admin_sj")
	public ModelAndView admin_sj(ModelAndView mav) {
		List<Store> list=adminService.selectStore();
		mav.addObject("storelist", list);
		return mav;
	}
	
	//입점완료메일--0830추가
	@RequestMapping("/approve")
	@ResponseBody
	public Map<Object, Object> approve(@RequestBody String email) {
		System.out.println("이메일 "+email);
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			eService.send("uncleminsung@gmail.com", "ynecplvxakafokod", email, "", "장날 입점을 축하합니다.", 
					"장날의 가족이 되신걸 축하합니다.!<br>"+"지금 바로 나의 상점에 접속해 보세요!<br>"+"<a href='#'>장날 접속하기</a>");
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		//현재 등록 날짜 수정
		adminService.updateRegister(email);
		
		map.put("email_ok",1);
		map.put("store_update",1);
		return map;
	}
}
