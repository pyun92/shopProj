package com.encore.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.encore.domain.Report;
import com.encore.domain.Userdata;
import com.encore.service.AdminService;
import com.encore.service.LoginDataService;

@SessionAttributes("data")
@Controller
public class LoginController {

	@Autowired
	private LoginDataService logindata;
	
	@Autowired
	private AdminService adminservice;

	//회원 가입
	@PostMapping("/insertuser")
	public String insertuser(Userdata data) {
		logindata.insertId(data);
		return "redirect:welcome";
	}
	
	//회원 로그인
	@PostMapping("/login")
	public String login(Userdata data, Model model, HttpServletResponse response) {
		Userdata findMember = logindata.login(data);
		Report r= adminservice.selectReportuser(findMember.getUserid());
		
		if (findMember != null && findMember.getPassword().equals(data.getPassword())) {
			System.out.println(findMember.getManage_level());
			//admin일때
			if(findMember.getManage_level()==2L) {
				System.out.println("매니저");
				return "redirect:admin_main";
			}
			//신고게시판에 이름이 있을때
			if(r!=null && findMember.getManage_level()==1L) {
				try {
					SimpleDateFormat format=new SimpleDateFormat("yyyy/mm/dd");
					Date today=format.parse(new SimpleDateFormat("yyyy/MM/dd").format(new Date()));
					if(r.getConfirmdate()==null) {
						response.setContentType("text/html; charset=UTF-8");
						PrintWriter out;
						try {
							out = response.getWriter();
							out.println("<script>alert('신고접수상태입니다.');"
									+ "location.href='welcome';</script>");
							out.flush();
							out.close();
							return "";
						} catch (IOException e) {
							e.printStackTrace();
						}
					}else {
						Date freeday=format.parse(r.getConfirmdate());
						long cal=(freeday.getTime()-today.getTime())/(24*60*60*1000);
						cal=Math.abs(cal);
						System.out.println("날짜차이:"+cal);
						if(cal>=0) {
							response.setContentType("text/html; charset=UTF-8");
							PrintWriter out;
							try {
								out = response.getWriter();
								out.println("<script>alert('"+r.getConfirmdate()+"일 까지 이용이 불가합니다.');"
										+ "location.href='welcome';</script>");
								out.flush();
								out.close();
								return "";
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
					
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
			}else {//신고테이블에 이름 x
				model.addAttribute("data", findMember);
			}
			//신고테이블에 이름이 있건 없건 둘다 welcome 페이지로 넘어감
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
