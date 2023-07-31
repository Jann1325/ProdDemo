package com.aic.proddemo.domain;

import javax.persistence.*;

@Entity
@Table(name = "prod_order_detail")
public class ProdOrderDetail{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "prod_order_detail_id", updatable = false)
	private Integer prodOrderDetailId;
	@Column(name = "order_detail_id")
	private Integer orderDetailId;
	@Column(name = "prod_order_state")
	private String prodOrderCode;
	@Column(name = "prod_order_state", insertable = false, updatable = false)
	private Integer prodOrderState;

	public ProdOrderDetail() {

	}

	public Integer getProdOrderDetailId() {
		return prodOrderDetailId;
	}

	public void setProdOrderDetailId(Integer prodOrderDetailId) {
		this.prodOrderDetailId = prodOrderDetailId;
	}

	public Integer getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(Integer orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	public String getProdOrderCode() {
		return prodOrderCode;
	}

	public void setProdOrderCode(String prodOrderCode) {
		this.prodOrderCode = prodOrderCode;
	}

	public Integer getProdOrderState() {
		return prodOrderState;
	}

	public void setProdOrderState(Integer prodOrderState) {
		this.prodOrderState = prodOrderState;
	}

	public ProdOrderDetail(Integer prodOrderDetailId, Integer orderDetailId, String prodOrderCode, Integer prodOrderState) {
		this.prodOrderDetailId = prodOrderDetailId;
		this.orderDetailId = orderDetailId;
		this.prodOrderCode = prodOrderCode;
		this.prodOrderState = prodOrderState;
	}
}