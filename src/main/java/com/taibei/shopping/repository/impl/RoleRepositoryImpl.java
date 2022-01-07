package com.taibei.shopping.repository.impl;

import com.taibei.shopping.repository.RoleRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
@author Xinpu Wang
*/
@Repository
public class RoleRepositoryImpl implements RoleRepositoryCustom {

	@PersistenceContext
	private EntityManager em;
}
