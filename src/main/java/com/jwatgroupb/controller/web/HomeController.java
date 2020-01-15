/*
	@author:Quang Truong
	@date: Jan 14, 2020
*/

package com.jwatgroupb.controller.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.jwatgroupb.entity.UserEntity;
import com.jwatgroupb.service.UserService;


@Controller(value = "homeControllerOfWeb")
public class HomeController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = {"/","/trang-chu"}, method = RequestMethod.GET)
	public ModelAndView homePage() {
		ModelAndView mav = new ModelAndView("web/home");
		return mav;
	}
	
	
	@RequestMapping(value = "/show-user", method = RequestMethod.GET)
	public ModelAndView showListUser() {
		ModelAndView mav = new ModelAndView("web/userlist");
		List<UserEntity> listUser= userService.findAll();
		mav.addObject("listUser", listUser);
		return mav;
	}
	
	@RequestMapping(value= "/login", method = RequestMethod.GET)
	public ModelAndView loginPage() {
		ModelAndView mav = new ModelAndView("web/login");
		return mav;
	}
}

