package com.encore.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.encore.domain.Product;
import com.encore.domain.ProductImg;
import com.encore.domain.Store;
import com.encore.persistence.ProductImgRepository;
import com.encore.persistence.SearchProductRepository;
import com.encore.persistence.SearchStoreRepository;

@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	private SearchProductRepository prodrep;

	@Autowired
	private SearchStoreRepository shoprep;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Store> searchStore(String word) {
		List<Store> list = null;
		String qstr = "from Store WHERE STORENAME like :sname or storetype like :stype";
		Query query = entityManager.createQuery(qstr).setParameter("sname", "%" + word + "%").setParameter("stype",
				"%" + word + "%");
		list = query.getResultList();
		return list;
	}

	@Override
	public List<Product> searchProd(String word) {
		List<Product> list = null;
		String qstr = "from Product WHERE proname like :pname or categorybig like :pcategorybig or categorysmall like :pcategorysmall";
		Query query = entityManager.createQuery(qstr).setParameter("pname", "%" + word + "%")
				.setParameter("pcategorybig", "%" + word + "%").setParameter("pcategorysmall", "%" + word + "%");
		list = query.getResultList();
//		확인용 프린트ln
//		for(Product pp :list) { 
//			System.out.println(pp.getName());
//		}

		return list;

	}

	@Override
	public int getTotalPage() {
		int n = shoprep.gettotalpage()==null?0:shoprep.gettotalpage();
		return n;
	}

	@Override
	public List<Store> searchStorebycate(String word) {
		List<Store> list = null;
		String qstr = "from Store WHERE storetype = :stype";
		Query query = entityManager.createQuery(qstr).setParameter("stype", word );
		list = query.getResultList();
		return list;
	}

	@Override
	public List<Product> searchProdbycate(String word) {
		List<Product> list = null;
		String qstr = "from Product WHERE categorybig = :pcategorybig or categorysmall = :pcategorysmall";
		Query query = entityManager.createQuery(qstr).setParameter("pcategorybig",  word ).setParameter("pcategorysmall",  word );
		list = query.getResultList();
		return list;
	}
}
