package com.websystique.springmvc.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.websystique.springmvc.model.User;
import com.websystique.springmvc.model.UserProfile;
import com.websystique.springmvc.service.UserProfileService;
import com.websystique.springmvc.service.UserService;
import com.websystique.springmvc.model.Courses;
import com.websystique.springmvc.service.CoursesService;
import com.websystique.springmvc.model.Project;
import com.websystique.springmvc.service.ProjectService;

@Controller
@RequestMapping("/")
@SessionAttributes({ "roles"})
public class AppController {

	@Autowired
	UserService userService;
	@Autowired
	CoursesService coursesService;

	@Autowired
	ProjectService projectService;

	@Autowired
	UserProfileService userProfileService;

	@Autowired
	MessageSource messageSource;

	@Autowired
	PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;

	@Autowired
	AuthenticationTrustResolver authenticationTrustResolver;

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String root(ModelMap model) {

		if (hasRole("admin")) {
			return "redirect:/admin/home";
		} else {
			if (userService.checkVerify(PrincipalManagement.getPrincipal())) {

				return "redirect:/user/home";

			} else {
				model.addAttribute("loggedinuser", PrincipalManagement.getPrincipal());
				return "accessNotPermit";
			}

		}
	}

	/**
	 * This method will provide UserProfile list to views
	 */
	@ModelAttribute("roles")
	public List<UserProfile> initializeProfiles() {
		return userProfileService.findAll();
	}
	/**
	 * This method will provide UserProfile list to views
	 */
	

	/**
	 * This method handles Access-Denied redirect.
	 */
	@RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
	public String accessDeniedPage(ModelMap model) {
		model.addAttribute("loggedinuser", PrincipalManagement.getPrincipal());
		return "accessDenied";
	}

	/**
	 * This method handles login GET requests. If users is already logged-in and
	 * tries to goto login page again, will be redirected to list page.
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
		if (isCurrentAuthenticationAnonymous()) {
			return "login";
		} else {
			if (!userService.checkVerify(PrincipalManagement.getPrincipal())) {
				return "login";
			} else {
				return "redirect:/";
			}
		}
	}

	/**
	 * This method handles signup GET requests. If users is already logged-in and
	 * tries to goto login page again, will be redirected to list page.
	 */
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signupUser(ModelMap model) {
		if (isCurrentAuthenticationAnonymous()) {
			User user = new User();

			Set<UserProfile> userProfiles = new HashSet<>();
			UserProfile userProfile = userProfileService.findById(2);

			userProfiles.add(userProfile);
			user.setUserProfiles(userProfiles);

			model.addAttribute("user", user);

			return "signup";
		} else {
			return "redirect:/admin/users";
		}
	}

	@RequestMapping(value = { "/signup" }, method = RequestMethod.POST)
	public String signupUser(@Valid User user, BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
			return "signup";
		}

		if (!userService.isUsernameUnique(user.getIdUser(), user.getUsername())) {
			FieldError ssoError = new FieldError("user", "username", messageSource.getMessage("non.unique.username", new String[] { user.getUsername() }, Locale.getDefault()));
			result.addError(ssoError);
			return "signup";
		}

		userService.registerUser(user);
		model.addAttribute("loggedinuser", user.getUsername());

		model.addAttribute("success", "User " + user.getFirstName() + " " + user.getLastName() + " registered successfully");

		return "signupsuccess";
	}

	/**
	 * This method handles logout requests. Toggle the handlers if you are
	 * RememberMe functionality is useless in your app.
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			// new SecurityContextLogoutHandler().logout(request, response, auth);
			persistentTokenBasedRememberMeServices.logout(request, response, auth);
			SecurityContextHolder.getContext().setAuthentication(null);
		}
		return "redirect:/login?logout";
	}

	/**
	 * This method returns true if users is already authenticated [logged-in], else
	 * false.
	 */
	private boolean isCurrentAuthenticationAnonymous() {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authenticationTrustResolver.isAnonymous(authentication);
	}

	protected boolean hasRole(String role) {
		// get security context from thread local
		SecurityContext context = SecurityContextHolder.getContext();
		if (context == null)
			return false;

		Authentication authentication = context.getAuthentication();
		if (authentication == null)
			return false;
		
		
//		if (role.equals("user")) {
//			String username = PrincipalManagement.getPrincipal();
		
//			boolean isactive = userService.checkVerify(username);
//			if (!isactive) {
//				return false;
//			}
//		}

		for (GrantedAuthority auth : authentication.getAuthorities()) {
			if (("ROLE_" + role).equals(auth.getAuthority()))
				return true;
		}

		return false;
	}

}