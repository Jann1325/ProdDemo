package com.aic.proddemo.web.controller;

import com.aic.proddemo.service.ProdDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/prod/Prod")
public class ProdDetailController {

	@Autowired
	private ProdDetailService prodDetailService;

	@GetMapping("/details")
	public String getProdDetails(@RequestParam Integer id) {
		return prodDetailService.getProdDetails(id);
	}

	@PostMapping("/comment/report") // 使用POST方法來提交JSON資料
	public String reportProdComment(@RequestBody Map<String, Object> requestBody) {
		return prodDetailService.reportProdComment(requestBody);
	}
}
