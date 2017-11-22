package com.websystique.springmvc.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.UserDao;
import com.websystique.springmvc.dao.UserProfileDao;
import com.websystique.springmvc.model.User;
import com.websystique.springmvc.model.UserProfile;


@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao dao;
	
	@Autowired
	private UserProfileDao updao;

	@Autowired
    private PasswordEncoder passwordEncoder;
	
	public User findById(int id) {
		return dao.findById(id);
	}

	public User findBySSO(String username) {
		User user = dao.findBySSO(username);
		return user;
	}

	public void saveUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		dao.save(user);
	}

	/*
	 * Since the method is running with Transaction, No need to call hibernate update explicitly.
	 * Just fetch the entity from db and update it with proper values within transaction.
	 * It will be updated in db once transaction ends. 
	 */
	public void updateUser(User user) {
		User entity = dao.findById(user.getIdUser());
		if(entity!=null){
			entity.setUsername(user.getUsername());
			if(!user.getPassword().equals(entity.getPassword())){
				entity.setPassword(passwordEncoder.encode(user.getPassword()));
			}
			entity.setFirstName(user.getFirstName());
			entity.setLastName(user.getLastName());
			entity.setEmail(user.getEmail());
			entity.setUserProfiles(user.getUserProfiles());
			
			entity.setVerified(user.isVerified());
		
		}
	}

	
	public void deleteUserByUsername(String username) {
		dao.deleteByUsername(username);
	}
	
	public void acceptUser(String username) {
		User user = dao.findBySSO(username);

		UserProfile role = updao.findById(2);
		
		user.getUserProfiles().clear();
		user.getUserProfiles().add(role); //role=user
		user.setVerified(true);
		
//		updateUser.(user);
//		dao.acceptUser(username);
	}

	public List<User> findAllUsers() {
		return dao.findAllUsers();
	}
	
	public List<User> findUsers() {
		return dao.findUsers();
	}

	public boolean isUsernameUnique(Integer id, String username) {
		User user = findBySSO(username);
		return ( user == null || ((id != null) && (user.getUsername().equals(username))));
	}
	
	
	 public boolean checkVerify(String username) {
		 return dao.checkVerify(username);
	 }
	 
	 public long countUser() {
		 return dao.countUser();
	 }

	@Override
	public void registerUser(User user) {
		UserProfile r = updao.findById(3);
		Set<UserProfile> roles = new HashSet<>();
		roles.add(r);
		
		user.setUserProfiles(roles);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		dao.save(user);
		
	}
	
}
