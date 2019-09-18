package com.encore.persistence;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.encore.domain.ProductOrder;

public interface ProductOrderRepository extends CrudRepository<ProductOrder, Long> {

	@Query(value ="select * from Product_Order where userseq=1? order by orderseq DESC  ",nativeQuery = true)
	List<ProductOrder> findByUserseqOrder(Long seq);
	
	@Query(value ="select * from Product_Order where userseq=1? and orderstate='배송완료'",nativeQuery = true)
	List<ProductOrder> findByUserseqAndOrderState(Long seq);
	
	List<ProductOrder> findByUserseq(Long seq);
	
}
