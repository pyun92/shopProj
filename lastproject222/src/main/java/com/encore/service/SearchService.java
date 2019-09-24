package com.encore.service;

import java.sql.ResultSet;
import java.util.List;import org.omg.CORBA.INTERNAL;

import com.encore.domain.Product;
import com.encore.domain.ProductImg;
import com.encore.domain.Store;

public interface SearchService{
	List<Store> searchStore(String word);
	List<Product> searchProd(String word);
	List<Store> searchStorebycate(String word);
	List<Product> searchProdbycate(String word);
	int getTotalPage();
//	List<Store> getpagestore(int page);
}
