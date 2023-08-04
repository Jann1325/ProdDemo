package com.aic.proddemo.web.vm;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import lombok.Data;

@Data
public class ProdRequestVM {

    private String prodName;

    private String resName;

    @JsonSetter(nulls = Nulls.SKIP)
    private Integer prodPriceFrom =  Integer.valueOf(0);

    @JsonSetter(nulls = Nulls.SKIP)
    private Integer prodPriceTo =  Integer.valueOf(99999);

    @JsonSetter(nulls = Nulls.SKIP)
    private Integer prodCommentScoreFrom =  Integer.valueOf(0);

    @JsonSetter(nulls = Nulls.SKIP)
    private  Integer prodCommentScoreTo =  Integer.valueOf(5);

    private String resType;

    private String resAdd;

}