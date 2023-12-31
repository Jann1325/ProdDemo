package com.aic.proddemo.web.vm;

import java.io.Serializable;
import java.util.Arrays;

public class CartItemVM {

	private String productId;
	private String productName;
	private double price;
	private Integer qty;
	private byte[] prodPic;
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Integer getQty() {
		return qty;
	}
	public void setQty(Integer qty) {
		this.qty = qty;
	}
	public byte[] getProdPic() {
		return prodPic;
	}
	public void setProdPic(byte[] prodPic) {
		this.prodPic = prodPic;
	}
	@Override
	public String toString() {
		return "CartItem [productId=" + productId + ", productName=" + productName + ", price=" + price + ", qty=" + qty
				+ ", prodPic=" + Arrays.toString(prodPic) + "]";
	}
	public CartItemVM(String productId, String productName, double price, Integer qty, byte[] prodPic) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.qty = qty;
		this.prodPic = prodPic;
	}
	public CartItemVM() {
		super();
	}

}
