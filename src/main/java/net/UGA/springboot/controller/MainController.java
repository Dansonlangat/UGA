package net.UGA.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {
	
	@GetMapping ("/login")
	@ModelAttribute("user")
	public String login() {
		System.out.println("i am here !!!");
		//username and password
		//userservice -get from users able where username and password = user(supplied from the portal)
		//the above condition=true navigate to //	return "index";
		///else remain login page-
		return "login";
		//validation--after succesful login naviagte to index
	//	return "index";
	}
	/*
	 * @GetMapping("/") public String home() { return "index"; }
	 */
}