package com.aic.proddemo.repository;


import com.aic.proddemo.domain.OrderDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
    @Query(value = "SELECT OD.*, O.ACC_ID, A.ACC_NAME " +
            "FROM ORDER_DETAIL OD " +
            "JOIN chooeat.ORDER O ON OD.ORDER_ID = O.ORDER_ID " +
            "JOIN ACCOUNT A ON O.ACC_ID = A.ACC_ID " +
            "WHERE OD.PROD_ID = ?", nativeQuery = true)
    List<Object[]> findByProdId(Integer prodId);
}
