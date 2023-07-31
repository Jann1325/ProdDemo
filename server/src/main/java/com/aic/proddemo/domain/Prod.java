package com.aic.proddemo.domain;

import javax.persistence.*;
import java.util.Arrays;
@Entity
@Table(name = "prod")
public class Prod {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "prod_id", updatable = false)
	private Integer prodId;
	@Column(name = "restaurant_id")
	private Integer restaurantId;
	@Column(name = "prod_name")
	private String prodName;
	@Column(name = "prod_text")
	private String prodText;
	@Column(name = "prod_userguide")
	private String prodUserguide;
	@Column(name = "prod_price")
	private Integer prodPrice;
	@Column(name = "prod_qty")
	private Integer prodQty;
	@Column(name = "prod_state")
	private Integer prodState;
	@Column(name = "prod_pic")
	private byte[] prodPic;
	@Column(name = "prod_comment_number")
	private Integer prodCommentNumber;
	@Column(name = "prod_comment_score")
	private Integer prodCommentScore;

	@Transient
	private String prodPicBase64;

	@OneToOne
	@JoinColumn(name = "restaurant_id", insertable = false, updatable = false)
	private Restaurant restaurant;

	public Prod() {
	}

	public Integer getProdId() {
		return prodId;
	}

	public void setProdId(Integer prodId) {
		this.prodId = prodId;
	}

	public Integer getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(Integer restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public String getProdText() {
		return prodText;
	}

	public void setProdText(String prodText) {
		this.prodText = prodText;
	}

	public String getProdUserguide() {
		return prodUserguide;
	}

	public void setProdUserguide(String prodUserguide) {
		this.prodUserguide = prodUserguide;
	}

	public Integer getProdPrice() {
		return prodPrice;
	}

	public void setProdPrice(Integer prodPrice) {
		this.prodPrice = prodPrice;
	}

	public Integer getProdQty() {
		return prodQty;
	}

	public void setProdQty(Integer prodQty) {
		this.prodQty = prodQty;
	}

	public Integer getProdState() {
		return prodState;
	}

	public void setProdState(Integer prodState) {
		this.prodState = prodState;
	}

	public byte[] getProdPic() {
		return prodPic;
	}

	public void setProdPic(byte[] prodPic) {
		this.prodPic = prodPic;
	}

	public Integer getProdCommentNumber() {
		return prodCommentNumber;
	}

	public void setProdCommentNumber(Integer prodCommentNumber) {
		this.prodCommentNumber = prodCommentNumber;
	}

	public Integer getProdCommentScore() {
		return prodCommentScore;
	}

	public void setProdCommentScore(Integer prodCommentScore) {
		this.prodCommentScore = prodCommentScore;
	}


	public Prod(Integer prodId, Integer restaurantId, String prodName, String prodText, String prodUserguide,
			Integer prodPrice, Integer prodQty, Integer prodState, byte[] prodPic, Integer prodCommentNumber,
			Integer prodCommentScore) {
		super();
		this.prodId = prodId;
		this.restaurantId = restaurantId;
		this.prodName = prodName;
		this.prodText = prodText;
		this.prodUserguide = prodUserguide;
		this.prodPrice = prodPrice;
		this.prodQty = prodQty;
		this.prodState = prodState;
		this.prodPic = prodPic;
		this.prodCommentNumber = prodCommentNumber;
		this.prodCommentScore = prodCommentScore;
	}

	public String getProdPicBase64() {
		return prodPicBase64;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public void setProdPicBase64(String prodPicBase64) {
		this.prodPicBase64 = prodPicBase64;
	}

	@Override
	public String toString() {
		return "Prod{" +
				"prodId=" + prodId +
				", restaurantId=" + restaurantId +
				", prodName='" + prodName + '\'' +
				", prodText='" + prodText + '\'' +
				", prodUserguide='" + prodUserguide + '\'' +
				", prodPrice=" + prodPrice +
				", prodQty=" + prodQty +
				", prodState=" + prodState +
				", prodPic=" + Arrays.toString(prodPic) +
				", prodPicBase64='" + prodPicBase64 + '\'' +
				", prodCommentNumber=" + prodCommentNumber +
				", prodCommentScore=" + prodCommentScore +
				", restaurant=" + restaurant +
				'}';
	}

	public Prod(Integer prodId, Integer restaurantId, String prodName, String prodText, String prodUserguide, Integer prodPrice, Integer prodQty, Integer prodState, byte[] prodPic, String prodPicBase64, Integer prodCommentNumber, Integer prodCommentScore, Restaurant restaurant) {
		this.prodId = prodId;
		this.restaurantId = restaurantId;
		this.prodName = prodName;
		this.prodText = prodText;
		this.prodUserguide = prodUserguide;
		this.prodPrice = prodPrice;
		this.prodQty = prodQty;
		this.prodState = prodState;
		this.prodPic = prodPic;
		this.prodPicBase64 = prodPicBase64;
		this.prodCommentNumber = prodCommentNumber;
		this.prodCommentScore = prodCommentScore;
		this.restaurant = restaurant;
	}
}


