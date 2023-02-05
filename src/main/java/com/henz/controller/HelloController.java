package com.henz.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	/*@Value("${some.config}") //this is defined in application.properties
	private String someConfig;*/
	
	//@RequestMapping(value = "/", method = RequestMethod.GET)
	@GetMapping(value = "/")
	public String helloWorld() {
		//System.out.println(someConfig);
		return "Hello World update!";
	}

}
