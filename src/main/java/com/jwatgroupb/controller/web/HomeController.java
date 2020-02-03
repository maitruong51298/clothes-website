/*
	@author:Quang Truong
	@date: Jan 14, 2020
*/

package com.jwatgroupb.controller.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jwatgroupb.constant.SystemConstant;
import com.jwatgroupb.entity.ProfileUserEntity;
import com.jwatgroupb.entity.RoleUserEntity;
import com.jwatgroupb.entity.UserEntity;
import com.jwatgroupb.repository.RoleUserRepository;
import com.jwatgroupb.repository.UserRepository;
import com.jwatgroupb.service.CartService;
import com.jwatgroupb.service.UserService;
import com.jwatgroupb.util.SecurityUtils;
import com.jwatgroupb.validator.ProfileUserValidator;
import com.jwatgroupb.validator.UserValidator;

@Controller(value = "homeControllerOfWeb")
public class HomeController {

	@Autowired
	private CartService cartService;

	@RequestMapping(value = { "/", "/HomePage" }, method = RequestMethod.GET)
	public ModelAndView homePage() {
		ModelAndView mav = new ModelAndView("web/home");
		return mav;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView loginPage() {
		ModelAndView mav = new ModelAndView("web/login");
		return mav;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return new ModelAndView("redirect:/HomePage");
	}

	@RequestMapping(value = "/customerAccessDenied", method = RequestMethod.GET)
	public ModelAndView accessDenied() {
		return new ModelAndView("redirect:/login?accessDenied");
	}

	@RequestMapping(value = "/ex/{id}", method = RequestMethod.GET)
	@ResponseBody
	public String getFoosBySimplePathWithPathVariable(@PathVariable("id") String id) {
		return "Get a specific Foo with id=" + id;
	}

//	@RequestMapping(value = "/checkout")
//	public ModelAndView checkOut() {
//		List<CartItemEntity> listCartItem= new ArrayList<CartItemEntity>();
//		listCartItem=userService.findCartOfUser(getUsername());
//		ModelAndView mav = new ModelAndView("/web/checkout");
//		mav.addObject("listCartItem", listCartItem);
//		return mav;
//	}

	@RequestMapping(value = "/LoginSuccessful")
	public ModelAndView loginSuccessful(@CookieValue(value = "cart_code", required = false) String cartCode) {
		String username = SecurityUtils.getPrincipal().getUsername();
		if (cartCode != null) {
			cartService.addCartAfterLogin(username, cartCode);
		}
		return new ModelAndView("redirect:/HomePage");
	}

	
	
	//HAI
	@Autowired
	private UserService userService;

	@Autowired
	private UserValidator userValidator;
	
	@Autowired
	private ProfileUserValidator profileUserValidator;

	@Autowired
	private RoleUserRepository roleUserRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@ModelAttribute("userForm")
	public UserEntity getUser() {
		return new UserEntity();
	}

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String registration(Model model) {
		model.addAttribute("userForm", new UserEntity());
		return "login";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String registration(@ModelAttribute("userForm") UserEntity userForm, BindingResult bindingResult,
			Model model) {
		userValidator.validate(userForm, bindingResult);
		if (bindingResult.hasErrors()) {
			return "web/login";
		}
		userForm.setActive(SystemConstant.ACTIVE_STATUS);
		RoleUserEntity roleUser = roleUserRepository.findOneById(2);
		userForm.setRoleUserEntity(roleUser);
		userService.saveUser(userForm);
		return "notice/registerSuccess";
	}
	
	
	@ModelAttribute("infoForm")
	public ProfileUserEntity getProfileUser() {
		return new ProfileUserEntity();
	}
	
	@RequestMapping(value = "/user/addInfo", method = RequestMethod.POST)
	public String registration(@ModelAttribute("infoForm") ProfileUserEntity infoForm, BindingResult bindingResult,
			Model model) {
		profileUserValidator.validate(infoForm, bindingResult);
		if (bindingResult.hasErrors()) {
			return "web/profile";
		}
		System.out.println("validate profile success");
		System.out.println(infoForm.getBirthday());
		UserEntity userEntity = userRepository.findOneByUserNameAndActive(SecurityUtils.getPrincipal().getUsername(), SystemConstant.ACTIVE_STATUS);
		infoForm.setUserEntity(userEntity);
		userService.saveProfileUser(infoForm);
		System.out.println("save profile success");
		return "web/profile";
	}


	@RequestMapping(value = "/user/profile", method = RequestMethod.GET)
	public ModelAndView userProfile() {
		ModelAndView mav = new ModelAndView("web/profile");
		return mav;
	}
}
