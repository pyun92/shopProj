package com.encore.service;

import java.util.List;

import com.encore.domain.Product;
import com.encore.domain.Store;

public interface SearchService{
	List<Store> searchStore(String word);
	List<Product> searchProd(String word);
}
