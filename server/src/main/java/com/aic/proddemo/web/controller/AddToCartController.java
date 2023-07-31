package com.aic.proddemo.web.controller;

import com.aic.proddemo.service.AddToCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/prod")
public class AddToCartController {

	@Autowired
	private AddToCartService addToCartService;

	@PostMapping("/add-cart")
	public String addToCart(@RequestBody Map<String, String> requestParams) {
		return addToCartService.addToCart(requestParams);
	}

	@GetMapping("/get-cart")
	public String getCart(@RequestParam int accId) {
		return addToCartService.getCart(accId);
	}
}
