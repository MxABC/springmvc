package com.lbx.myapp;

import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/user")
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
//		model.addAttribute("serverTime", "fdsajlfdjsalfjdsa" );
		
		return "home";
	}
	
//	@RequestMapping(value = "/",method = RequestMethod.POST)
//	public void create(HttpServletRequest request,HttpServletResponse response) throws IOException
//	{
//		response.getWriter().write("3ks:" );
//	}
	
	@RequestMapping(value = "/mypost",method = RequestMethod.POST)
	public void userLogin(HttpServletRequest request,HttpServletResponse response,User userInfo) throws IOException
	{
		logger.info("Welcome post login, The userid: "+ userInfo.getId());
		response.getWriter().write("3ks:" + userInfo.getName() + "for login" );
		
	}
	
	@RequestMapping(value = "/register",method = RequestMethod.POST)
	public void userRegister(HttpServletRequest request,HttpServletResponse response,User userInfo) throws IOException
	{
		logger.info("Welcome post register, The userid: "+ userInfo.getId());
		response.getWriter().write("3ks:" + userInfo.getName() + "for register" );
		
	}
	
}

class User implements Serializable {  
    
    private static final long serialVersionUID = 1L;  
      
    private String id;  
    private String name;  
    private int age;  
      
    public User() {  
        super();  
    }  
  
    public User(String id, String name, int age) {  
        super();  
        this.id = id;  
        this.name = name;  
        this.age = age;  
    }  
  
    public int getAge() {  
        return age;  
    }  
  
    public void setAge(int age) {  
        this.age = age;  
    }  
  
    public String getId() {  
        return id;  
    }  
  
    public void setId(String id) {  
        this.id = id;  
    }  
  
    public String getName() {  
        return name;  
    }  
  
    public void setName(String name) {  
        this.name = name;  
    }  
      
}  
