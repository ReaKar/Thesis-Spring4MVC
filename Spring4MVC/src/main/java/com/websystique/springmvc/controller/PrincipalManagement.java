package com.websystique.springmvc.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.websystique.springmvc.model.User;
import com.websystique.springmvc.service.UserService;
import com.websystique.springmvc.service.UserServiceImpl;

public class PrincipalManagement {
	public static String getPrincipal() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}

	public static Integer getPrincipalID() {
		String username = getPrincipal();
		
		UserService service = new UserServiceImpl();
		User user = service.findBySSO(username);
		return user.getIdUser();
		
	}
	
}
