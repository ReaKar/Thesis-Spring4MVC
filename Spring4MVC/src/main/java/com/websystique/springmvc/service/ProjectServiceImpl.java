package com.websystique.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.ProjectDao;
import com.websystique.springmvc.model.Project;


@Service("projectService")
@Transactional
public class ProjectServiceImpl implements ProjectService{

	@Autowired
	private ProjectDao dao;

	//@Autowired
   // private PasswordEncoder passwordEncoder;
	
	public Project findById(int idProject) {
		return dao.findById(idProject);
	}

	public Project findByTitle(String title) {
		Project project = dao.findByTitle(title);
		return project;
	}

	public void saveProject(Project project) {
		//course.setPassword(passwordEncoder.encode(course.getPassword()));
		dao.save(project);
	}

	/*
	 * Since the method is running with Transaction, No need to call hibernate update explicitly.
	 * Just fetch the entity from db and update it with proper values within transaction.
	 * It will be updated in db once transaction ends. 
	 */
	public void updateProject(Project project) {
		Project entity = dao.findById(project.getIdProject());
		if(entity!=null){
			//entity.setCourselist(project.getCourselist());
		entity.setIdCourse(project.getIdCourse());
			
			entity.setTitle(project.getTitle());
			entity.setDescription(project.getDescription());
			entity.setOpendate(project.getOpendate());
			entity.setClosedate(project.getClosedate());
		}
	}

	
	public void deleteProjectById(int idProject) {
		dao.deleteById(idProject);
	}
	


	public List<Project> findAllProject() {
		return dao.findAllProject();
	}
	
	public List<Project> findAllCourses() {
		return dao.findAllCourses();
	}

	
}
