package com.aic.proddemo.service;

import com.aic.proddemo.web.vm.ProdVM;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProdVMConverter implements Converter<Object[], ProdVM> {

    @Override
    public ProdVM convert(Object[] source) {
        ProdVM prodVM = new ProdVM();
        prodVM.setProdId((Integer) source[0]);
        prodVM.setRestaurantId((Integer) source[1]);
        prodVM.setProdName((String) source[2]);
        prodVM.setProdText((String) source[3]);
        prodVM.setProdUserguide((String) source[4]);
        prodVM.setProdPrice((Integer) source[5]);
        prodVM.setProdQty((Integer) source[6]);
        prodVM.setProdState((Integer) source[7]);
        prodVM.setProdPic((byte[]) source[8]);
        prodVM.setProdCommentNumber((Integer) source[9]);
        prodVM.setProdCommentScore((Integer) source[10]);
        prodVM.setResName((String) source[11]);
        prodVM.setResAdd((String) source[12]);
        prodVM.setResType((String) source[13]);

        return prodVM;
    }
}
