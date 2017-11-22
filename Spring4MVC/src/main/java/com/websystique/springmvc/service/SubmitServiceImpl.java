package com.websystique.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.SubmitDao;
import com.websystique.springmvc.model.Submit;


@Service("submitService")
@Transactional
public class SubmitServiceImpl implements SubmitService{

	@Autowired
	private SubmitDao dao;

	//@Autowired
   // private PasswordEncoder passwordEncoder;
	
	public Submit findById(int idSubmission) {
		return dao.findById(idSubmission);
	}
	
	public Submit findByIdUser(int idUser) {
		return dao.findById(idUser);
	}
	public Submit findByIdProject(int idUser) {
		return dao.findById(idUser);
	}


	

	public void saveSubmits(Submit submit) {
		dao.save(submit);
	}

	/*
	 * Since the method is running with Transaction, No need to call hibernate update explicitly.
	 * Just fetch the entity from db and update it with proper values within transaction.
	 * It will be updated in db once transaction ends. 
	 */
	public void updateSubmit(Submit submit) {
		Submit entity = dao.findById(submit.getIdSubmission());
		if(entity!=null){
			//entity.setIdUser(submit.getIdUser());
			
		//	entity.setIdProject(submit.getIdProject());
			entity.setFilepath(submit.getFilepath());
			entity.setWhen(submit.getWhen());
			
		}
	}


	public List<Submit> findAllSubmits() {
		return dao.findAllSubmits();
	}
	public List<Submit> findAllSubmitsByIdProject(int idProject){
		return dao.findAllSubmitsByIdProject(idProject);
	}

	
	 public List<Submit> findAllByUserId(int idUser) {
	        return dao.findAllByUserId(idUser);
	    }
	 
	 public void deleteById(int idSubmission){
	        dao.deleteById(idSubmission);
	    }

	@Override
	public List<Submit> findAllByUserIAndProjectID(Integer idUser, int idProject) {
		List<Submit> submits = dao.findAllByUserIAndProjectID(idUser, idProject);
 
		return submits;
	}
}
