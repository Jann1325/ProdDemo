package com.aic.proddemo.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Arrays;
@Data
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
}


