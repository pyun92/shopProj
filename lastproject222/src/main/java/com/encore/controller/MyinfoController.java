package com.encore.controller;


import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.encore.domain.Bucket;
import com.encore.domain.ProductOrder;
import com.encore.domain.Review;
import com.encore.domain.Userdata;
import com.encore.persistence.BucketRepository;
import com.encore.persistence.ShopRepository;
import com.encore.service.MyinfoServiceImpl;
import com.encore.service.OptionService;
import com.encore.service.ProductServiceImpl;

@SessionAttributes("data")
@Controller
public class MyinfoController {
	
	@Value("${file.upload-dir}")
	String uploadFileDir;
	
	@Autowired
	private MyinfoServiceImpl myinfoservice;
	
	@Autowired
	private OptionService optionservice;
	
	@Autowired
	private ProductServiceImpl psl;
	
	@Autowired
	private ShopRepository shop;
	
	@RequestMapping("/jumoon")
	public String Jumoon(@ModelAttribute("data") Userdata user,Model model,Integer pgno) {
		
	List<ProductOrder> listsize = myinfoservice.orderlist(user.getUserseq());
	Page<ProductOrder> page= myinfoservice.listpage(user.getUserseq(),PageRequest.of(pgno-1, 5, new Sort(Direction.DESC, "orderseq")));
	
	List<Integer> pagenum =new ArrayList<Integer>();
	pagenum.add(listsize.size());
	pagenum.add(pgno);
	
	model.addAttribute("jumoonsize",pagenum);		
	model.addAttribute("jumoonlist",page);
	model.addAttribute("options",optionservice.findoption());
	model.addAttribute("jumoondetail",psl.findallbucket(user.getUserseq()));
		return "jumoon";
	}
	
	@GetMapping("/cancellist")
	public String cancellist(@ModelAttribute("data") Userdata user,Model model) {
	model.addAttribute("jumoonlist",myinfoservice.orderlist(user.getUserseq()));	
		return "cancel";
	}
	
	@RequestMapping("/jumoondetail")
	public String jumoondetail(ProductOrder porder,Model model) {
		System.out.println("111111111111111111111111111");
		List<Bucket> jumoondetail =myinfoservice.jumoondetail(porder.getOrderseq());
		model.addAttribute("options",optionservice.findoption());
		model.addAttribute("jumoondetail",jumoondetail);
		
		return "jumoondetail";
	}
	
	@RequestMapping("/jumooncancel")
	public String jumooncancel(ProductOrder order) {
		myinfoservice.cancel(order.getOrderseq());
		return "redirect:jumoon";
		
	}
	
	//리뷰창
	@GetMapping("/review")
	public String review(@ModelAttribute("data") Userdata data,Long seq,Model model) {
		model.addAttribute("prodde",myinfoservice.reviewProduct(seq));
		return "review2";
	}
	
	//저장
	@RequestMapping("/reviewsave")
	public String reivewsave(@ModelAttribute("data") Userdata data,MultipartHttpServletRequest mtfReqeust) {
		System.out.println("쓴 내용:"+mtfReqeust.getParameter("reviewdetail"));
		Review review=new Review();
		
		//이미지 먼저 저장하기
		MultipartFile file=mtfReqeust.getFile("reviewimg");
		String fileNameExtension=FilenameUtils.getExtension(file.getOriginalFilename()).toLowerCase();
		String realFilename=RandomStringUtils.randomAlphanumeric(10) + "." + fileNameExtension; 
		File realFile = new File(uploadFileDir+realFilename);
		realFile.getParentFile().mkdirs();
		try {
			file.transferTo(realFile);
		} catch (IllegalStateException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("파일 저장됨");
		review.setReviewimg(realFilename);
		review.setUserseq(data.getUserseq());
		review.setBucketseq(Long.valueOf(mtfReqeust.getParameter("bucketseq")));
		review.setProductseq(Long.valueOf(mtfReqeust.getParameter("productseq")));
		review.setReviewdetail(mtfReqeust.getParameter("reviewdetail"));
		review.setReviewdate(new SimpleDateFormat("yyyy/MM/dd").format(new Date()));
		
		myinfoservice.reviewSave(review,Long.valueOf(mtfReqeust.getParameter("bucketseq")));
		return "redirect:myreview";
	}
	
	//주문목록받아옴
	@GetMapping("/myreview")
	public String myReview(@ModelAttribute("data") Userdata data,Model model) {
		System.out.println("데이터"+data.getUserseq());
		model.addAttribute("completelist",myinfoservice.ordercompleteList(data.getUserseq()));
		return "myreview";
	}
	
	//내가 쓴 리뷰보기
	@RequestMapping("/myreviewpage")
	public String myreivewPage(@ModelAttribute("data") Userdata data,Model model) {
		model.addAttribute("relist",myinfoservice.reviewList(data.getUserseq()));
		return "myreviewpage";
	}
	
	//리뷰수정
	@RequestMapping("/updateReview")
	@ResponseBody
	public Map<Object,Object> updateReview(MultipartHttpServletRequest mtfReqeust,@ModelAttribute("data") Userdata data) {
		System.out.println("쓴 내용:"+mtfReqeust.getParameter("reviewdetail"));
		Review review=myinfoservice.review(Long.valueOf(mtfReqeust.getParameter("updateseq")));
		review.setReviewdetail(mtfReqeust.getParameter("reviewdetail"));
		review.setReviewdate(new SimpleDateFormat("yyyy/MM/dd").format(new Date()));
		
		//이미지 변경됬을때
		MultipartFile img=mtfReqeust.getFile("reviewimg");
		if(img.getSize()>0L) {
			String fileNameExtension=FilenameUtils.getExtension(img.getOriginalFilename()).toLowerCase();
			String realFilename=RandomStringUtils.randomAlphanumeric(10) + "." + fileNameExtension; 
			File realFile = new File(uploadFileDir+realFilename);
			realFile.getParentFile().mkdirs();
			try {
				img.transferTo(realFile);
			} catch (IllegalStateException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			review.setReviewimg(realFilename);
			System.out.println("파일 저장됨");
		}
		myinfoservice.updateReview(review);
		
		Map<Object,Object> m= new HashMap<Object, Object>();
		m.put("ok", 1);
		return m;
	}
	
	//리뷰삭제
	@RequestMapping("/deleteReview")
	@ResponseBody
	public Map<Object,Object> deleteReview(@RequestBody Map<String,Object> obj){
		System.out.println("상품번호 : "+obj.get("reviewseq"));
		Long seq=Long.valueOf((String) obj.get("reviewseq"));
		//사진삭제
		Review review=myinfoservice.review(seq);
		File file=new File(uploadFileDir+review.getReviewimg());
		if(file.exists()) {
			if(file.exists()) {
				System.out.println("파일삭제 성공");
			}else {
				System.out.println("파일 삭제 실패");
			}
		}else {
			System.out.println("파일이 존재하지 않습니다.");
		}
		
		myinfoservice.deleteReview(seq);
		Map<Object,Object> m= new HashMap<Object, Object>();
		m.put("ok", 1);
		return m;
	}
	
	@GetMapping("/myinfoside")
	public void myinfoside() {
	
	}
}
