package com.aic.proddemo.web.vm;

import java.sql.Timestamp;

public class CommentVM {

    private Integer orderDetailId;
    private Integer orderId;
    private Integer prodId;
    private Integer prodPrice;
    private Integer orderProdQty;
    private Integer prodCommentScore;
    private String prodCommentText;
    private String resProdReplyText;
    private Timestamp prodCommentTimestamp;
    private Timestamp resProdReplyTimestamp;
    private Integer accCouponId;
    private Integer accId;
    private String accName;

    public CommentVM() {
    }

    public CommentVM(Integer orderDetailId, Integer orderId, Integer prodId, Integer prodPrice, Integer orderProdQty, Integer prodCommentScore, String prodCommentText, String resProdReplyText, Timestamp prodCommentTimestamp, Timestamp resProdReplyTimestamp, Integer accCouponId, Integer accId, String accName) {
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
        this.accId = accId;
        this.accName = accName;
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

    public Integer getAccId() {
        return accId;
    }

    public void setAccId(Integer accId) {
        this.accId = accId;
    }

    public String getAccName() {
        return accName;
    }

    public void setAccName(String accName) {
        this.accName = accName;
    }
}
