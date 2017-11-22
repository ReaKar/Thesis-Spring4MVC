package com.websystique.springmvc.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.websystique.springmvc.configuration.AppInitializer;
import com.websystique.springmvc.model.Courses;
import com.websystique.springmvc.model.FileBucket;
import com.websystique.springmvc.model.Project;
import com.websystique.springmvc.model.User;
import com.websystique.springmvc.model.Submit;

import com.websystique.springmvc.service.CoursesService;
import com.websystique.springmvc.service.ProjectService;
import com.websystique.springmvc.service.UserProfileService;
import com.websystique.springmvc.service.UserService;
import com.websystique.springmvc.util.FileValidator;
import com.websystique.springmvc.service.SubmitService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userService;
	@Autowired
	CoursesService coursesService;

	@Autowired
	ProjectService projectService;

	@Autowired
	UserProfileService userProfileService;

	@Autowired
	SubmitService submitService;

	@Autowired
	MessageSource messageSource;

	@Autowired
	PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;

	@Autowired
	FileValidator fileValidator;

	@Autowired
	ServletContext context;

	@InitBinder("fileBucket")
	protected void initBinder(WebDataBinder binder) { // kaleitai automata kata thn ypovolh
		binder.setValidator(fileValidator);
	}

	// -----------------------apo dw------------------------------

	@RequestMapping(value = { "/manage-documents-{idProject}" }, method = RequestMethod.GET)
	public String addDocuments(ModelMap model, @PathVariable int idProject) {
		String username = PrincipalManagement.getPrincipal();

		User user = userService.findBySSO(username);
		model.addAttribute("user", user);

		Integer idUser = user.getIdUser();

		FileBucket fileModel = new FileBucket();
		model.addAttribute("fileBucket", fileModel);

		Project project = projectService.findById(idProject);
		model.addAttribute("project", project);

		List<Submit> documents = submitService.findAllByUserIAndProjectID(idUser, idProject);
		model.addAttribute("documents", documents);

		return "managedocuments";
	}

	@RequestMapping(value = { "/all-projects" }, method = RequestMethod.GET)
	public String AllProjects(ModelMap model) {
		String username = PrincipalManagement.getPrincipal();

		User user = userService.findBySSO(username);
		model.addAttribute("user", user);

		Integer idUser = user.getIdUser();

		// Project project = projectService.findById(idProject);
		// model.addAttribute("project", project);

		List<Submit> documents = submitService.findAllByUserId(idUser);
		model.addAttribute("documents", documents);

		return "allsubmits";
	}

	@RequestMapping(value = { "/view-documents-{idProject}" }, method = RequestMethod.GET)
	public String ViewDocuments(ModelMap model, @PathVariable int idProject) {
		String username = PrincipalManagement.getPrincipal();

		User user = userService.findBySSO(username);
		model.addAttribute("user", user);

		Integer idUser = user.getIdUser();

		FileBucket fileModel = new FileBucket();
		model.addAttribute("fileBucket", fileModel);

		Project project = projectService.findById(idProject);
		model.addAttribute("project", project);

		List<Submit> documents = submitService.findAllByUserIAndProjectID(idUser, idProject);
		model.addAttribute("documents", documents);

		return "viewdocuments";
	}

	@RequestMapping(value = { "/download-document-{idProject}-{idSubmission}" }, method = RequestMethod.GET)
	public String downloadDocument(@PathVariable int idProject, @PathVariable int idSubmission, ModelMap model, HttpServletResponse response) throws IOException {
		String username = PrincipalManagement.getPrincipal();

		User user = userService.findBySSO(username);
		model.addAttribute("user", user);

		Submit documents = submitService.findById(idSubmission);
		String filename = documents.getName();

		// model.addAttribute("documents", documents);
		String downloadFolder = AppInitializer.LOCATION + File.separator + idProject + File.separator + username + File.separator;
		File file = new File(downloadFolder + filename);

		response.setContentType(documents.getType());

		response.setContentLength((int) file.length());
		response.setHeader("Content-Disposition", "attachment; filename=" + filename);

		InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
		FileCopyUtils.copy(inputStream, response.getOutputStream());

		Project project = projectService.findById(idProject);
		model.addAttribute("project", project);

		// apo edww UserDocument document = userDocumentService.findById(docId);
		// response.setContentType(document.getType());
		// response.setContentLength(document.getContent().length);
		// response.setHeader("Content-Disposition","attachment; filename=\"" +
		// document.getName() +"\"");
		//
		// FileCopyUtils.copy(document.getContent(), response.getOutputStream());
		//
		// mexri edw ayto p exei autos return "redirect:/add-document-"+userId;

		return null;
	}

	@RequestMapping(value = { "/manage-documents-{idProject}" }, method = RequestMethod.POST)
	public String uploadDocument(@Valid FileBucket fileBucket, BindingResult result, ModelMap model, @PathVariable int idProject) throws IOException {
		String username = PrincipalManagement.getPrincipal();

		User user = userService.findBySSO(username);
		model.addAttribute("user", user);

		Integer idUser = user.getIdUser();

		// mpainei edw prin kalesw save

		MultipartFile multipartFile = fileBucket.getFile();
		String filepath = AppInitializer.LOCATION + File.separator + idProject + File.separator + user.getUsername() + File.separator + multipartFile.getOriginalFilename();

		if (result.hasErrors() == false) {
			File file = new File(filepath);
			boolean fileExists = file.exists();

			if (fileExists) {
				result.rejectValue("file", "Upload.error");
			}
		}

		if (result.hasErrors()) {
			Project project = projectService.findById(idProject);
			model.addAttribute("project", project);

			List<Submit> documents = submitService.findAllByUserIAndProjectID(idUser, idProject);
			model.addAttribute("documents", documents);

			return "managedocuments";
		} else {
			System.out.println("Fetching file");

			saveDocument(fileBucket, user, idProject);

			return "redirect:/user/manage-documents-" + idProject;
		}
	}

	private void saveDocument(FileBucket fileBucket, User user, int idProject) throws IOException {
		Project project = projectService.findById(idProject);
		Submit document = new Submit();
		int a = 0;

		// store : multipartFile.getBytes() to hdd
		MultipartFile multipartFile = fileBucket.getFile();

		String filepath = AppInitializer.LOCATION + File.separator + idProject + File.separator + user.getUsername() + File.separator + multipartFile.getOriginalFilename();

		File file = new File(filepath);
		file.getParentFile().mkdirs();

		byte[] fileBytes = fileBucket.getFile().getBytes();

		try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filepath));) {
			bos.write(fileBytes); // metafora byte apo thn mnhmh sto disko
			bos.flush();
			bos.close();
		}
		;

		document.setName(multipartFile.getOriginalFilename());
		document.setFilepath(filepath);
		document.setProject(project);
		document.setType(multipartFile.getContentType());
		document.setUser(user);
		document.setWhen(new Date());

		submitService.saveSubmits(document);
	}

	// -------------------------mexri edw g upload
	// edw moy emfanizei ts titlous twn mathimatwn
	@RequestMapping(value = { "home" }, method = RequestMethod.GET)
	public String HomeUser(ModelMap model) {
		List<Courses> courses = coursesService.findAllCourses();
		List<Courses> cr = new ArrayList<Courses>();
		for (Courses str : courses) {
			if (str.isActive()) {
				cr.add(str);

			}

		}
		model.addAttribute("courses", cr); // edw t exw kanei auto gia na stelnei mono t active mathimata pernaw apo th mia
											// lista sthn allh.
		// model.addAttribute("title",);
		return "homeuser";

	}

	// moy dinei t mathima p epelekse o user
	@RequestMapping(value = { "course-{title}" }, method = RequestMethod.GET)
	public String Course(@PathVariable String title, ModelMap model) {
		Courses courses = coursesService.findByTitle(title);
		model.addAttribute("courses", courses);

		return "courseTitle";
	}

	@RequestMapping(value = { "instructions" }, method = RequestMethod.GET)
	public String UserInstructions(ModelMap model) {
		List<Courses> courses = coursesService.findAllCourses();

		List<Courses> cr = new ArrayList<Courses>();
		for (Courses str : courses) {
			if (str.isActive()) {
				cr.add(str);

			}

		}
		model.addAttribute("courses", cr); // edw t exw kanei auto gia na stelnei mono t active mathimata pernaw apo th mia
											// lista sthn allh.
		// model.addAttribute("title",);
		return "instructions";

	}

}
