package com.websystique.springmvc.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.websystique.springmvc.model.Project;
import com.websystique.springmvc.model.Submit;


@Repository("submitDao")
public class SubmitDaoImpl extends AbstractDao<Integer, Submit> implements SubmitDao {

	static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
	
	public Submit findById(int idSubmission) {
		Submit submit = getByKey(idSubmission);
		
		return submit;
	}

	public Submit findByIdUser(int idUser) {
		Submit submit = getByKey(idUser);
		
		return submit;
	}
	
	public Submit findByIdProject(int idProject) {
		Submit submit = getByKey(idProject);
		
		return submit;
	}

	@SuppressWarnings("unchecked")
	public List<Submit> findAllSubmits() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("when"));
	
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<Submit> submit = (List<Submit>) criteria.list();//to allaksa k apo ekei p m emfanize olous ts xrhstes prosthesa toRestrictions
		
		
		return submit;
	}
	@SuppressWarnings("unchecked")
	public List<Submit> findAllSubmitsByIdProject(int idProject) {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("when"));
	
		Criteria userCriteria = criteria.createCriteria("project");
        userCriteria.add(Restrictions.eq("idProject", idProject));
		List<Submit> submit = (List<Submit>) criteria.list();//to allaksa k apo ekei p m emfanize olous ts xrhstes prosthesa toRestrictions
		
		
		return submit;
	}

	public void save(Submit submit) {
		persist(submit);
	}
	
	public List<Submit> findAllByUserId(int idUser){
		Criteria crit = createEntityCriteria();
        Criteria userCriteria = crit.createCriteria("user");
        userCriteria.add(Restrictions.eq("idUser", idUser));	
		 Criteria criteria = crit.addOrder(Order.desc("when"));

        return (List<Submit>)crit.list();
		
	}
    
	public void deleteById(int idSubmission) {
	 Submit submit =  getByKey(idSubmission);
     delete(submit);
	}

	public List<Submit> findAllByUserIAndProjectID(Integer idUser, int idProject){
		 Criteria crit = createEntityCriteria();
	     Criteria userCriteria = crit.createCriteria("user");
	     userCriteria.add(Restrictions.eq("idUser", idUser));
	     Criteria courseCriteria = crit.createCriteria("project");
	     courseCriteria.add(Restrictions.eq("idProject", idProject));
		 Criteria criteria = crit.addOrder(Order.desc("when"));

	     
	     return (List<Submit>)crit.list();
	}
	
	


}
