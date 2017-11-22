package com.websystique.springmvc.dao;
import java.util.List;


import com.websystique.springmvc.model.Submit;


public interface SubmitDao {

	Submit findById(int idSubmission);
	
	Submit findByIdUser(int idUser);
	
	Submit findByIdProject(int idProject);
	
	void save(Submit submit);
	
	//void deleteByUsername(String username);
	
	
	
	List<Submit> findAllSubmits();
	
	
    List<Submit> findAllSubmitsByIdProject(int idProject);
	     
    List<Submit> findAllByUserId(int idUser);
	     
	void deleteById(int idSubmission);

	List<Submit> findAllByUserIAndProjectID(Integer idUser, int idProject);
	
	

}