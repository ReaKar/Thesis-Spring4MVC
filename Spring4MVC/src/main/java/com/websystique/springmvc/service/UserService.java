package com.websystique.springmvc.service;

import java.util.List;

import com.websystique.springmvc.model.User;


public interface UserService {
	
	User findById(int id);
	
	User findBySSO(String username);
	
	void saveUser(User user);
	
	void updateUser(User user);
	
	void deleteUserByUsername(String username);

	List<User> findAllUsers(); 
	
	List<User> findUsers(); 
	
	boolean isUsernameUnique(Integer id, String username);
	
	void acceptUser(String username);
	
    boolean checkVerify(String username);
    
    long countUser();

	void registerUser(User user);
	 

}
