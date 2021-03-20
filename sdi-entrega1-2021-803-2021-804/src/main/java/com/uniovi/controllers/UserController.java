package com.uniovi.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.ServletWebRequest;

import com.uniovi.entities.User;
import com.uniovi.services.RolesService;
import com.uniovi.services.SecurityService;
import com.uniovi.services.UserService;
import com.uniovi.validators.RegistroFormValidator;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private SecurityService securityService;
	
	@Autowired
	private RolesService rolesService;
	
	@Autowired
	private RegistroFormValidator registroFormValidator;
	
	@Autowired
	private HttpSession httpSession;

	@RequestMapping("/user/list")
	public String getListado(Model model) {
		model.addAttribute("usersList", userService.getUsers());
		return "user/list";
	}

	@RequestMapping(value = "/user/add")
	public String getUser(Model model) {
		model.addAttribute("usersList", userService.getUsers());
		return "user/add";
	}

	@RequestMapping(value = "/user/add", method = RequestMethod.POST)
	public String setUser(@ModelAttribute User user) {
		userService.addUser(user);
		return "redirect:/user/list";
	}

	@RequestMapping("/user/delete/{id}")
	public String delete(@PathVariable Long id) {
		userService.deleteUser(id);
		return "redirect:/user/list";
	}
	
	@RequestMapping(value = "/user/delete", method = RequestMethod.POST)
	public String delete(ServletWebRequest request) {
		if(request.getParameterValues("idChecked") != null) {
			for(String idCheck : request.getParameterValues("idChecked")) {
				userService.deleteUser(Long.valueOf(idCheck));
			}
		}
		return "redirect:/user/list";
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(Model model) {
		model.addAttribute("user", new User());
		return "signup";
	}

	// Registro, login y autologin
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(@Validated User user, BindingResult result) {
		registroFormValidator.validate(user, result);
		if(result.hasErrors()) {
			return "signup";
		}
		user.setRol(rolesService.getRoles()[0]);
		user.setSaldo(100);
		userService.addUser(user);
		securityService.autoLogin(user.getEmail(), user.getPasswordConfirm());
		return "redirect:home";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		return "login";
	}
	
	@RequestMapping(value = { "/home" }, method = RequestMethod.GET)
	public String home(Model model) {
		User u= userService.getUserAuthenticated();
		httpSession.setAttribute("usuario", u);
		return "home";
	}
	
	@PostMapping("/user/list")
	public String delete(@RequestParam("checkboxeliminar") List<String> idusers){
	    if(idusers != null){
	        for(String idd : idusers){
	            Long id = Long.parseLong(idd);
	            userService.deleteUser(id);
	         } 
	    }
	    return "redirect:/user/list";
	}

}
