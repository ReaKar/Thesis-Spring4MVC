package com.websystique.springmvc.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.websystique.springmvc.model.Courses;
import com.websystique.springmvc.model.Project;
import com.websystique.springmvc.model.Submit;

@Repository("projectDao")
public class ProjectDaoImpl extends AbstractDao<Integer, Project> implements ProjectDao {

	static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
	
	public Project findById(int idProject) {
		Project project = getByKey(idProject);
		//if(course!=null){
			//Hibernate.initialize(course.getUserProfiles());
		//}
		return project;
	}

	public Project findByTitle(String title) {
		logger.info("title : {}", title);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("title", title));
		Project project = (Project)crit.uniqueResult();
		//if(course!=null){
		//	Hibernate.initialize(course.getUserProfiles());
		//}
		return project;
	}

	@SuppressWarnings("unchecked")
	public List<Project> findAllProject() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("title"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<Project> project = (List<Project>) criteria.list();
		
		// No need to fetch userProfiles since we are not showing them on list page. Let them lazy load. 
		// Uncomment below lines for eagerly fetching of userProfiles if you want.
		/*
		for(User user : users){
			Hibernate.initialize(user.getUserProfiles());
		}*/
		return project;
	}

	public void save(Project project) {
		persist(project);
	}

	public void deleteById(int idProject) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("idProject", idProject));
		Project project = (Project)crit.uniqueResult();
		delete(project);
	}
	

	
   public List<Project> findAllCourses(){
	   Criteria crit = createEntityCriteria();
       Criteria userCriteria = crit.createCriteria("courses");
     //  userCriteria.add(Restrictions.eq("idUser", id));
       return (List<Project>)crit.list();
		
		
       
   }
   
   

	


}
