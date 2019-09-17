package com.encore.controller;

import java.util.ArrayList;
//민성테스트  asdfasdfasdf
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.encore.domain.Advertising;
import com.encore.domain.Store;
import com.encore.service.AdminService;
import com.encore.service.AdvertisingService;
import com.encore.service.EmailChkService;
import com.encore.service.ProductImgService;
import com.encore.service.ProductService;
import com.encore.service.ShopService;
import com.encore.service.TimetableService;

@Controller
public class AdminController {

	@Autowired
	private AdminService adminService;

	@Autowired
	private EmailChkService eService;
	
	@Autowired
	private ProductService prodservice;
	
	@Autowired
	private ProductImgService prodimgservice;
	
	@Autowired
	private ShopService shopservice;

	@Autowired
	private AdvertisingService adservice;
	
	@Autowired
	private TimetableService tservice;

	@GetMapping("/admin_jang")
	public String admin() {
		return "admin_login";
	}

	// 로그인했을때 나오는 페이지
	@GetMapping("/admin_main")
	public void admin_main() {

	}

	//
	// 신고페이지
	@RequestMapping("/admin_report")
	public ModelAndView admin_report(ModelAndView mav) {
		mav.addObject("storelist", adminService.selectReport());// 상점
		mav.addObject("product", adminService.selectProductseq());
		// mav.addObject("userlist",adminService.selectReport(0));//회원
		return mav;
	}

	// 신고할데이터 넘어올때
	@RequestMapping("/report_confirm")
	@ResponseBody
	public Map<Object, Object> report_confirm(@RequestBody Map<String, Object> map) {
		Map<Object, Object> map1 = new HashMap<Object, Object>();
		System.out.println("날짜" + map.get("day") + "시퀀스:" + map.get("seq"));
		List<String> s = (List<String>) map.get("seq");
		List<Long> seq = new ArrayList<Long>();
		for (int i = 0; i < s.size(); i++) {
			seq.add(Long.parseLong(s.get(i)));
			System.out.println("시퀀스 " + seq.get(i));
		}
		System.out.println("시퀀스:" + seq.size());
		int day = (int) (map.get("day"));
		int n = 0;
		for (Long i : seq) {
			n = adminService.updateConfirm(i, day);
		}
		map1.put("confirm", n);
		return map1;
	}

	@RequestMapping("/admin_user")
	public ModelAndView admin_user(ModelAndView mav) {
		mav.addObject("userlist", adminService.selectUser());

		return mav;
	}

	// 타임테이블 설정
	@RequestMapping("/admin_timetable")
	public ModelAndView admin_timetable(ModelAndView mav) {
		mav.addObject("list", tservice.findAll());
		
		return mav;
	}
	
	//광고 쳌쳌
	@GetMapping(value = "/addel")
	public ModelAndView addel(ModelAndView mav, HttpServletRequest req, Advertising adv) { // 컨트롤러 거치면서 매핑주소때문에 url이상한데
																							// 어떻게 할까요
		adservice.deleteAD(adv.getAdseq());

		mav.setViewName("admin_advertising");
		mav.addObject("list", adservice.findAll());
		return mav;
	}
	
	//광고승인
	@GetMapping(value = "/adok")
	public ModelAndView adok(ModelAndView mav,  @RequestParam(value = "adseq") String adseq) { 
		Long seq = Long.parseLong(adseq);
		System.out.println(seq);
		adservice.updateADstat(seq);

		mav.setViewName("admin_advertising");
		mav.addObject("list", adservice.findAll());
		return mav;
	}

	//광고설정
		@GetMapping(value = "/setad")
		public String setad(@RequestParam(value = "adseq") String adseq, Model model) { 
			Advertising ad =  adservice.findAdvertising(Long.parseLong(adseq));
			model.addAttribute("product", prodservice.getProd(ad.getProdseq())); //상품
			model.addAttribute("prodimg", prodimgservice.getProdImg(ad.getProdseq())); //상품이미지
			model.addAttribute("store",	shopservice.findbyid(prodservice.getProd(ad.getProdseq()).getStoreseq()));
			//상점과 상점소개글
			model.addAttribute("ad", ad);	//ad소개글, url

			//이대로만 넣을 시 타임테이블 도메인 수정해야함
			
			return "admin_ad_update";
		}
	
	@RequestMapping("/admin_advertising")
	public ModelAndView admin_advertising(ModelAndView mav) {
		mav.addObject("list", adservice.findAll());

		return mav;
	}

	@GetMapping("/admin_sj")
	public ModelAndView admin_sj(ModelAndView mav) {
		List<Store> list = adminService.selectStore();
		mav.addObject("storelist", list);
		return mav;
	}

	// 입점완료메일--0830추가
	// 1212마지막으로
	@RequestMapping("/approve")
	@ResponseBody
	public Map<Object, Object> approve(@RequestBody Map<String, Object> map1) {
		System.out.println("이메일 " + map1.get("email") + "시퀀스:" + map1.get("seq"));
		String email = (String) map1.get("email");
		Long seq = Long.parseLong((String) map1.get("seq"));
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			eService.send("uncleminsung@gmail.com", "ynecplvxakafokod", email, "", "장날 입점을 축하합니다.",
					"장날의 가족이 되신걸 축하합니다.!<br>" + "지금 바로 나의 상점에 접속해 보세요!<br>" + "<a href='#'>장날 접속하기</a>");
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}

		// 현재 등록 날짜 수정
		adminService.updateRegister(seq);
		// user의 매니지레벨 1로
		adminService.updateLevel(seq);

		map.put("email_ok", 1);
		map.put("store_update", 1);
		return map;
	}

}
