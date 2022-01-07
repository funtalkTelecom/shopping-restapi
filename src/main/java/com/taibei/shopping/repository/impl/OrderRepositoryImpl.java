package com.taibei.shopping.repository.impl;

import com.taibei.shopping.repository.OrderRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
@author Xinpu Wang
*/
@Repository
public class OrderRepositoryImpl implements OrderRepositoryCustom {

	@PersistenceContext
	private EntityManager em;
}
