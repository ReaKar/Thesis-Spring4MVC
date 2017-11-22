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

@Repository("coursesDao")
public class CoursesDaoImpl extends AbstractDao<Integer, Courses> implements CoursesDao {

	static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

	public Courses findById(int idCourse) {
		Courses course = getByKey(idCourse);
		// if(course!=null){
		// Hibernate.initialize(course.getUserProfiles());
		// }
		return course;
	}

	public Courses findByTitle(String title) {
		logger.info("title : {}", title);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("title", title));
		Courses course = (Courses) crit.uniqueResult();
		if (course != null) {
			Hibernate.initialize(course.getProjectList()); // eager taktikh
		}
		// if(course!=null){
		// Hibernate.initialize(course.getUserProfiles());
		// }
		return course;
	}

	@SuppressWarnings("unchecked")
	public List<Courses> findAllCourses() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("title"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		List<Courses> courses = (List<Courses>) criteria.list();

		// No need to fetch userProfiles since we are not showing them on list page. Let
		// them lazy load.
		// Uncomment below lines for eagerly fetching of userProfiles if you want.
		/*
		 * for(User user : users){ Hibernate.initialize(user.getUserProfiles()); }
		 */
		return courses;
	}

	public void save(Courses course) {
		persist(course);
	}

	public void deleteById(int idCourse) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("idCourse", idCourse));
		Courses course = (Courses) crit.uniqueResult();
		delete(course);
	}

	public void activeCourse(int idCourse) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("idCourse", idCourse));
		Courses course = (Courses) crit.uniqueResult();
		if (course.isActive()) {
			course.setActive(false);
		} else {
			course.setActive(true);

		}

		update(course);
	}

	@Override
	public List<Courses> findActiveCourses() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("title"));
		criteria.add(Restrictions.eq("active", true));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		List<Courses> courses = (List<Courses>) criteria.list();

		// No need to fetch userProfiles since we are not showing them on list page. Let
		// them lazy load.
		// Uncomment below lines for eagerly fetching of userProfiles if you want.
		/*
		 * for(User user : users){ Hibernate.initialize(user.getUserProfiles()); }
		 */
		return courses;
	}

	public List<Courses> findAll() {
		Criteria crit = createEntityCriteria();
		return (List<Courses>) crit.list();

	}

}
