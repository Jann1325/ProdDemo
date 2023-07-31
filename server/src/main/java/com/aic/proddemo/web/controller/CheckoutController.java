package com.aic.proddemo.web.controller;

import com.aic.proddemo.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/prod/prod")
public class CheckoutController {

	private final CheckoutService checkoutService;

	@Autowired
	public CheckoutController(CheckoutService checkoutService) {
		this.checkoutService = checkoutService;
	}

	@PostMapping("/directBuy")
	public void checkout(@RequestBody Map<String, Object> requestBody, HttpServletRequest request) {
		checkoutService.processCheckout(requestBody, request);
	}

	@GetMapping("/checkout")
	public String getCheckoutData(@RequestParam("accId") String accId, HttpServletRequest request) {
		return checkoutService.getCheckoutData(accId, request);
	}
}
