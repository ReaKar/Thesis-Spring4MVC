package com.websystique.springmvc.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.websystique.springmvc.model.Submit;
import com.websystique.springmvc.model.User;
import com.websystique.springmvc.model.UserProfile;



@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {

	static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
	
	public User findById(int idUser) {
		User user = getByKey(idUser);
		if(user!=null){
			Hibernate.initialize(user.getUserProfiles());
		}
		return user;
	}

	public User findBySSO(String username) {
		logger.info("username : {}", username);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("username", username));
		User user = (User)crit.uniqueResult();
		if(user!=null){
			Hibernate.initialize(user.getUserProfiles());
		}
		return user;
	}

	@SuppressWarnings("unchecked")
	public List<User> findAllUsers() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("lastName"));
		criteria.add(Restrictions.eq("verified", false));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<User> users = (List<User>) criteria.list();//to allaksa k apo ekei p m emfanize olous ts xrhstes prosthesa toRestrictions
		
		// No need to fetch userProfiles since we are not showing them on list page. Let them lazy load. 
		// Uncomment below lines for eagerly fetching of userProfiles if you want.
		/*
		for(User user : users){
			Hibernate.initialize(user.getUserProfiles());
		}*/
		return users;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<User> findUsers() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("lastName"));
		criteria.add(Restrictions.eq("verified", true));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<User> users = (List<User>) criteria.list();//to allaksa k apo ekei p m emfanize olous ts xrhstes prosthesa toRestrictions
//		for(User user : users){
//			Hibernate.initialize(user.getUserProfiles());
//		}
		
		return users;
	}

	public void save(User user) {
		persist(user);
	}

	public void deleteByUsername(String username) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("username", username));
		User user = (User)crit.uniqueResult();
		delete(user);
	}
	
	
	
	 public boolean checkVerify(String username) {
		 Criteria crit = createEntityCriteria();
	     crit.add(Restrictions.eq("username", username));
		 User user = (User)crit.uniqueResult();
		 if (user.isVerified()) {
			 return true;
			 
		 }else {
			 return false;
		 }
	}
	 
	 public long countUser() {
		 Criteria criteria = createEntityCriteria().setProjection(Projections.rowCount());
		 criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		 long resultCount = (long)criteria.uniqueResult();
		 
		 return resultCount;
	 }
	 
	


}
