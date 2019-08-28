package com.encore.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
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

import com.encore.domain.Bucket;
import com.encore.domain.Option;
import com.encore.domain.Order;
import com.encore.domain.Product;
import com.encore.domain.ProductImg;
import com.encore.domain.Userdata;
import com.encore.service.OptionService;
import com.encore.service.ProductImgService;
import com.encore.service.ProductService;

@SessionAttributes("data")
@Controller
public class ProductController {
	@Value("${file.upload-dir}")
	String uploadFileDir;
	
	@Autowired
	private ProductService service;
	
	@Autowired
	private ProductImgService imgService;
	
	@Autowired
	private OptionService opService;
	
	@ModelAttribute("data")
	public Userdata setMember() {
		return new Userdata();
	}
	

	
	//상품 등록
	@RequestMapping("/insertProc" )
    public String requestupload2(MultipartHttpServletRequest mtfRequest,@ModelAttribute("data") Userdata userdata ) {
        List<MultipartFile> fileList = mtfRequest.getFiles("files");
       
        Product prod = new Product();
        
        prod.setName(mtfRequest.getParameter("name"));
        prod.setJaego(Integer.parseInt(mtfRequest.getParameter("jaego")));
        prod.setPrice(Integer.parseInt(mtfRequest.getParameter("price")));
        prod.setDiscount(Integer.parseInt(mtfRequest.getParameter("discount")));
        prod.setEditor("ddd");  //나중에 변경
        prod.setStoreseq(userdata.getUserseq());
        service.insertProd(prod); //게시글 insert
        Long detailNum= (long) 0.0;
        for (MultipartFile mf : fileList) {
        		
        		ProductImg prodImg = new ProductImg();
                String fileName = mf.getOriginalFilename(); 
                String fileNameExtension = FilenameUtils.getExtension(fileName).toLowerCase(); 
                File destinationFile; 
                String destinationFileName; 
                //String uploadPath = request.getServletContext().getRealPath("Productimg");
                    destinationFileName = RandomStringUtils.randomAlphanumeric(10) + "." + fileNameExtension; 
                    destinationFile = new File (uploadFileDir+ destinationFileName); 
                System.out.println(("실제 파일 업로드 경로 : "+uploadFileDir));
                System.out.println(mtfRequest.getServletContext().getContextPath());
                destinationFile.getParentFile().mkdirs(); 
                
            	prodImg.setImgnum(prod.getProductseq());
            	prodImg.setFileName(destinationFileName);
            	prodImg.setFileUrl(uploadFileDir);
                prodImg.setDetailnum(detailNum);
                imgService.insertProdImg(prodImg); //file insert
                
                
                
                try {
					mf.transferTo(destinationFile);
					detailNum++;
					System.out.println(detailNum);
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                
            	
        }
        String[] optionContent = mtfRequest.getParameterValues("optionContent");
		String[] optionName = mtfRequest.getParameterValues("optionName");
		String[] optionPrice = mtfRequest.getParameterValues("optionPrice");
		if (optionContent != null) {
			for (int i = 0; i < optionContent.length; i++) {
				Option op = new Option();
				op.setOptioncontent(optionContent[i]);
				op.setOptionname(optionName[i]);
				op.setOptionprice(optionPrice[i]);
				op.setProductseq(prod.getProductseq());
				opService.insertOption(op);
			}
		}

        return "welcome";
    }

	//개인 상점 등록 상품 보기
	@RequestMapping("/product_getList_sj")
	public void product_getList_sj(@ModelAttribute("data") Userdata user,Model model ,Product proc) {
		List<Product> proclist= service.getProdList(user.getUserseq());
		List<ProductImg> procimg =imgService.getDetailNum();
		
		model.addAttribute("proc",proclist);
		model.addAttribute("procimg",procimg);
	}

	
	
	@GetMapping("/product_getProduct2")
	public ModelAndView product_getProduct(ModelAndView mav,Product prod) {
		System.out.println("번호 :"+prod.getProductseq());
		mav.setViewName("product_getProduct2");
		mav.addObject("proDe", service.getProd(prod));
		mav.addObject("proDeImg", imgService.getProdImg(prod));
		return mav;
	}
	
	//장바구니 추가
	@RequestMapping("/bucketsession")
	public String bucketsession(HttpServletRequest request,Model model) {
		System.out.println("=======================================");
		Bucket buc= new Bucket();

		buc.setDeliveryfee(2500);
		buc.setDiscount(Integer.parseInt(request.getParameter("discount")));
		buc.setImgname(request.getParameter("filename"));
		buc.setPrice(Integer.parseInt(request.getParameter("price")));
		buc.setQuantity(Integer.parseInt(request.getParameter("quantity")));
		buc.setSellername(request.getParameter("name"));   //나중에 스토에 이름으로 변경
		buc.setUserseq(Long.parseLong(request.getParameter("owner")));
		service.insertBucket(buc);
		return "redirect:newbucketlist";
		
	}
	@RequestMapping("/newbucketlist")
	public String bucketsession(@ModelAttribute("data") Userdata user,Model model) {
		System.out.println("+++++++++++++++++++++++++++++++");
		model.addAttribute("probucket",service.findallbucket(user.getUserseq()));
		return "newbucket";
	}
	
	//수량 저장
	@RequestMapping("/modify")
	@ResponseBody
	public String modify(@RequestBody Bucket data) {
		System.out.println("121212121212121212121212");
        //service.quantitymodify(data);
       
        return "map";
	}
}

