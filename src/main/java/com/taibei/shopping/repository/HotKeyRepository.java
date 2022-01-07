package com.taibei.shopping.repository;

import com.taibei.shopping.entity.HotKey;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
@author Xinpu Wang
*/
public interface HotKeyRepository extends JpaRepository<HotKey, Integer>, JpaSpecificationExecutor<HotKey> {


}
