package com.in28minutes.springboot.myfirstwebapp.welcome;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;


@Controller
@SessionAttributes("name") //put into session scope
public class WelcomeController {
	
	/**
	 * private Logger logger=LoggerFactory.getLogger(getClass()); //use logger to debug
	
	//url--http://localhost:8080/login?firstname=yuan	
	//if you want to make something available to the view, 
	//put it into the model
	
	@RequestMapping("login")
	public String login(@RequestParam String firstname, ModelMap model) {
		
		model.put("name",firstname ); //"name" is for expression language in jsp
			
		logger.debug("Request param is {}"+firstname); //debug level
		logger.info(firstname); //info level
		
		System.out.println("request param is "+firstname); //not recommended, use logger
		
		return "login";
		
		}
		*/
		

	@RequestMapping(value="/",method=RequestMethod.GET)
	public String welcoomePage(ModelMap model) {	
		model.put("name", "yuan");
		return "welcome";
		
	}
	
	private String getLoggedinUsername() {
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
	
		return authentication.getName();
	}
	
	/**
	 * 
	 * 
	 *
	@RequestMapping(value="login",method=RequestMethod.POST)
	public String welcomePage(@RequestParam String name,
			@RequestParam String password,ModelMap model) {	
		
	if(as.authenticate(name,password)) {
	model.put("name", name);
	model.put("password", password);
		
		return "welcome";
	}
	
	model.put("error","please try again" );
	return "login";
		
	}
	*/

}
