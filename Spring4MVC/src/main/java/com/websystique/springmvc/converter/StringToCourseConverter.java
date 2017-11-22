package com.websystique.springmvc.converter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.websystique.springmvc.model.Courses;
import com.websystique.springmvc.service.CoursesService;
import com.websystique.springmvc.service.UserProfileService;

@Component
public class StringToCourseConverter implements Converter<Object, Courses>{

	static final Logger logger = LoggerFactory.getLogger(RoleToUserProfileConverter.class);
	
	@Autowired
	CoursesService courseService;

	/**
	 * Gets UserProfile by Id
	 * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
	 */
	public Courses convert(Object element) {
		Integer id = Integer.parseInt((String)element); //k t string t kanei int
		Courses course = courseService.findById(id);   // kai kanto object
		return course;
	}
}