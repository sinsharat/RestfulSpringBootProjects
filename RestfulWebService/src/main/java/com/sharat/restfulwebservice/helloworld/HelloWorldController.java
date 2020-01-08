package com.sharat.restfulwebservice.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	@Autowired
	private MessageSource messageSource;
	
	@GetMapping("/")
	public String welcome() {
		return "Welcome to Spring Boot Application.";
	}
	
	@RequestMapping(path = {"/helloworld"}, method = RequestMethod.GET)
	public String helloWorld() {
		return "Hello World";
	}
	
	@GetMapping(path = {"/helloworld/{message}"})
	public HelloWorldBean helloWorld(@PathVariable String message) {
		HelloWorldBean helloWorldBean = new HelloWorldBean(message);
		return helloWorldBean;
	}
	
	@GetMapping(path = {"/helloworld/internationalize"})
	public String helloWorldInternationalization() {
		return messageSource.getMessage("hellowworld.message", null, LocaleContextHolder.getLocale());
	}
	
}
