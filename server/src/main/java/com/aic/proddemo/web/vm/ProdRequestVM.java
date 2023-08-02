package com.aic.proddemo.web.vm;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProdRequestVM {

    private String prodName;
    private String resName;
    private Integer prodPrice;
    private Double prodCommentScore;
    private String resType;
    private String resAdd;

//
//    private Integer prodId;
//
//    private Integer restaurantId;
//
//    @JsonSetter(nulls = Nulls.SKIP)
//    private String prodName= "%";
//
//    private Integer prodPrice;
//
//    private Integer prodQty;
//
//    private Integer prodState;
//
//    private Integer prodCommentNumber;
//
//    private Integer prodCommentScore;
//
//    @JsonSetter(nulls = Nulls.SKIP)
//    private String resName= "%";
//
//    @JsonSetter(nulls = Nulls.SKIP)
//    private String resAdd= "%";
//
//    private Integer resTypeId;
//
//    @JsonSetter(nulls = Nulls.SKIP)
//    private String resType= "%";
}