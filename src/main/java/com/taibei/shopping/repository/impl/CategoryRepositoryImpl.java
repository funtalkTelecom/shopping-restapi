package com.taibei.shopping.repository.impl;

import com.taibei.shopping.repository.CategoryRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
@author Xinpu Wang
*/
@Repository
public class CategoryRepositoryImpl implements CategoryRepositoryCustom {

	@PersistenceContext
	private EntityManager em;
}
