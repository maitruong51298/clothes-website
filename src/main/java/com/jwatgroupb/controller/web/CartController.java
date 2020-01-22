/*
	@author:Quang Truong
	@date: Jan 21, 2020
*/

package com.jwatgroupb.controller.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.jwatgroupb.service.CartService;
import com.jwatgroupb.util.RandomStringUtil;
import com.jwatgroupb.util.SecurityUtils;

@Controller
@RequestMapping(value = "/cart")
public class CartController {

	@Autowired
	private CartService cartService;
	
	@GetMapping(value = "/add/{productId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void addProduct(@PathVariable("productId") Long productId,
			@CookieValue(value = "cart_code",required = false) String cartCode,
			HttpServletResponse response) {
		if (SecurityUtils.isAuthenticanted() == true) {// Da dang nhap
			String username=SecurityUtils.getPrincipal().getUsername();
			cartService.updateOrCreateCartWithUsername(username, productId);
			System.out.println("Da dang nhap");
		} else if (cartCode!=null) {//Chua dang nhap, ton tai cookie cart
			cartService.updateCartItem(cartCode, productId);
			System.out.println("Chua dang nhap, co cookie");
		} else {//Chua dang nhap, ko ton tai cookie cart
			System.out.println("chua dang nhap, k co cookie");
			
			//Random cart_code
			cartCode=RandomStringUtil.Random();
			
			//Tao cookie cho cart
			Cookie cookie = new Cookie("cart_code",cartCode );
			cookie.setMaxAge(60*60*24*365);
			response.addCookie(cookie);
			
			//Tao moi cart
			cartService.saveCartWithCartCodeAndProductId(cartCode, productId);
			
		}
	}
}
