package com.encore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.encore.domain.Product;
import com.encore.domain.ProductImg;
import com.encore.persistence.ProductImgRepository;

@Service
public class ProductImgServiceImpl implements ProductImgService {

	@Autowired
	private ProductImgRepository rep;
	
	//이미지 저장
	@Override
	public void insertProdImg(ProductImg prodImg) {
		rep.save(prodImg);
	}
	
	//상세보기에서 사진 뿌려주는거
	@Override
	public List<ProductImg> getProdImgs() {
		return (List<ProductImg>)rep.findAll();
	}
	
	//메인 화면에서 대표이지미 출력
	@Override
	public List<ProductImg> getDetailNum() {
		// TODO Auto-generated method stub
		return (List<ProductImg>)rep.findByDetailnum(0L);
	}

	
	@Override
	public List<ProductImg> getProdImg(Product prod) {
		List<ProductImg> list=rep.findByImgnum(prod.getProductseq());
		System.out.println("이미지 "+list.get(0).getFileName());
		return list;
	}
}
