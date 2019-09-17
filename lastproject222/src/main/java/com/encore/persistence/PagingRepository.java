package com.encore.persistence;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.encore.domain.ProductOrder;

public interface PagingRepository extends PagingAndSortingRepository<ProductOrder, Integer>{

	Page<ProductOrder> findByUserseq(Long seq,Pageable pageable);
}
