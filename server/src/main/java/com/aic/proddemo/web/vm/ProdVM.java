package com.aic.proddemo.web.vm;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import lombok.Data;

@Data
public class ProdVM {

    private Integer prodId;

    private Integer restaurantId;

    @JsonSetter(nulls = Nulls.SKIP)
    private String prodName= "%";

    private String prodText;

    private String prodUserguide;

    private Integer prodPrice;

    private Integer prodQty;

    private Integer prodState;

    private byte[] prodPic;

    private Integer prodCommentNumber;

    private Integer prodCommentScore;

    @JsonSetter(nulls = Nulls.SKIP)
    private String resName= "%";

    @JsonSetter(nulls = Nulls.SKIP)
    private String resAdd= "%";

    private Integer resTypeId;

    @JsonSetter(nulls = Nulls.SKIP)
    private String resType= "%";
}