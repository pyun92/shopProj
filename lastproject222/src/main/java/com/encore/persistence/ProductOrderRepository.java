package com.encore.persistence;

import org.springframework.data.repository.CrudRepository;

import com.encore.domain.ProductOrder;

public interface ProductOrderRepository extends CrudRepository<ProductOrder, Long> {

}
