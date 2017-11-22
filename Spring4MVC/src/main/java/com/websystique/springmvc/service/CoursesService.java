package com.websystique.springmvc.service;

import java.util.List;

import com.websystique.springmvc.model.Courses;


public interface CoursesService {
	
	Courses findById(int idCourse);
	
	Courses findByTitle(String title);
	
	void saveCourses(Courses course);
	
	void updateCourses(Courses course);
	
	void deleteCourseById(int idCourse);

	List<Courses> findAllCourses(); 
	
	List<Courses> findActiveCourses();
	
    List<Courses> findAll();
	
   void activeCourse(int idCourse);

}