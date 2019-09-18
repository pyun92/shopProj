package com.encore.service;

import java.util.List;

import com.encore.domain.Product;
import com.encore.domain.ProductImg;

public interface ProductImgService {
	void insertProdImg(ProductImg prodImg);
	
	List<ProductImg> getProdImg(Product prod); 
	
	List<ProductImg> getProdImg(Long seq);
	
	List<ProductImg> getProdImgs(); //전체 이미지 
	
	List<ProductImg> getDetailNum(); //대표 이미지 
	
	ProductImg getDetailNum(Long seq);
	
	ProductImg getDetail(Long seq,Long detail);//수정에서 서버사진 삭제할때 쓸거
	
	void deleteProd(Long seq);//상품삭제시 이미지 삭제'
	
	void deleteProdImg(Long seq,Long detailNum);//상품수정시 서버에 올라온 이미지 삭제
}
