package com.encore.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.encore.domain.Advertising;
import com.encore.domain.Product;
import com.encore.domain.ProductImg;
import com.encore.domain.Store;
import com.encore.persistence.AdvertisingRepository;
import com.encore.persistence.LoginRepository;
import com.encore.persistence.ProductImgRepository;
import com.encore.persistence.ProductRepository;
import com.encore.persistence.ReportRepository;
import com.encore.persistence.ShopRepository;

@Service
public class AdvertisingServiceImpl implements AdvertisingService {

	@Autowired
	private AdvertisingRepository rep;
	@Autowired
	private ProductRepository prodrep;
	@Autowired
	private ProductImgRepository prodimgrep;
	@Autowired
	private ShopService storeservice;
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Product> findAdProduct() {
		List<Product>list = null;
		String qstr = "from Product a where a.productseq in (select b.prodseq from Advertising b where b.smallad = 2)";
		Query query = entityManager.createQuery(qstr);
		list = query.getResultList();
		return list;
	}
	
	@Override
	public List<Advertising> findbigad() {
		List<Advertising>list = null;
		String qstr = "from Advertising a where a.bigad=2";
		Query query = entityManager.createQuery(qstr);
		list = query.getResultList();
		return list;
	}
	
	
	@Override
	public List<Advertising> findAll() {
		List<Advertising> list=(List<Advertising>)rep.findAll();
		return list;
	}

	@Override
	public void insertAD(Advertising ad) {
		rep.save(ad);
	}

	@Override
	public void deleteAD(Long num) {
		rep.deleteById(num);
		
	}

	@Override
	public void updateADstat(Long num) {
		Advertising ad1 = rep.findById(num).get();
		 ad1.setStatus("승인");
		 rep.save(ad1);
	}

	@Override
	public Advertising findAdvertising(Long num) {
		return rep.findById(num).get();
	}

	@Override
	public void updatebigAD(Long num) {
		Advertising ad1 = rep.findById(num).get();
		 ad1.setBigad(2);
		 rep.save(ad1);
		
	}
	@Override
	public void cancelbigAD(Long num) {
		Advertising ad1 = rep.findById(num).get();
		 ad1.setBigad(1);
		 rep.save(ad1);
	}	

	@Override
	public void updateSmallAD(Long seq) {
		Advertising ad = rep.findById(seq).get();
		ad.setSmallad(2);
		rep.save(ad);
	}

	@Override
	public void cancleSmallAD(Long seq) {
		Advertising ad = rep.findById(seq).get();
		ad.setSmallad(1);
		rep.save(ad);
		
	}

	@Override
	public List<Product> findbigadprod() {
		List<Product>list = null;
		String qstr = "from Product a where a.productseq in (select b.prodseq from Advertising b)";
		Query query = entityManager.createQuery(qstr);
		list = query.getResultList();
		return list;
	}

	@Override
	public List<ProductImg> findbigadimg() {
		List<ProductImg> list = null;
		String qstr = "from ProductImg a where a.detailnum=0 and a.imgnum in (select c.prodseq from Advertising c where bigad=2)";
		Query query = entityManager.createQuery(qstr);
		list = query.getResultList();
		return list;
	}

	@Override
	public List<Store> findadstore() {
		List<Store> list = null;
		String qstr = "from Store a where a.storeseq in (select b.storeseq from Product b where b.productseq in (select c.prodseq from Advertising c))";
		Query query = entityManager.createQuery(qstr);
		list = query.getResultList();
		return list;
	}
	
}
