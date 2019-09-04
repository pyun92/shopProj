package com.encore.persistence;

import java.sql.ResultSet;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.encore.domain.Store;

@Repository
public interface SearchStoreRepository extends JpaRepository<Store, Long>{
	@Query(value = "select max(ceil(rownum/3)) as page from (select * from Store order by rownum desc) ",nativeQuery = true)
	Integer gettotalpage();
}
