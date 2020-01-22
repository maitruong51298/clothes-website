/*
	@author:Quang Truong
	@date: Jan 20, 2020
*/

package com.jwatgroupb.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/admin")
@SessionAttributes
public class AdminController {
	
	@GetMapping(value = "")
	public ModelAndView login() {
		ModelAndView mav = new ModelAndView("admin/login");
		return mav;
	}
}
