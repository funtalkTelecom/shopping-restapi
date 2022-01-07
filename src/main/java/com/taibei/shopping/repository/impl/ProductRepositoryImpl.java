package com.taibei.shopping.repository.impl;

import com.taibei.shopping.repository.ProductRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
@author Xinpu Wang
*/
@Repository
public class ProductRepositoryImpl implements ProductRepositoryCustom {

	@PersistenceContext
	private EntityManager em;
}
