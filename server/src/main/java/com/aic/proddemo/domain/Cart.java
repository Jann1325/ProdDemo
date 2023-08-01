package com.aic.proddemo.domain;

import lombok.Data;

import javax.persistence.*;
//@Data
@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id", updatable = false)
    private Integer cartId;

    @Column(name = "acc_id")
    private Integer accId;

    @Column(name = "prod_id")
    private Integer prodId;

    @Column(name = "prod_number")
    private Integer prodNumber;

    public Cart(Integer cartId, Integer accId, Integer prodId, Integer prodNumber) {
        this.cartId = cartId;
        this.accId = accId;
        this.prodId = prodId;
        this.prodNumber = prodNumber;
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public Integer getAccId() {
        return accId;
    }

    public void setAccId(Integer accId) {
        this.accId = accId;
    }

    public Integer getProdId() {
        return prodId;
    }

    public void setProdId(Integer prodId) {
        this.prodId = prodId;
    }

    public Integer getProdNumber() {
        return prodNumber;
    }

    public void setProdNumber(Integer prodNumber) {
        this.prodNumber = prodNumber;
    }
}
