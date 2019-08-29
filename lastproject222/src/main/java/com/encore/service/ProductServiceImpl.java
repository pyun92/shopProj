package com.encore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.encore.domain.Bucket;
import com.encore.domain.Product;
import com.encore.persistence.BucketRepository;
import com.encore.persistence.ProductRepository;


@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository rep;
	
	@Autowired
	private BucketRepository buc;
	
	@Override
	public void insertProd(Product prod) {
		rep.save(prod);			//물품 등록
	}

	@Override
	public List<Product> getProdList(Long seq) {
		return (List<Product>)rep.findByStoreseq(seq); //작성자 기준으로 출력
	}

	@Override
	public List<Product> getProdListAll() {
		return 	(List<Product>)rep.findAll(); //메인 화면 전체 상품 목록
		
	}
	
	@Override
	public Product getProd(Product prod) {
		return rep.findById(prod.getProductseq()).get();
	}

	@Override
	public void insertBucket(Bucket bucket) {
				buc.save(bucket);
	}

	@Override
	public List<Bucket> findallbucket(Long userseq) {
		return (List<Bucket>)buc.findByUserseq(userseq);
	}
	
	//장바구니 수량 수정시 저장
	@Override
	public void quantitymodify(Bucket data) {
		Bucket bucketdata = buc.findById(data.getBucketseq()).get();
		bucketdata.setQuantity(data.getQuantity());
		buc.save(bucketdata);
		
	}

	@Override
	public void delbucketlist(Long num) {
		buc.deleteById(num);
		
	}
	
	



}
