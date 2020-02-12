/*
	@author:Quang Truong
	@date: Jan 31, 2020
*/

package com.jwatgroupb.controller.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.jwatgroupb.constant.SystemConstant;
import com.jwatgroupb.entity.BillEntity;
import com.jwatgroupb.entity.CartEntity;
import com.jwatgroupb.entity.UserEntity;
import com.jwatgroupb.service.CartService;
import com.jwatgroupb.service.CheckoutService;
import com.jwatgroupb.service.UserService;
import com.jwatgroupb.util.SecurityUtils;

@Controller
@SessionAttributes({ "user", "cart", "bill" })
public class CheckOutController {

	@Autowired
	private UserService userService;

	@Autowired
	private CartService cartService;

	@Autowired
	private CheckoutService checkoutService;

	@RequestMapping(value = "/checkout", method = RequestMethod.GET)
	public ModelAndView checkoutPage(@CookieValue(value = "cart_code", required = false) String cartCode) {
		ModelAndView mav = new ModelAndView("web/checkout");
		CartEntity cart = new CartEntity();
		if (SecurityUtils.isAuthenticanted() == true) {// Da dang nhap
			String username = SecurityUtils.getPrincipal().getUsername();
			cart = cartService.findOneByUserName(username);
		} else {
			cart = cartService.findOneByCartCode(cartCode);
		}
		BillEntity bill = new BillEntity();
		mav.addObject("bill", bill);
		mav.addObject("cart", cart);
		return mav;
	}

	@RequestMapping(value = "/checkout", method = RequestMethod.POST)
	public ModelAndView pay(@ModelAttribute("bill") @Valid BillEntity bill, BindingResult result,
			@ModelAttribute("cart") CartEntity cart) {
		ModelAndView mav = new ModelAndView();
		UserEntity userEntity;
		if (result.hasErrors()) {
			mav.setViewName("web/checkout");
			return mav;
		} else {
			if (cart.getListCartItem().isEmpty()) {
				mav.setViewName("redirect:/checkout?cartisempty");
				return mav;
			} else {
				if (SecurityUtils.isAuthenticanted() == true) {
					String username = SecurityUtils.getPrincipal().getUsername();
					userEntity = userService.findByUsername(username);
					bill.setUserEntity(userEntity);
				}
				bill.setStatus(SystemConstant.ACTIVE_STATUS);
				checkoutService.addBill(bill);
				checkoutService.addBillDetail(cart, bill);
				cartService.clearItemInCart(cart);
				cart = new CartEntity();
				mav.setViewName("redirect:/checkout?PaySuccesful");
				return mav;
			}
		}
	}

	@RequestMapping(value = "/checkout/getquotes")
	public ModelAndView getQuotes() {
		ModelAndView mav = new ModelAndView();
		if (SecurityUtils.isAuthenticanted() == true) {
			String username = SecurityUtils.getPrincipal().getUsername();
			UserEntity user = userService.findByUsername(username);
			mav.addObject("user", user);
			mav.setViewName("redirect:/checkout");
		} else {
			mav.setViewName("redirect:/checkout?notLogin");
		}
		return mav;
	}
}
