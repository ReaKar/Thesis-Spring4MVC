package com.websystique.springmvc.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.CoursesDao;
import com.websystique.springmvc.model.Courses;


@Service("coursesService")
@Transactional
public class CoursesServiceImpl implements CoursesService{

	@Autowired
	private CoursesDao dao;

	//@Autowired
   // private PasswordEncoder passwordEncoder;
	
	public Courses findById(int idCourse) {
		return dao.findById(idCourse);
	}

	public Courses findByTitle(String title) {
		Courses course = dao.findByTitle(title);
		return course;
	}

	public void saveCourses(Courses course) {
		//course.setPassword(passwordEncoder.encode(course.getPassword()));
		dao.save(course);
	}

	/*
	 * Since the method is running with Transaction, No need to call hibernate update explicitly.
	 * Just fetch the entity from db and update it with proper values within transaction.
	 * It will be updated in db once transaction ends. 
	 */
	public void updateCourses(Courses course) {
		Courses entity = dao.findById(course.getIdCourse());
		if(entity!=null){
			entity.setSemester(course.getSemester());
			//entity.setProjectList(course.getProjectList());
			
			entity.setTitle(course.getTitle());
			entity.setDescription(course.getDescription());
			entity.setFilepath(course.getFilepath());
			entity.setActive(course.isActive());
			entity.setOpendate(course.getOpendate());
			entity.setClosedate(course.getClosedate());
		}
	}

	
	public void deleteCourseById(int idCourse) {
		dao.deleteById(idCourse);
	}
	
	public void activeCourse(int idCourse) {
		dao.activeCourse(idCourse);
	}

	public List<Courses> findAllCourses() {
		return dao.findAllCourses();
	}

	@Override
	public List<Courses> findActiveCourses() {
		return dao.findActiveCourses();
	}
	
	public List<Courses> findAll() {
		return dao.findAll();
	}

	
}
