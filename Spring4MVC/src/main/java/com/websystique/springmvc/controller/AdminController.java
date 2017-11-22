package com.websystique.springmvc.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.websystique.springmvc.configuration.AppInitializer;
import com.websystique.springmvc.model.Courses;
import com.websystique.springmvc.model.FileBucket;
import com.websystique.springmvc.model.Project;
import com.websystique.springmvc.model.Submit;
import com.websystique.springmvc.model.User;
import com.websystique.springmvc.model.UserProfile;

import com.websystique.springmvc.service.CoursesService;
import com.websystique.springmvc.service.ProjectService;
import com.websystique.springmvc.service.SubmitService;
import com.websystique.springmvc.service.UserProfileService;
import com.websystique.springmvc.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	UserService userService;
	@Autowired
	CoursesService coursesService;
	
	@Autowired
	ProjectService projectService;
	
	@Autowired
	SubmitService submitService;
	

	@Autowired
	UserProfileService userProfileService;

	@Autowired
	MessageSource messageSource;

	@Autowired
	PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;
	
	@RequestMapping(value = { "users" }, method = RequestMethod.GET)
	public String listUsers (ModelMap model) {
        long number= userService.countUser(); //moy ypologizei poses sunolika eggrafes exw
		List<User> users = userService.findAllUsers(); //olous tous mh egkekrimenous xrhstes
		model.addAttribute("users", users);
		model.addAttribute("loggedinuser", PrincipalManagement.getPrincipal());
		model.addAttribute("number",number);
		return "userslist";
	}
	
	
	
	@RequestMapping(value = { "allusers" }, method = RequestMethod.GET)
	public String listAllUsers (ModelMap model) {
        long number= userService.countUser(); //moy ypologizei poses sunolika eggrafes exw
		List<User> users = userService.findUsers(); //olous ts egkekrimenous
		model.addAttribute("users", users);
		model.addAttribute("loggedinuser", PrincipalManagement.getPrincipal());
		model.addAttribute("number",number);
		return "allusers";
	}
	
	@RequestMapping(value = { "home" }, method = RequestMethod.GET)
	public String Home(ModelMap model) {
		model.addAttribute("loggedinuser",  PrincipalManagement.getPrincipal());
		return "homeadmin";
	}
	
	@RequestMapping(value = { "edit-user-{username}" }, method = RequestMethod.GET)
	public String editUser(@PathVariable String username, ModelMap model) {
		User user = userService.findBySSO(username);
		model.addAttribute("user", user);
		model.addAttribute("edit", true);
		model.addAttribute("loggedinuser", PrincipalManagement.getPrincipal());
		return "registration";
	}

	@RequestMapping(value = { "edit-user-{username}" }, method = RequestMethod.POST)
	public String updateUser(@Valid User user, BindingResult result, ModelMap model, @PathVariable String username) {

		if (result.hasErrors()) {
			return "registration";
		}
		
		
		 //Uncomment below 'if block' if you WANT TO ALLOW UPDATING SSO_ID in UI which
		 // is a unique key to a User. 
//		if (!userService.isUsernameUnique(user.getIdUser(), user.getUsername())) {
//			FieldError ssoError = new FieldError("user", "username", messageSource.getMessage("non.unique.username",
//					new String[] { user.getUsername() }, Locale.getDefault()));
//			result.addError(ssoError);
//			return "registration";
//		}
	
		user.setVerified(true);
		
		userService.updateUser(user);


		model.addAttribute("success",
				"User " + user.getFirstName() + " " + user.getLastName() + " updated successfully");
		model.addAttribute("loggedinuser", PrincipalManagement.getPrincipal());
		return "registrationsuccess";
	}
	
	@RequestMapping(value = { "newuser" }, method = RequestMethod.GET)
	public String newUser(ModelMap model) {
		User user = new User();
		model.addAttribute("user", user);
		model.addAttribute("edit", false);
		model.addAttribute("loggedinuser", PrincipalManagement.getPrincipal());
		return "registration";
	}

	@RequestMapping(value = { "newuser" }, method = RequestMethod.POST)
	public String saveUser(@Valid User user, BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
			return "registration";
		}

		/*
		 * Preferred way to achieve uniqueness of field [sso] should be implementing
		 * custom @Unique annotation and applying it on field [sso] of Model class
		 * [User].
		 * 
		 * Below mentioned peace of code [if block] is to demonstrate that you can fill
		 * custom errors outside the validation framework as well while still using
		 * internationalized messages.
		 * 
		 */
		if (!userService.isUsernameUnique(user.getIdUser(), user.getUsername())) {
			FieldError ssoError = new FieldError("user", "username", messageSource.getMessage("non.unique.username",
					new String[] { user.getUsername() }, Locale.getDefault()));
			result.addError(ssoError);
			return "registration";
		}

		userService.saveUser(user);

		model.addAttribute("success",
				"User " + user.getFirstName() + " " + user.getLastName() + " registered successfully");
		model.addAttribute("loggedinuser", PrincipalManagement.getPrincipal());
		// return "success";
		return "registrationsuccess";
	}
	
	@RequestMapping(value = { "delete-user-{username}" }, method = RequestMethod.GET)
	public String deleteUser(@PathVariable String username) {
		userService.deleteUserByUsername(username);
		return "redirect:/admin/allusers";
	}

	@RequestMapping(value = { "accept-user-{username}" }, method = RequestMethod.GET)
	public String findUser(@PathVariable String username) {
		userService.acceptUser(username);
		return "redirect:/admin/users";
	}
	
	@RequestMapping(value = { "accept-all" }, method = RequestMethod.GET)
	public String AcceptAllUsers(ModelMap model) {
		List<User> users = userService.findAllUsers();
		for (User str : users) {
		userService.acceptUser(str.getUsername());
		}
		return "redirect:/admin/users";
	}
	
	@RequestMapping(value = { "delete-all" }, method = RequestMethod.GET)
	public String DeleteAllUsers(ModelMap model) {
		List<User> users = userService.findUsers();

		

		
	  UserProfile role = userProfileService.findById(1);

		 

		
			
		for (User str : users) {
//			Set<UserProfile> a= str.getUserProfiles();
//			userProfiles.add(str.getUserProfiles());
			//if(str.getUserProfiles()==userProfile) {
		//	Set<UserProfile> a=str.getUserProfiles();
		//	if(str.getUserProfiles().(role.getDescription())) {
			   
			//}else {
			
		    userService.deleteUserByUsername(str.getUsername());
			//}		
		  //	}
		}
		return "redirect:/admin/allusers";
	}
	
//              -----------------COURSES----------------------------------
	@RequestMapping(value = { "delete-courses-{idCourse}" }, method = RequestMethod.GET)
	public String deleteCourses(@PathVariable int idCourse) {
		coursesService.deleteCourseById(idCourse);
		return "redirect:/admin/courseslist";
	} 
	
	@RequestMapping(value = { "createcourse" }, method = RequestMethod.GET)
	public String newCourse(ModelMap model) {
		Courses courses = new Courses();
		model.addAttribute("courses", courses);
		model.addAttribute("edit", false);
		model.addAttribute("loggedinuser", PrincipalManagement.getPrincipal());
		return "createcourse";
	}
	
	@RequestMapping(value = { "createcourse" }, method = RequestMethod.POST)
	public String saveCourse(@Valid Courses courses, BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
			return "createcourse";
		}	

		courses.setFilepath(AppInitializer.LOCATION);		
		
		coursesService.saveCourses(courses);

		model.addAttribute("success",
				"Course " + courses.getTitle() + " " + " registered successfully");
		model.addAttribute("loggedinuser", PrincipalManagement.getPrincipal());
		// return "success";
		return "registrationsuccess";
	}
	
	//epistrefei lista mathimatwn
	@RequestMapping(value = { "courseslist" }, method = RequestMethod.GET)
	public String Courseslist(ModelMap model) {

				List<Courses> courses = coursesService.findAllCourses();
				model.addAttribute("courses", courses);
				model.addAttribute("loggedinuser", PrincipalManagement.getPrincipal());
				return "courses";
		}
	
	@RequestMapping(value = { "edit-courses-{idCourse}" }, method = RequestMethod.GET)
	public String editCourse(@PathVariable Integer idCourse, ModelMap model) {
		Courses courses = coursesService.findById(idCourse);
		model.addAttribute("courses", courses);
		model.addAttribute("edit", true);
		model.addAttribute("loggedinuser", PrincipalManagement.getPrincipal());
		return "createcourse";
	}

	@RequestMapping(value = { "edit-courses-{idCourse}" }, method = RequestMethod.POST)
	public String updateCourse(@Valid  Courses courses, BindingResult result, ModelMap model,@PathVariable Integer idCourse) {

		if (result.hasErrors()) {
			return "createcourse";
		}
		

		coursesService.updateCourses(courses);

		model.addAttribute("success",
				"Course " + courses.getTitle() + " " + courses.getDescription() + " updated successfully");
		model.addAttribute("loggedinuser", PrincipalManagement.getPrincipal());
		return "registrationsuccess";
	}
   
	
	@RequestMapping(value = { "active-courses-{idCourse}" }, method = RequestMethod.GET)
	public String activecourse(@PathVariable Integer idCourse) {
		coursesService.activeCourse(idCourse);
		return "redirect:/admin/courseslist";
	}
	
	@RequestMapping(value = { "inactive-courses-{idCourse}" }, method = RequestMethod.GET)
	public String inactivecourse(@PathVariable Integer idCourse) {
		coursesService.activeCourse(idCourse);
		return "redirect:/admin/courseslist";
	}
	
	
	// LISTA PROJECT
		@RequestMapping(value = { "projects" }, method = RequestMethod.GET)
		public String Projectlist(ModelMap model) {

				List<Project> project = projectService.findAllProject();
				model.addAttribute("project", project);
				model.addAttribute("loggedinuser", PrincipalManagement.getPrincipal());
				return "projectlist";
		}
		//diagrafei ena mathima
		@RequestMapping(value = { "delete-project-{idProject}" }, method = RequestMethod.GET)
		public String deleteProject(@PathVariable Integer idProject) {
			projectService.deleteProjectById(idProject);
			return "redirect:/admin/projects";
		} 
		
		
		@RequestMapping(value = { "edit-project-{idProject}" }, method = RequestMethod.GET)
		public String editProject(@PathVariable Integer idProject, ModelMap model) {
			Project project = projectService.findById(idProject);
			model.addAttribute("project", project);
			model.addAttribute("edit", true);
			model.addAttribute("loggedinuser", PrincipalManagement.getPrincipal());
			
			List<Courses> courses = coursesService.findAll(); //prosthesa auth l thn apokatv grammh 
			model.addAttribute("courses", courses);
			
			return "createproject";
		}

		/**
		 * This method will be called on form submission, handling POST request for
		 * updating user in database. It also validates the user input
		 */
		@RequestMapping(value = { "edit-project-{idProject}" }, method = RequestMethod.POST)
		public String updateProject(@Valid  Project project, BindingResult result, ModelMap model,@PathVariable Integer idProject) {

			if (result.hasErrors()) {
				return "createproject";
			}
			
			projectService.updateProject(project);

			model.addAttribute("success",
					"Project " + project.getTitle() + " " + project.getDescription() + " updated successfully");
			model.addAttribute("loggedinuser", PrincipalManagement.getPrincipal());
			return "registrationsuccess";
		}
		
		@RequestMapping(value = { "createproject" }, method = RequestMethod.GET)
		public String newProject(ModelMap model) {
			Project project = new Project();
			model.addAttribute("project", project);
			model.addAttribute("edit", false);
			model.addAttribute("loggedinuser", PrincipalManagement.getPrincipal());
			
			List<Courses> courses = coursesService.findAll(); //prosthesa auth l thn apokatv grammh 
			model.addAttribute("courses", courses);
			
			return "createproject";
		}
		
		@RequestMapping(value = { "createproject" }, method = RequestMethod.POST)
		public String saveProject(@Valid Project project, BindingResult result, ModelMap model) {

			if (result.hasErrors()) {
				return "createproject";
			}

		

			projectService.saveProject(project);

			model.addAttribute("success", "Project " + project.getTitle() + " " + " registered successfully");
			model.addAttribute("loggedinuser", PrincipalManagement.getPrincipal());
			// return "success";
			return "registrationsuccess";
		}
		
		@RequestMapping(value = { "/downloads-{idProject}" }, method = RequestMethod.GET)
		public String ViewDocuments(ModelMap model, @PathVariable Integer idProject) {
			//String username = PrincipalManagement.getPrincipal();

			//User user = userService.findBySSO(username);
			//model.addAttribute("user", user);

			//Integer idUser = user.getIdUser();

			FileBucket fileModel = new FileBucket();
			model.addAttribute("fileBucket", fileModel);

			Project project = projectService.findById(idProject);
			model.addAttribute("project", project);

			List<Submit> documents = submitService.findAllSubmitsByIdProject(idProject);
			model.addAttribute("documents", documents);

			return "downloads";
		}
		@RequestMapping(value = { "/download-document-{idProject}-{idSubmission}" }, method = RequestMethod.GET)
		public String downloadDocument(@PathVariable int idProject, @PathVariable int idSubmission, ModelMap model, HttpServletResponse response) throws IOException {
			


			Submit documents = submitService.findById(idSubmission);
			String filename = documents.getName();
			User a=documents.getUser();

			// model.addAttribute("documents", documents);
			String downloadFolder = AppInitializer.LOCATION + File.separator + idProject + File.separator + a.getUsername() + File.separator;
			File file = new File(downloadFolder + filename);

			response.setContentType(documents.getType());

			response.setContentLength((int) file.length());
			response.setHeader("Content-Disposition", "attachment; filename=" + filename);

			InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
			FileCopyUtils.copy(inputStream, response.getOutputStream());
            Project id=documents.getProject();
			Project project = projectService.findById(idProject);
			model.addAttribute("project", project);

			

			return null;
		}
		
}
