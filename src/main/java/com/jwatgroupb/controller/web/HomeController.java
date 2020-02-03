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
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jwatgroupb.service.CartService;
import com.jwatgroupb.util.SecurityUtils;

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

}
