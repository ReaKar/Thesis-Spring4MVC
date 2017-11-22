package com.websystique.springmvc.service;


import java.util.List;

import com.websystique.springmvc.model.Project;


public interface ProjectService {
	
	Project findById(int idProject);
	
	Project findByTitle(String title);
	
	void saveProject(Project project);
	
	void updateProject(Project project);
	
	void deleteProjectById(int idProject);

	List<Project> findAllProject(); 
	
	List<Project> findAllCourses(); 
	

	

}