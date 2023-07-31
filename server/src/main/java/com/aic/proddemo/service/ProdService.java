package com.aic.proddemo.service;

import com.aic.proddemo.domain.Prod;
import com.aic.proddemo.repository.ProdRepository;
import com.aic.proddemo.web.vm.ProdVM;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class ProdService{
	@Autowired
	private ProdRepository prodRepository;

	@Autowired
	private ProdVMConverter ProdVMConverter;


	// 所有產品
	public Page<ProdVM> selectAll(Pageable pageable) {
		Page<Object[]> resultList = prodRepository.selectAll(pageable);
		List<ProdVM> prodVMList = new ArrayList<>();

		for (Object[] result : resultList) {
			ProdVM prodVM = ProdVMConverter.convert(result);
			prodVMList.add(prodVM);
		}

		return new PageImpl<>(prodVMList, pageable, resultList.getTotalElements());
	}
	// 文字模糊比對搜尋
	public Page<ProdVM> getByCompositeQuery(String search, Pageable pageable) {
		Page<Object[]> resultList = prodRepository.getByCompositeQuery(search, pageable);
		List<ProdVM> prodVMList = new ArrayList<>();

		for (Object[] result : resultList) {
			ProdVM prodVM = ProdVMConverter.convert(result);
			prodVMList.add(prodVM);
		}

		return new PageImpl<>(prodVMList, pageable, resultList.getTotalElements());
	}
	// 依餐廳分類搜尋
	public Page<ProdVM> getByCategory(String category, Pageable pageable) {
		Page<Object[]> resultList = prodRepository.getByCategory(category, pageable);

		List<ProdVM> prodVMList = new ArrayList<>();

		for (Object[] result : resultList) {
			ProdVM prodVM = ProdVMConverter.convert(result);
			prodVMList.add(prodVM);
		}

		return new PageImpl<>(prodVMList, pageable, resultList.getTotalElements());
	}

	// 產品排序
	public Page<ProdVM>  getSortedProducts(String sortParam, Pageable pageable) {
		Page<Object[]> resultList = prodRepository.selectAll(pageable);
		List<ProdVM> prodVMList = new ArrayList<>();

		for (Object[] result : resultList) {
			ProdVM prodVM = ProdVMConverter.convert(result);
			prodVMList.add(prodVM);
		}
		if ("star".equals(sortParam)) {
			Collections.sort(prodVMList, Comparator.comparing(ProdVM::getProdCommentScore, Comparator.nullsLast(Comparator.reverseOrder())));
		} else if ("pricefromhigh".equals(sortParam)) {
			Collections.sort(prodVMList, Comparator.comparing(ProdVM::getProdPrice, Comparator.reverseOrder()));
		} else if ("pricefromlow".equals(sortParam)) {
			Collections.sort(prodVMList, Comparator.comparing(ProdVM::getProdPrice));
		}

		List<ProdVM> sortedProducts = prodVMList.subList(
				(int) pageable.getOffset(),
				Math.min((int) pageable.getOffset() + pageable.getPageSize(), prodVMList.size())
		);

		return new PageImpl<>(sortedProducts, pageable, resultList.getTotalElements());
	}
}