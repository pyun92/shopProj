package com.encore.controller;

import java.io.File;
import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.encore.domain.Bucket;
import com.encore.domain.Option;
import com.encore.domain.Product;
import com.encore.domain.ProductImg;
import com.encore.domain.Store;
import com.encore.domain.Userdata;
import com.encore.service.OptionService;
import com.encore.service.ProductImgService;
import com.encore.service.ProductService;
import com.encore.service.ReviewService;
import com.encore.service.ShopService;

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
	
	@Autowired
	private ReviewService reviewService;
	
	@Autowired
	private ShopService shopservice;
	
	//에디터 이미지 등록
/*	@RequestMapping("/editorImage")
	public Map<String,Object> editorImage(MultipartHttpServletRequest mtfRequest,@ModelAttribute("data") Userdata user){
		Map<String,Object> map=new HashMap<String, Object>();
		String fileName=
		
		String fileExtends=mtfRequest.getFile("uploadfile").getOriginalFilename()
		
		return null;
	}
*/
	//
	//상품 등록
	@RequestMapping("/insertProc" )
    public String requestupload2(MultipartHttpServletRequest mtfRequest,@ModelAttribute("data") Userdata userdata ) {
		  List<MultipartFile> fileList = mtfRequest.getFiles("files");
	       
	      Product prod = new Product();
	        
	      prod.setProname(mtfRequest.getParameter("proname"));
	      prod.setJaego(Integer.parseInt(mtfRequest.getParameter("jaego")));
	      prod.setPrice(Integer.parseInt(mtfRequest.getParameter("price")));
	      prod.setDiscount(Integer.parseInt(mtfRequest.getParameter("discount")));
	      //  prod.setEditor("ddd");  //나중에 변경
	      prod.setEditor(mtfRequest.getParameter("editor"));
	      prod.setCategorybig(mtfRequest.getParameter("categorybig"));
	      prod.setCategorysmall(mtfRequest.getParameter("categorysmall"));
	      prod.setStoreseq(userdata.getUserseq());
	      service.insertProd(prod); //게시글 insert
	      
	      MultipartFile main_img=mtfRequest.getFile("main_img");
	      imgSave(main_img,prod.getProductseq(),0L);
	      
	      Long detailNum= (long) 1.0;
	      for (MultipartFile mf : fileList) {
	    	  imgSave(mf,prod.getProductseq(),detailNum);
	    	  detailNum++;
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

	        return "redirect:product_getList2"; //판매목록보기로 이동
    }
	
	

	//개인 상점 등록 상품관리 & 개인상점 상품리스트
	@RequestMapping(value= {"/product_getList_sj","/product_getList2"})
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
		mav.addObject("proOption",opService.selectOption(prod.getProductseq()));
		mav.addObject("review",reviewService.findReview(prod));
		return mav;
	}
	
	//상품수정 
	@RequestMapping("/product_update")
	public ModelAndView product_update(ModelAndView model,Product p) {
		System.out.println("상품번호"+p.getProductseq());
		model.setViewName("product_update");
		model.addObject("proc", service.getProd(p));//상품정보
		model.addObject("proImg", imgService.getProdImg(p));//상품이미지
		model.addObject("proOption", opService.selectOption(p.getProductseq()));//상품옵션
		//System.out.println("넘어감?"+imgService.getProdImg(p).get(0).getFileName());
		return model;
	}
	
	//수정하기 처리
	@RequestMapping("/updateProc")
	@ResponseBody
	public Map<Object,Object> updateProc(MultipartHttpServletRequest mtfReqeust,@ModelAttribute("data") Userdata data){
		//수정된 값만 넘어가기
		//게시글 insert
		Long seq=Long.parseLong(mtfReqeust.getParameter("seq"));
		Product pr=service.getProd(seq);
		pr.setProname(mtfReqeust.getParameter("proname"));
        pr.setJaego(Integer.parseInt(mtfReqeust.getParameter("jaego")));
        pr.setPrice(Integer.parseInt(mtfReqeust.getParameter("price")));
        pr.setDiscount(Integer.parseInt(mtfReqeust.getParameter("discount")));
        pr.setEditor(mtfReqeust.getParameter("editor"));
        pr.setCategorybig(mtfReqeust.getParameter("categorybig"));
        pr.setCategorysmall(mtfReqeust.getParameter("categorysmall"));
        pr.setStoreseq(data.getUserseq());
        service.insertProd(pr);
        
        //이미지 
        //재정렬
        List<ProductImg> img=imgService.getProdImg(seq);
        for(int i=0;i<img.size();i++) {
        	img.get(i).setDetailnum((long)i);
        	System.out.println("파일이름 "+img.get(i).getFileName()+"상세번호" +img.get(i).getDetailnum());
        	imgService.insertProdImg(img.get(i));
        }
        
        //대표이미지 수정됬으면 넘기기
        MultipartFile main_img=mtfReqeust.getFile("main_img");
        List<MultipartFile> img_array=mtfReqeust.getFiles("files");//제품이미지
        
        if(!img.get(0).getFileOriginalName().equals(main_img.getOriginalFilename())) {
        	imgSave(main_img,pr.getProductseq(),0L);
        }
        System.out.println("이미지 사이즈 :"+img.size());
        
        Long jseq=(long)img.size();
        for(int i=0;i<img_array.size();i++) {
        	imgSave(img_array.get(i), pr.getProductseq(), jseq);
        	jseq++;
        }
      
    	Map<Object,Object> m= new HashMap<Object, Object>();
		System.out.println("상품수정함");
		m.put("ok", 1);
		m.put("seq", pr.getProductseq());
		return m;
	}
	
	//상품삭제
	@RequestMapping("/deleteProduct")
	@ResponseBody
	public Map<Object,Object> delete_product(@RequestBody Map<String,Object> obj) {
		//System.out.println("넘어감?"+imgService.getProdImg(p).get(0).getFileName());
		System.out.println("상품번호 : "+obj.get("seq"));
		Long seq=Long.valueOf((String)obj.get("seq"));
		service.deleteProduct(seq);
		//파일 삭제
		List<ProductImg> img=imgService.getProdImg(seq);//이미지num으로 받아오기
		File file;
		for(ProductImg i:img) {
			file=new File(i.getFileUrl()+i.getFileName());
			if(file.exists()) {
				if(file.delete()) {//파일삭제 성공
					System.out.println("파일삭제 성공"+i.getFileName());
				}else {
					System.out.println("파일 삭제 실패");
				}
			}else {
				System.out.println("파일이 존재하지 않습니다.");
			}
		}
		
		//데이터베이스 삭제
		imgService.deleteProdImg(seq);
		Map<Object,Object> m= new HashMap<Object, Object>();
		System.out.println("상품삭제함 controller");
		m.put("ok", 1);
		return m;
	}
	
	//장바구니 추가
			@RequestMapping("/bucketsession")
			public String bucketsession(HttpServletRequest request,Model model,@ModelAttribute("data") Userdata user) {
				System.out.println("=======================================");
				Bucket buc= new Bucket();
				Store store = shopservice.findbyid(Long.parseLong(request.getParameter("storeseq")));
				
				buc.setDeliveryfee(2500);
				buc.setDiscount(Integer.parseInt(request.getParameter("discount")));
				buc.setImgname(request.getParameter("filename"));
				buc.setPrice(Integer.parseInt(request.getParameter("price")));
				buc.setQuantity(Integer.parseInt(request.getParameter("quantity")));
				buc.setItemname(request.getParameter("name"));
				buc.setSellername(store.getStorename());   //나중에 스토에 이름으로 변경
				buc.setUserseq(user.getUserseq());
				buc.setStoreseq(Long.parseLong(request.getParameter("storeseq")));
				buc.setProductseq(Long.parseLong(request.getParameter("productseq")));
				//buc.setBucketoption(option.getOptioncontent()+"   "+option.getOptionname()+"  (+"+option.getOptionprice()+"원)");
				buc.setOptionseq(Long.parseLong(request.getParameter("optionseq")));
				buc.setCondition("bucket");
				buc.setChecked(1);
				service.insertBucket(buc);
				
				return "redirect:newbucketlist?optionseq="+request.getParameter("optionseq");
				
			}

			@RequestMapping("/newbucketlist")
			public String bucketsession1(@ModelAttribute("data") Userdata user,Model model) {
				System.out.println("+++++++++++++++++++++++++++++++");
			
				model.addAttribute("options",opService.findoption());
			
				model.addAttribute("probucket",service.findallbucket(user.getUserseq()));
				return "newbucket";
			}
	
	//수량 변경
	@RequestMapping("/modifydata")
	@ResponseBody
	public Map<Object, Object>  modify(@RequestBody Map<String, String> params,HttpServletRequest request,Bucket  buc) {
		Map<Object, Object> map = new HashMap<Object, Object>(); 
		buc.setBucketseq(Long.parseLong(params.get("bucketseq")));
		buc.setQuantity(Integer.parseInt(params.get("quantity")));
        service.quantitymodify(buc);
        return map;
	}
	
	//장바구니 체크 박스 상태
	@RequestMapping("/checked")
	@ResponseBody
	public Map<Object, Object> checked(@RequestBody Map<String, String> params,HttpServletRequest request,Bucket  buc) {
		Map<Object, Object> map = new HashMap<Object, Object>(); 
		buc.setBucketseq(Long.parseLong(params.get("bucketseq")));
		buc.setChecked(Integer.parseInt(params.get("checkd")));
       service.checked(buc);
        return map;
	}
	
	//목록 삭제 
	@RequestMapping("/removebucket")
	@ResponseBody
	public Map<Object, Object>  removebucket(@RequestBody Map<String, String> params,HttpServletRequest request,Bucket  buc) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		System.out.println(params.get("bucketseq"));
		service.delbucketlist(Long.parseLong(params.get("bucketseq")));
        return map;
	}
	
	public void imgSave(MultipartFile mf,Long prod_seq,Long detailNum) {

        String fileName = mf.getOriginalFilename(); 
        String fileNameExtension = FilenameUtils.getExtension(fileName).toLowerCase(); 
        String destinationFileName = RandomStringUtils.randomAlphanumeric(10) + "." + fileNameExtension; 
        File destinationFile = new File (uploadFileDir+ destinationFileName); 
        //String uploadPath = request.getServletContext().getRealPath("Productimg");
        System.out.println(("실제 파일 업로드 경로 : "+uploadFileDir));
//      System.out.println(mtfRequest.getServletContext().getContextPath());
        destinationFile.getParentFile().mkdirs(); 
                
        ProductImg prodImg = new ProductImg();
        prodImg.setImgnum(prod_seq);
        prodImg.setFileOriginalName(fileName);
        prodImg.setFileName(destinationFileName);
        prodImg.setFileUrl(uploadFileDir);
        prodImg.setDetailnum(detailNum);
        imgService.insertProdImg(prodImg); //file insert
        try {
			mf.transferTo(destinationFile);
			
        } catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
        }
	}
	
//	//결제창 
//	@RequestMapping("/paymentwindow")
//	public String paymentwindow(HttpServletRequest request,Model model) {
//		System.out.println(request.getParameter("totalprice"));
//		System.out.println(request.getParameter("bucketseq"));
//		System.out.println("ddddddd");
//		return "baesong";
//		
//	}

	
	
}

