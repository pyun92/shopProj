package com.encore.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.encore.domain.Userdata;
import com.encore.service.LoginDataService;

@SessionAttributes("data")
@Controller
public class LoginController {

	@Autowired
	private LoginDataService logindata;
	
	
	//회원 가입
	@PostMapping("/insertuser")
	public String insertuser(Userdata data) {
		logindata.insertId(data);
		return "redirect:welcome";
	}
	
	//회원 로그인
	@PostMapping("/login")
	public String login(Userdata data, Model model ) {
		Userdata findMember = logindata.login(data);
		
		if (findMember != null && findMember.getPassword().equals(data.getPassword())) {
			model.addAttribute("data", findMember);
			if(findMember.getManage_level()==2L) {//admin일때
				System.out.println("매니저");
				return "redirect:admin_main";
			}else {
				
			}
			return "redirect:welcome";
			
		} else {
			return "redirect:welcome";
		}
	}
	
	@GetMapping("/logout")
	public String logout(SessionStatus status) {
		status.setComplete();
		return "redirect:welcome";		
	}
	
	@RequestMapping("/idcheck")
	@ResponseBody
	public Map<Object, Object>  idcheck(@RequestBody String data) {
		int count ;
        Map<Object, Object> map = new HashMap();
        count = logindata.idcheck(data);
        map.put("cnt", count);
        return map;
	}
	
	
}
