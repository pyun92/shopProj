package com.encore.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.encore.domain.Store;
import com.encore.domain.Userdata;
import com.encore.service.ShopService;

@SessionAttributes("data")
@Controller
public class ShopController {
	@Value("${store.upload-dir}")
	String storeFileURL;

	@Value("${storeimg.upload-dir}")
	String storeimgFileURL;
	
	@Autowired
	private ShopService shopservice;

	@RequestMapping("/shopRegister")
	public void shopRegister() {

	}

	@RequestMapping(value= {"/index","/"})
	public String index(Model model, Store store) {
		if(shopservice.findbyid(store.getStoreseq())==null) {
//			mav.setViewName("welcome");
//			model.setViewName("redirect:welcome");
			return "redirect:welcome";
		}else {
//			model.addObject("store", shopservice.findbyid(store.getStoreseq()));
			model.addAttribute("store", shopservice.findbyid(store.getStoreseq()));
			return "redirect:product_getList2?storeseq="+store.getStoreseq();
		}
	
	}

	@RequestMapping("/storelist")
	public String list(Model mav) {
		mav.addAttribute("stlist",shopservice.AllstoreList());
		return "storelist";
	}
	
	@RequestMapping("/namecheck")
	@ResponseBody
	public Map<Object, Object> nameCheck(@RequestBody String data) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("cnt", shopservice.namecheck(data));
		return map;
	}

	@PostMapping("/insertShop")
	public String insertShop(MultipartHttpServletRequest mrequest, @ModelAttribute("data") Userdata user) {
		List<MultipartFile> storefile = mrequest.getFiles("storefile");
		MultipartFile storeimg = mrequest.getFile("storeimg");

		Store store = new Store();
		String storename;
		
		store.setStorename(mrequest.getParameter("storename"));
		System.out.println("세션 아이디" + user.getUserseq());
		store.setStoreseq(user.getUserseq());
		store.setStoredetail(mrequest.getParameter("storedetail"));
		store.setStoreemail(mrequest.getParameter("storeemail"));
		store.setStoretype(mrequest.getParameter("storetype"));
		String lastFileName = "";
		System.out.println(store.getStoredetail() + store.getStoreemail() + store.getStoretype());
		// 이미지파일 추가
		for (int i = 0; i < storefile.size(); i++) {
			String fileName=user.getUserid()+"_"+i;
			String fileExtendsion=FilenameUtils.getExtension(storefile.get(i).getOriginalFilename()).toLowerCase();
			storename=fileName+"."+fileExtendsion;
			File lastFile=new File(storeFileURL+storename);

			try {
				storefile.get(i).transferTo(lastFile);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}

			lastFileName+=storename;
			if (i + 1 < storefile.size()) {
				lastFileName += ",";
			}
			System.out.println("저장할 파일 이름:" + lastFileName);

		}

		store.setStorefile(lastFileName);
		
		//상점사진 추가
			String storeimgName=user.getUserid()+"_storeimg_";
			String storeimgNameExtendsion=FilenameUtils.getExtension(storeimg.getOriginalFilename()).toLowerCase();
			String storename2=storeimgName+"."+storeimgNameExtendsion;
			File lastFile2=new File(storeimgFileURL+storename2);
			try {
				storeimg.transferTo(lastFile2);
			} catch (IllegalStateException  |IOException e) {
				e.printStackTrace();
			} 
			
			store.setStoreimg(storename2);
		//
		store.setRequestdate(new SimpleDateFormat("yyyy/MM/dd").format(new Date()));
		System.out.println("store " + store.toString());
		shopservice.insertStore(store);

		
		
		return "welcome";
	}
}
