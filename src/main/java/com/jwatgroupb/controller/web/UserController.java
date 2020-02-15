/*
	@author:Quang Truong
	@date: Feb 11, 2020
*/

package com.jwatgroupb.controller.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jwatgroupb.constant.SystemConstant;
import com.jwatgroupb.entity.BillEntity;
import com.jwatgroupb.entity.ProfileUserEntity;
import com.jwatgroupb.entity.UserEntity;
import com.jwatgroupb.repository.UserRepository;
import com.jwatgroupb.service.UserService;
import com.jwatgroupb.util.SecurityUtils;
import com.jwatgroupb.validator.ProfileUserValidator;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private ProfileUserValidator profileUserValidator;

	@Autowired
	private UserRepository userRepository;

	@ModelAttribute("infoForm")
	public ProfileUserEntity getProfileUser() {
		return new ProfileUserEntity();
	}

	// Format Date
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true, 10));
	}

	// Profile/ChangePassword
	@RequestMapping(value = "/user/changepassword", method = RequestMethod.POST)
	public String changepassword(@RequestParam("oldPasswordConfirm") String oldPasswordConfirm,
			@RequestParam("password") String password, Model model) {
		UserEntity userEntity = userRepository.findOneByUserNameAndActive(SecurityUtils.getPrincipal().getUsername(),
				SystemConstant.ACTIVE_STATUS);
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String oldPassword = SecurityUtils.getPrincipal().getPassword();

		if (passwordEncoder.matches(oldPasswordConfirm, oldPassword)) {
			userEntity.setPassword(password);
			userService.saveUser(userEntity);
			model.addAttribute("requestSuccess", "Request Success !");
		} else {
			model.addAttribute("oldPasswordConfirmError", "You inserted wrong password !");
			model.addAttribute("requestError", "You inserted wrong password !");
		}
		return "web/profile";
	}

	// Profile/AddInfo
	@RequestMapping(value = "/user/addInfo", method = RequestMethod.POST)
	public String addInfo(@ModelAttribute("infoForm") ProfileUserEntity infoForm, BindingResult bindingResult,
			Model model) {
		profileUserValidator.validate(infoForm, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("requestError", "Error occurred while updating information !");
			return "web/profile";
		}
		UserEntity userEntity = userRepository.findOneByUserNameAndActive(SecurityUtils.getPrincipal().getUsername(),
				SystemConstant.ACTIVE_STATUS);
		ProfileUserEntity profileUser = userService.findByUserEntity(userEntity);
		profileUser.setAddress(infoForm.getAddress());
		profileUser.setBirthday(infoForm.getBirthday());
		profileUser.setName(infoForm.getName());
		profileUser.setPhonenumber(infoForm.getPhonenumber());
		userService.saveProfileUser(profileUser);
		model.addAttribute("requestSuccess", "Request Success !");
		return "web/profile";
	}

	// Profile/
	@RequestMapping(value = "/user/profile", method = RequestMethod.GET)
	public ModelAndView userProfile() {
		ModelAndView mav = new ModelAndView("web/profile");
		return mav;
	}

	// User/PurchaseHistory
	@RequestMapping(value = "/user/purchaseHistory", method = RequestMethod.GET)
	public ModelAndView purchaseHistory() {
		ModelAndView mav = new ModelAndView("web/purchaseHistory");
		UserEntity userEntity = userRepository.findOneByUserNameAndActive(SecurityUtils.getPrincipal().getUsername(),
				SystemConstant.ACTIVE_STATUS);
		List<BillEntity> listBill = userService.findAllBill(userEntity.getId());
		mav.addObject("listBill", listBill);
		return mav;
	}

}
