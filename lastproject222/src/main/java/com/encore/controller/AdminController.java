package com.encore.controller;
import java.util.ArrayList;
//민성테스트  asdfasdfasdf
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

import com.encore.domain.Report;
import com.encore.domain.Store;
import com.encore.domain.Userdata;
import com.encore.service.AdminService;
import com.encore.service.EmailChkService;
import com.encore.service.LoginDataService;
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
	
	//신고페이지
	@RequestMapping("/admin_report")
	public ModelAndView admin_report(ModelAndView mav) {
		mav.addObject("storelist",adminService.selectReport());//상점
		mav.addObject("product",adminService.selectProductseq());
		//mav.addObject("userlist",adminService.selectReport(0));//회원
		return mav;
	}
	
	//신고할데이터 넘어올때
	@RequestMapping("/report_confirm")
	@ResponseBody
	public Map<Object,Object> report_confirm(@RequestBody Map<String,Object> map){
		Map<Object,Object> map1=new HashMap<Object, Object>();
		System.out.println("날짜"+map.get("day")+"시퀀스:"+map.get("seq"));
		List<String>s=(List<String>) map.get("seq");
		List<Long>seq=new ArrayList<Long>();
		for(int i=0;i<s.size();i++) {
			seq.add(Long.parseLong(s.get(i)));
			System.out.println("시퀀스 "+seq.get(i));
		}
		System.out.println("시퀀스:"+seq.size());
		int day=(int)(map.get("day"));
		int n=0;
		for(Long i:seq) {
			n=adminService.updateConfirm(i, day);
		}
		map1.put("confirm",n);
		return map1;
	}
	
	@RequestMapping("/admin_user")
	public ModelAndView admin_user(ModelAndView mav) {
		mav.addObject("userlist",adminService.selectUser());
		
		return mav;
	}
	
	@GetMapping("/admin_sj")
	public ModelAndView admin_sj(ModelAndView mav) {
		List<Store> list=adminService.selectStore();
		mav.addObject("storelist", list);
		return mav;
	}
	
	//입점완료메일--0830추가
	//1212마지막으로
	@RequestMapping("/approve")
	@ResponseBody
	public Map<Object, Object> approve(@RequestBody Map<String,Object> map1) {
		System.out.println("이메일 "+map1.get("email")+"시퀀스:"+map1.get("seq"));
		String email=(String)map1.get("email");
		Long seq=Long.parseLong((String) map1.get("seq"));
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
		adminService.updateRegister(seq);
		//user의 매니지레벨 1로 
		adminService.updateLevel(seq);
		
		map.put("email_ok",1);
		map.put("store_update",1);
		return map;
	}
	
	
}
