package com.aic.proddemo.web.vm;


import java.util.List;

public class CartVM{

	private String userId;
	private List<CartItemVM> items;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<CartItemVM> getItems() {
		return items;
	}

	public void setItems(List<CartItemVM> items) {
		this.items = items;
	}

	public CartVM(String userId, List<CartItemVM> items) {
		super();
		this.userId = userId;
		this.items = items;
	}

	public CartVM() {
		super();
	}

}
