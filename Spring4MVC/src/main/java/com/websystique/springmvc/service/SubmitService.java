package com.websystique.springmvc.service;

import java.util.List;

import com.websystique.springmvc.model.Submit;


public interface SubmitService {
	
	Submit findById(int idSubmission);
	
	Submit findByIdUser(int idUser);
	
	Submit findByIdProject(int idProject);
	
	void saveSubmits(Submit submit);
	
	void updateSubmit(Submit submit);
	


	List<Submit> findAllSubmits(); 
//	-----------------------------------
	List<Submit> findAllByUserId(int idUser);
    List<Submit> findAllSubmitsByIdProject(int idProject);
	
	void deleteById(int idSubmission);

	List<Submit> findAllByUserIAndProjectID(Integer idUser, int idProject);
	

	
   

}