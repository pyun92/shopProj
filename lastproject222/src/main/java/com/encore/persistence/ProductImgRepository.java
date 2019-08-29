package com.encore.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.encore.domain.ProductImg;

@Repository
public interface ProductImgRepository extends CrudRepository<ProductImg, Long> {
	
	List<ProductImg> findByDetailnum(Long searchKeyword);
	
	List<ProductImg> findByImgnum(Long productNum);
	
//	@Query("SELECT b FROM ProductImg b WHERE b.imgnum=searchKeyword and detailnum=0")  //데표이미지 출력 쿼리
//	List<ProductImg> titleimage(@Param("searchKeyword") String searchKeyword);
}
