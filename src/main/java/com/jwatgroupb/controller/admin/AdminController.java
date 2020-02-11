/*
	@author:Quang Truong
	@date: Jan 20, 2020
*/

package com.jwatgroupb.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jwatgroupb.entity.RoleUserEntity;
import com.jwatgroupb.entity.UserEntity;
import com.jwatgroupb.repository.RoleUserRepository;
import com.jwatgroupb.service.AdminService;

@Controller
public class AdminController {

	@Autowired
	public AdminService adminService;
	@Autowired
	public RoleUserRepository roleUserRepository;
	
	@RequestMapping(value = "/adminlogin", method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView mav = new ModelAndView("admin/login");
		return mav;
	}

	@RequestMapping(value = "/admin/Home", method = RequestMethod.GET)
	public ModelAndView homeAdmin() {
		return new ModelAndView("admin/home");
	}

	@RequestMapping(value = "/adminAccessDenied", method = RequestMethod.GET)
	public ModelAndView accessDenied() {
		return new ModelAndView("redirect:/adminlogin?accessDenied");
	}

	@RequestMapping(value = "admin/LoginSuccessful", method = RequestMethod.GET)
	public ModelAndView loginSuccessful() {
		return new ModelAndView("redirect:/admin/listUser");
	}

	@RequestMapping(value = "admin/private", method = RequestMethod.GET)
	public ModelAndView privatePage() {
		return new ModelAndView("admin/Private");
	}

	//CRUD

	@RequestMapping(value = "admin/listUser", method = RequestMethod.GET)
	public ModelAndView findAll() {
		List<UserEntity> listUser = adminService.findAll();
		ModelAndView mav = new ModelAndView("admin/listUser");
		mav.addObject("listUser", listUser);
		return mav;

	}

	@RequestMapping("admin/search")
	public ModelAndView search(@RequestParam String keyword) {
		System.out.println(keyword);
		List<UserEntity> result = adminService.search(keyword);
		ModelAndView mav = new ModelAndView("admin/search");
		mav.addObject("result", result);

		return mav;
	}

	@RequestMapping(value = "admin/adduser", method = RequestMethod.GET)
	public ModelAndView displayNewUserForm(UserEntity user) {
		ModelAndView mav = new ModelAndView("admin/adduser");
		mav.addObject("user", user);
		return mav;
	}

	@RequestMapping(value = "admin/addsave", method = RequestMethod.POST)
	public ModelAndView saveNewUser(@RequestParam("roleUserEntity") Long roleid,
			@ModelAttribute("user") UserEntity user, BindingResult result) {
		ModelAndView mav = new ModelAndView();
		System.out.println("role id nhap vao= " + roleid);
		System.out.println(user);
		RoleUserEntity role = roleUserRepository.findOne(roleid);
		System.out.println("Controller Role= " + role);
		user.setRoleUserEntity(role);
		System.out.println(user);
		if (adminService.findUserByUserName(user.getUserName()) == null) {
			adminService.saveUser(user, roleid);
			mav.setViewName("redirect:/admin/listUser");
		} else {
			System.out.println("User da ton tai");
			mav.setViewName("redirect:/admin/adduser");
			String message = "Username exists";
			mav.addObject("message", message);
		}
		return mav;
	}


	@RequestMapping(value = "admin/delete/{id}", method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable("id") Long id) {
		System.out.println("Id user nay la" + id);
		adminService.delete(id);
		return new ModelAndView("redirect:/admin/listUser");
	}

	@RequestMapping(value = "admin/edit/{userName}")
	public ModelAndView update(@PathVariable("userName") String userName) {
		ModelAndView mav = new ModelAndView("admin/editform");
		UserEntity user = adminService.findUserByUserName(userName);

		mav.addObject("editForm", user);
		System.out.println("USER FORM LOADED");
		return mav;
	}

	@RequestMapping(value = "admin/edit/editsave", method = RequestMethod.POST)
	public ModelAndView editsave(@RequestParam("userName") String userName,
			@RequestParam("roleUserEntity.id") Long roleid, @ModelAttribute("editForm") UserEntity user) {
		System.out.println("controller: " + "id = " + user.getId() + " ,username: " + userName + ", password: "
				+ user.getPassword() + ", active status:" + user.getActive());
		System.out.println(roleid);
		RoleUserEntity role = roleUserRepository.findOne(roleid);
		user.setRoleUserEntity(role);
		System.out.println(role.getRoleName());
		adminService.updateUser(user);
		return new ModelAndView("redirect:/admin/listUser");
	}
}
