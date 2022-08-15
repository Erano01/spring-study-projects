package me.serterano.springsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

@Controller
public class DemoController {

	@GetMapping("/")
	public String showHome() {
		return "home";
	}
	
//	public void addViewControllers(ViewControllerRegistry registry) {
//	    registry.addViewController("/").setViewName("home");
//	 }
	
	
	
	@GetMapping("/leaders")
	public String showLeaders() {
		return "leaders";
	}
	
	@GetMapping("/systems")
	public String showSystems() {
		return "systems";
	}
	
	
	
	
}
