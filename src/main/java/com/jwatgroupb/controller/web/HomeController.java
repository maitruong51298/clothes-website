/*
	@author:Quang Truong
	@date: Jan 14, 2020
*/

package com.jwatgroupb.controller.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jwatgroupb.constant.SystemConstant;
import com.jwatgroupb.entity.BillEntity;
import com.jwatgroupb.entity.CategoryEntity;
import com.jwatgroupb.entity.ProductEntity;
import com.jwatgroupb.entity.ProfileUserEntity;
import com.jwatgroupb.entity.RoleUserEntity;
import com.jwatgroupb.entity.UserEntity;
import com.jwatgroupb.repository.RoleUserRepository;
import com.jwatgroupb.repository.UserRepository;
import com.jwatgroupb.service.CartService;
import com.jwatgroupb.service.CategoryService;
import com.jwatgroupb.service.ProductService;
import com.jwatgroupb.service.UserService;
import com.jwatgroupb.util.SecurityUtils;
import com.jwatgroupb.validator.ProfileUserValidator;
import com.jwatgroupb.validator.UserValidator;

@Controller(value = "homeControllerOfWeb")
public class HomeController {

	@Autowired
	private CartService cartService;

	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryService categoryService;

	@RequestMapping(value = { "/", "/HomePage" }, method = RequestMethod.GET)
	public ModelAndView homePage() {
		ModelAndView mav = new ModelAndView("web/home");
		List<ProductEntity> listProduct = productService.findAllProduct();
		List<CategoryEntity> listCategory = categoryService.listCategory();
		mav.addObject("listCategory", listCategory);
		mav.addObject("listProduct", listProduct);
		return mav;
	}

	@RequestMapping(value = "/ProductCategory/{categoryName}", method = RequestMethod.GET)
	public ModelAndView category(@PathVariable("categoryName") String categoryName) {
		ModelAndView mav = new ModelAndView("web/productByCategory");
		
		List<ProductEntity> listProductByCategory = categoryService.findAllProduct(categoryName);
		mav.addObject("listProductByCategory", listProductByCategory);
		
		List<CategoryEntity> listCategory = categoryService.listCategory();
		mav.addObject("listCategory", listCategory);
		
		
		System.out.println("mav: " + mav);
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

	@RequestMapping(value = "/LoginSuccessful")
	public ModelAndView loginSuccessful(@CookieValue(value = "cart_code", required = false) String cartCode) {
		String username = SecurityUtils.getPrincipal().getUsername();
		if (cartCode != null) {
			cartService.addCartAfterLogin(username, cartCode);
		}
		return new ModelAndView("redirect:/HomePage");
	}

	// Hai update
	
	
	
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
	
	//Register
	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String registration(Model model) {
		model.addAttribute("userForm", new UserEntity());
		return "login";
	}
	//Register
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

		ProfileUserEntity profileUser = new ProfileUserEntity();
		profileUser.setUserEntity(userForm);
		userService.saveProfileUser(profileUser);

		userService.saveUser(userForm);
		return "notice/registerSuccess";
	}
	//Profile/ChangePassword
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
	
	@ModelAttribute("infoForm")
	public ProfileUserEntity getProfileUser() {
		return new ProfileUserEntity();
	}
	//Format Date
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true, 10));
	}
	//Profile/AddInfo
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
	//Profile/
	@RequestMapping(value = "/user/profile", method = RequestMethod.GET)
	public ModelAndView userProfile() {
		ModelAndView mav = new ModelAndView("web/profile");
		return mav;
	}
	//User/PurchaseHistory
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
