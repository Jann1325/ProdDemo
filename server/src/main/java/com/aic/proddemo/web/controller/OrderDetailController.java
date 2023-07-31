package com.aic.proddemo.web.controller;

import com.aic.proddemo.service.OrderDetailService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/prod/prod")
public class OrderDetailController {

	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private OrderDetailService orderDetailService;

	@PostMapping("/order")
	public String createOrder(@RequestBody JsonNode requestBody) {
		return orderDetailService.createOrder(requestBody);
	}
}
