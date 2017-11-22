package com.websystique.springmvc.dao;

import java.util.List;

import com.websystique.springmvc.model.User;


public interface UserDao {

	User findById(int idUser);
	
	User findBySSO(String username);
	
	void save(User user);
	
	void deleteByUsername(String username);
	
//	void acceptUser(String username);
	
	List<User> findAllUsers();
	
	boolean checkVerify(String username);
	
	long countUser();
	
	List<User> findUsers();

}

