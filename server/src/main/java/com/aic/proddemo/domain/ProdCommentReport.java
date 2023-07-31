package com.aic.proddemo.domain;


import javax.persistence.*;

@Entity
@Table(name = "prod_comment_report")
public class ProdCommentReport{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "prod_comment_report_id", updatable = false)
	private Integer prodCommentReportId;
	@Column(name = "acc_id")
	private Integer accId;
	@Column(name = "order_detail_id")
	private Integer orderDetailId;
	@Column(name = "prod_comment_report_reason")
	private String prodCommentReportReason;

	public ProdCommentReport() {
	}

	public ProdCommentReport(Integer prodCommentReportId, Integer accId, Integer orderDetailId,
                             String prodCommentReportReason) {
		super();
		this.prodCommentReportId = prodCommentReportId;
		this.accId = accId;
		this.orderDetailId = orderDetailId;
		this.prodCommentReportReason = prodCommentReportReason;
	}

	public Integer getProdCommentReportId() {
		return prodCommentReportId;
	}

	public void setProdCommentReportId(Integer prodCommentReportId) {
		this.prodCommentReportId = prodCommentReportId;
	}

	public Integer getAccId() {
		return accId;
	}

	public void setAccId(Integer accId) {
		this.accId = accId;
	}

	public Integer getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(Integer orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	public String getProdCommentReportReason() {
		return prodCommentReportReason;
	}

	public void setProdCommentReportReason(String prodCommentReportReason) {
		this.prodCommentReportReason = prodCommentReportReason;
	}


}
