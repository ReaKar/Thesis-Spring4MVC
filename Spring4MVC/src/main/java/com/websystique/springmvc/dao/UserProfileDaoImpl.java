package com.websystique.springmvc.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.websystique.springmvc.model.UserProfile;



@Repository("userProfileDao")
public class UserProfileDaoImpl extends AbstractDao<Integer, UserProfile>implements UserProfileDao{

	public UserProfile findById(int idRole) {
		return getByKey(idRole);
	}
	
	@SuppressWarnings("unchecked")
	public List<UserProfile> findAll(){
		Criteria crit = createEntityCriteria();
		crit.addOrder(Order.asc("description"));
		return (List<UserProfile>)crit.list();
	}

	@Override
	public UserProfile findByDescription(String description) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("description", description));
		return (UserProfile) crit.uniqueResult();
	}
	
}
