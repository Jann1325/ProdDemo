package com.aic.proddemo.service;

import com.aic.proddemo.domain.OrderDetail;
import com.aic.proddemo.domain.ProdCommentReport;
import com.aic.proddemo.repository.OrderDetailRepository;
import com.aic.proddemo.repository.ProdCommentReportRepository;
import com.aic.proddemo.repository.ProdRepository;
import com.aic.proddemo.web.vm.CommentVM;
import com.aic.proddemo.web.vm.ProdVM;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ProdDetailService {
    @Autowired
    private ProdRepository prodRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private ProdCommentReportRepository prodCommentReportRepository;
    @Autowired
    private ProdVMConverter prodVMConverter;
    @Autowired
    private CommentVMConverter commentVMConverter;

    public String getProdDetails(Integer prodId) {
        List<Object[]> orderDetails = orderDetailRepository.findByProdId(prodId);
        List<CommentVM> commentVMList = new ArrayList<>();
        for (Object[] result : orderDetails) {
            CommentVM commentVM = commentVMConverter.convert(result);
            commentVMList.add(commentVM);
        }

        List<Object[]> prod = prodRepository.selectByProdId(prodId);
        List<ProdVM> prodVMList = new ArrayList<>();

        for (Object[] result : prod) {
            ProdVM prodVM = prodVMConverter.convert(result);
            prodVMList.add(prodVM);
        }

        // 使用ObjectMapper處理JSON轉換
        ObjectMapper objectMapper = new ObjectMapper();
        String prodJsonStr = null;
        String orderDetailsJsonStr = null;
        try {
            prodJsonStr = objectMapper.writeValueAsString(prodVMList);
            orderDetailsJsonStr = objectMapper.writeValueAsString(commentVMList);
        } catch (Exception e) {
            e.printStackTrace();
        }

      return "{\"prod\": " + prodJsonStr + ", \"orderDetails\": " + orderDetailsJsonStr + "}";
    }

    public String reportProdComment(Map<String, Object> requestBody) {
        String reason = (String) requestBody.get("reason");
        int orderDetailId = (int) requestBody.get("orderDetailId");

        ProdCommentReport prodCommentReport = new ProdCommentReport();
        prodCommentReport.setAccId(1);
        prodCommentReport.setOrderDetailId(orderDetailId);
        prodCommentReport.setProdCommentReportReason(reason);

        ProdCommentReport savedProdCommentReport = prodCommentReportRepository.save(prodCommentReport);
        if (savedProdCommentReport != null) {
            return "評論檢舉成功！PROD_COMMENT_REPORT_ID為：" + savedProdCommentReport.getProdCommentReportId();
        } else {
            return "無法建立評論檢舉。";
        }
    }
}
