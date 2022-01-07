package com.taibei.shopping.repository;

import com.taibei.shopping.entity.SearchLog;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
@author Xinpu Wang
*/
public interface SearchLogRepository extends JpaRepository<SearchLog, Integer>, JpaSpecificationExecutor<SearchLog> {


    @Query("select distinct searchKey from SearchLog where userName=?1 order by  searchTime Desc")
    public Slice<Object> findByUserNameOrderBySearchTimeDesc(String userName, Pageable pageable);



    long deleteByUserNameAndSearchKey(String userName,String searchKey);


}
