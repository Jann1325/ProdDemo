package com.aic.proddemo.web.controller;

import com.aic.proddemo.service.ProdService;
import com.aic.proddemo.web.vm.ProdVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/prod/Prod")
public class ProdController {
	@Autowired
	private ProdService prodService;

	@GetMapping("/getall")
	public Page<ProdVM> getAllProducts(Pageable pageable) {
	 return this.prodService.selectAll(pageable);
	}
	@GetMapping("/getByCompositeQuery")
	public Page<ProdVM> getProductsByCompositeQuery(@RequestParam("search") String search, Pageable pageable) {
		return this.prodService.getByCompositeQuery(search, pageable);
	}
	@GetMapping("/getByCategory")
	public Page<ProdVM> getProductsByCategory(@RequestParam("category") String category, Pageable pageable) {
		return this.prodService.getByCategory(category, pageable);
	}
	@GetMapping("/sortBy")
	public Page<ProdVM> getsortProducts( @RequestParam("sortParam") String sortParam, Pageable pageable) {
		return this.prodService.getSortedProducts(sortParam, pageable);
	}
}

