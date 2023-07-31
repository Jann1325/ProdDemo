package com.aic.proddemo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "order_detail")
public class OrderDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_detail_id", updatable = false)
	private Integer orderDetailId;
	@Column(name = "order_id")
	private Integer orderId;
	@Column(name = "prod_id")
	private Integer prodId;
	@Column(name = "prod_price")
	private Integer prodPrice;
	@Column(name = "order_prod_qty")
	private Integer orderProdQty;
	@Column(name = "prod_comment_score")
	private Integer prodCommentScore;
	@Column(name = "prod_comment_text")
	private String prodCommentText;
	@Column(name = "res_prod_reply_text")
	private String resProdReplyText;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column(name = "prod_comment_timestamp")
	private Timestamp prodCommentTimestamp;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column(name = "res_prod_reply_timestamp")
	private Timestamp resProdReplyTimestamp;
	@Column(name = "acc_coupon_id")
	private Integer accCouponId;
	@Transient
	private String accName;
	
	public OrderDetail() {
	}

	@Override
	public String toString() {
		return "OrderDetail [orderDetailId=" + orderDetailId + ", orderId=" + orderId + ", prodId=" + prodId
				+ ", prodPrice=" + prodPrice + ", orderProdQty=" + orderProdQty + ", prodCommentScore="
				+ prodCommentScore + ", prodCommentText=" + prodCommentText + ", resProdReplyText=" + resProdReplyText
				+ ", prodCommentTimestamp=" + prodCommentTimestamp + ", resProdReplyTimestamp=" + resProdReplyTimestamp
				+ ", accCouponId=" + accCouponId + ", accName=" + accName + "]";
	}

	public Integer getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(Integer orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getProdId() {
		return prodId;
	}

	public void setProdId(Integer prodId) {
		this.prodId = prodId;
	}

	public Integer getProdPrice() {
		return prodPrice;
	}

	public void setProdPrice(Integer prodPrice) {
		this.prodPrice = prodPrice;
	}

	public Integer getOrderProdQty() {
		return orderProdQty;
	}

	public void setOrderProdQty(Integer orderProdQty) {
		this.orderProdQty = orderProdQty;
	}

	public Integer getProdCommentScore() {
		return prodCommentScore;
	}

	public void setProdCommentScore(Integer prodCommentScore) {
		this.prodCommentScore = prodCommentScore;
	}

	public String getProdCommentText() {
		return prodCommentText;
	}

	public void setProdCommentText(String prodCommentText) {
		this.prodCommentText = prodCommentText;
	}

	public String getResProdReplyText() {
		return resProdReplyText;
	}

	public void setResProdReplyText(String resProdReplyText) {
		this.resProdReplyText = resProdReplyText;
	}

	public Timestamp getProdCommentTimestamp() {
		return prodCommentTimestamp;
	}

	public void setProdCommentTimestamp(Timestamp prodCommentTimestamp) {
		this.prodCommentTimestamp = prodCommentTimestamp;
	}

	public Timestamp getResProdReplyTimestamp() {
		return resProdReplyTimestamp;
	}

	public void setResProdReplyTimestamp(Timestamp resProdReplyTimestamp) {
		this.resProdReplyTimestamp = resProdReplyTimestamp;
	}

	public Integer getAccCouponId() {
		return accCouponId;
	}

	public void setAccCouponId(Integer accCouponId) {
		this.accCouponId = accCouponId;
	}

	public String getAccName() {
		return accName;
	}

	public void setAccName(String accName) {
		this.accName = accName;
	}

	public OrderDetail(Integer orderDetailId, Integer orderId, Integer prodId, Integer prodPrice, Integer orderProdQty,
                       Integer prodCommentScore, String prodCommentText, String resProdReplyText, Timestamp prodCommentTimestamp,
                       Timestamp resProdReplyTimestamp, Integer accCouponId, String accName) {
		super();
		this.orderDetailId = orderDetailId;
		this.orderId = orderId;
		this.prodId = prodId;
		this.prodPrice = prodPrice;
		this.orderProdQty = orderProdQty;
		this.prodCommentScore = prodCommentScore;
		this.prodCommentText = prodCommentText;
		this.resProdReplyText = resProdReplyText;
		this.prodCommentTimestamp = prodCommentTimestamp;
		this.resProdReplyTimestamp = resProdReplyTimestamp;
		this.accCouponId = accCouponId;
		this.accName = accName;
	}
	
}