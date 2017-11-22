package com.websystique.springmvc.dao;



import java.util.List;

import com.websystique.springmvc.model.Project;
import com.websystique.springmvc.model.*;

public interface ProjectDao {
	
    Project findById(int idProject);
	
	Project findByTitle(String title);
	
	void save(Project project);
	
	void deleteById(int idProject);
	
	List<Project> findAllProject();
	
	
	List<Project> findAllCourses();
	


}