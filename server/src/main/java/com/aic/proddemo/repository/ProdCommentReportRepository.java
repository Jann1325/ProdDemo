package com.aic.proddemo.repository;


import com.aic.proddemo.domain.ProdCommentReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdCommentReportRepository extends JpaRepository<ProdCommentReport,Integer> {


}
