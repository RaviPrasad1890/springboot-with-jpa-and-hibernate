package com.ravi.springboot.web.controller;

import javax.servlet.http.HttpServletRequest;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Controller("error")
public class ErrorController {
	private static Logger logger=LoggerFactory.getLogger(ErrorController.class);
	
	@ExceptionHandler(Exception.class)
	public ModelAndView handleException
		(HttpServletRequest request, Exception ex){
		ModelAndView mv = new ModelAndView();

		mv.addObject("exception", ex.getLocalizedMessage());
		mv.addObject("url", request.getRequestURL());
		logger.info("Exception "+ex.getLocalizedMessage());
		logger.info("URL "+request.getRequestURL());
		mv.setViewName("error");
		return mv;
	}

}
