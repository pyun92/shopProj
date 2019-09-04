package com.encore.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.encore.domain.ProductOrder;

public interface ProductOrderRepository extends CrudRepository<ProductOrder, Long> {

	List<ProductOrder> findByUserseq(Long seq);
}
