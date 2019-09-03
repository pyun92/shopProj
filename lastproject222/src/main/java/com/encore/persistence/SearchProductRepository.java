package com.encore.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.encore.domain.Product;

@Repository
public interface SearchProductRepository extends JpaRepository<Product,Long>{

} 
