package com.aic.proddemo.web.controller;

import com.aic.proddemo.domain.OrderDetail;
import com.aic.proddemo.repository.OrderDetailRepository;
import com.aic.proddemo.service.OrderDetailService;
import com.aic.proddemo.service.ProdCommentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProdCommentController {
	@Autowired
	private ProdCommentService prodCommentService;

	@GetMapping("/getSortedComments")
	public ResponseEntity<List<OrderDetail>> getSortedComments(
			@RequestParam(name = "sort", required = false, defaultValue = "all") String sortParam,
			@RequestParam(name = "id") int prodId,
			HttpServletResponse res) throws IOException {

		List<OrderDetail> list = prodCommentService.getSortedComments(sortParam, prodId);
		return ResponseEntity.ok(list);
	}
}
