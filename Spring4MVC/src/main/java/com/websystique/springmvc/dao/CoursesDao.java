package com.websystique.springmvc.dao;

import java.util.List;

import com.websystique.springmvc.model.Courses;

public interface CoursesDao {
	
Courses findById(int idCourse);
	
	Courses findByTitle(String title);
	
	void save(Courses course);
	
	void deleteById(int idCourse);
	
	void activeCourse(int idCourse);
	
	List<Courses> findAllCourses();
	List<Courses> findAll();


	List<Courses> findActiveCourses();

}
