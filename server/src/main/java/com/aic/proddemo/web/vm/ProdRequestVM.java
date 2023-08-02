package com.aic.proddemo.web.vm;

import lombok.Data;

@Data
public class ProdRequestVM {

    private String prodName;

    private String resName;

    private Integer prodPrice;

    private Double prodCommentScore;

    private String resType;

    private String resAdd;

}