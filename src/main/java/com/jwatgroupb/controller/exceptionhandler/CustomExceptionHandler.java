/*
	@author:Quang Truong
	@date: Feb 12, 2020
*/

package com.jwatgroupb.controller.exceptionhandler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class CustomExceptionHandler  {
	
	
	@ExceptionHandler(CustomException.class)
	public ModelAndView handleCustomException(CustomException ex) {
		ModelAndView mav = new ModelAndView("errorPage");
		mav.addObject("errCode", ex.getErrCode());
		mav.addObject("errMsg", ex.getErrMsg());
		return mav;

	}
	@ExceptionHandler(NoHandlerFoundException.class)
	public ModelAndView handleException() {
		ModelAndView mav = new ModelAndView("errorPage");
		mav.addObject("errCode", "404 Error Page");
		mav.addObject("errMsg", "The page you are looking for is not avaiable now");
		return mav;

	}

	@ExceptionHandler(Exception.class)
	public ModelAndView handleAllException(Exception ex) {

		ModelAndView mav = new ModelAndView("errorPage");
		mav.addObject("errMsg", ex.getMessage());
		return mav;

	}
	
	
}
