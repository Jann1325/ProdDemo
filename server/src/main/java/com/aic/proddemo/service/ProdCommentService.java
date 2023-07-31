package com.aic.proddemo.service;

import com.aic.proddemo.domain.OrderDetail;
import com.aic.proddemo.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdCommentService {
    private final OrderDetailRepository orderDetailRepository;

    @Autowired
    public ProdCommentService(OrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }

    public List<OrderDetail> getSortedComments(String sortParam, int prodId) {
        List<OrderDetail> list;
        if ("all".equals(sortParam)) {
            // 若排序參數為 "all"，則返回所有評論
            list = convertToObjectList(orderDetailRepository.findByProdId(prodId));
        } else {
            int filterScore = Integer.parseInt(sortParam);
            List<Object[]> orderDetailList = orderDetailRepository.findByProdId(prodId);

            list = convertToObjectList(orderDetailList).stream()
                    .filter(orderDetail -> orderDetail.getProdCommentScore() == filterScore)
                    .collect(Collectors.toList());
        }

        return list;
    }

    private List<OrderDetail> convertToObjectList(List<Object[]> objectList) {
        List<OrderDetail> orderDetailList = new ArrayList<>();
        for (Object[] orderDetailProps : objectList) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderDetailId((Integer) orderDetailProps[0]);
            orderDetail.setOrderId((Integer) orderDetailProps[1]);
            orderDetail.setProdId((Integer) orderDetailProps[2]);
            orderDetail.setProdPrice((Integer) orderDetailProps[3]);
            orderDetail.setOrderProdQty((Integer) orderDetailProps[4]);
            orderDetail.setProdCommentScore((Integer) orderDetailProps[5]);
            orderDetail.setProdCommentText((String) orderDetailProps[6]);
            orderDetail.setResProdReplyText((String) orderDetailProps[7]);
            orderDetail.setProdCommentTimestamp((Timestamp) orderDetailProps[8]);
            orderDetail.setResProdReplyTimestamp((Timestamp) orderDetailProps[9]);
            orderDetail.setAccCouponId((Integer) orderDetailProps[10]);
            orderDetailList.add(orderDetail);
        }
        return orderDetailList;
    }
}
