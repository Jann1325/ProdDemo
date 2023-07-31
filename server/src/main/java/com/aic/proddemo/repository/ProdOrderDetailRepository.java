package com.aic.proddemo.repository;

import com.aic.proddemo.domain.ProdOrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface ProdOrderDetailRepository extends JpaRepository<ProdOrderDetail, Integer> {
}
