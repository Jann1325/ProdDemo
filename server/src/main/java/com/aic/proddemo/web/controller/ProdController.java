package com.aic.proddemo.web.controller;

import com.aic.proddemo.domain.Prod;
import com.aic.proddemo.service.ProdService;
import com.aic.proddemo.web.vm.ProdRequestVM;
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

	// 獲取全部商品
	@GetMapping("/getall")
	public Page<ProdVM> getAllProducts(Pageable pageable) {
	 return this.prodService.selectAll(pageable);
	}

	// 搜尋商品
	@GetMapping("/getprod")
	public Page<ProdVM> searchProds(Pageable pageable, ProdRequestVM params) {
		return this.prodService.searchProds(pageable, params);
	}

	// 新增或修改商品資料
	@PostMapping("/saveprod")
	public Prod SaveProd(@RequestBody Prod prod){
		return this.prodService.save(prod);
	}

	// 刪除商品
	@PostMapping("/deleteprod")
	public void deleteProd(@RequestBody Prod prod){
		this.prodService.delete(prod);
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

