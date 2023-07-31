package com.aic.proddemo.service;

import com.aic.proddemo.web.vm.CommentVM;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class CommentVMConverter implements Converter<Object[], CommentVM> {

    @Override
    public CommentVM convert(Object[] source) {
        CommentVM commentVM = new CommentVM();
        commentVM.setOrderDetailId((Integer) source[0]);
        commentVM.setOrderId((Integer) source[1]);
        commentVM.setProdId((Integer) source[2]);
        commentVM.setProdPrice((Integer) source[3]);
        commentVM.setOrderProdQty((Integer) source[4]);
        commentVM.setProdCommentScore((Integer) source[5]);
        commentVM.setProdCommentText((String) source[6]);
        commentVM.setResProdReplyText((String) source[7]);
        commentVM.setProdCommentTimestamp((Timestamp) source[8]);
        commentVM.setResProdReplyTimestamp((Timestamp) source[9]);
        commentVM.setAccCouponId((Integer) source[10]);
        commentVM.setAccId((Integer) source[11]);
        commentVM.setAccName((String) source[12]);

        return commentVM;
    }
}
