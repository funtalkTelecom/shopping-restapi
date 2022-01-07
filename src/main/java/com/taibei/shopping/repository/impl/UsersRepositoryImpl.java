package com.taibei.shopping.repository.impl;

import com.taibei.shopping.entity.Order;
import com.taibei.shopping.entity.User;
import com.taibei.shopping.repository.UserRepositoryCustom;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
@author Xinpu Wang
*/

@Repository
public class UsersRepositoryImpl implements UserRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;

    public List<User> findUser(int userId){

		String jpql = "SELECT a FROM User a where  a.id= ?1";

		Query query = entityManager.createQuery(jpql).setParameter(1, userId);
		return  query.getResultList();


	}

	public List<Order> findUserOrder(User user, Pageable pageable){

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Order> criteriaQuery = criteriaBuilder.createQuery(Order.class);
		Root<Order> root = criteriaQuery.from(Order.class);
		criteriaQuery.select(root);
		if (user != null) {
			criteriaQuery.where(criteriaBuilder.equal(root.get("user"), user));
		}

		TypedQuery<Order> query = entityManager.createQuery(criteriaQuery).setFlushMode(FlushModeType.COMMIT);

		query.setFirstResult((pageable.getPageNumber() - 1) * pageable.getPageSize());
		query.setMaxResults(pageable.getPageSize());

		return  query.getResultList();
	}


}
