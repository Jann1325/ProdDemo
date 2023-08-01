package com.aic.proddemo.repository;

import com.aic.proddemo.domain.Prod;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.Tuple;
import java.util.List;

@Repository
public interface ProdRepository extends JpaRepository<Prod, Integer> {
    @Query(value = "SELECT p.prod_id, r.restaurant_id, p.prod_name, p.prod_text, p.prod_userguide, " +
            "p.prod_price, p.prod_qty, p.prod_state, p.prod_pic, p.prod_comment_number, p.prod_comment_score, " +
            "r.res_name, r.res_add, GROUP_CONCAT(rt.res_type_name SEPARATOR ' / ') AS category_names " +
            "FROM chooeat.prod p " +
            "JOIN chooeat.restaurant r ON p.restaurant_id = r.restaurant_id " +
            "JOIN chooeat.res_type_detail rtd ON p.restaurant_id = rtd.restaurant_id " +
            "JOIN chooeat.res_type rt ON rtd.res_type_id = rt.res_type_id " +
            "GROUP BY p.prod_id, r.restaurant_id, r.res_name", nativeQuery = true)
    Page<Object[]> selectAll(Pageable pageable);

    @Query(value = "SELECT p.prod_id, r.restaurant_id, p.prod_name, p.prod_text, p.prod_userguide, " +
            "p.prod_price, p.prod_qty, p.prod_state, p.prod_pic, p.prod_comment_number, p.prod_comment_score, " +
            "r.res_name, r.res_add, GROUP_CONCAT(rt.res_type_name SEPARATOR ' / ') AS category_names " +
            "FROM chooeat.prod p " +
            "JOIN chooeat.restaurant r ON p.restaurant_id = r.restaurant_id " +
            "JOIN chooeat.res_type_detail rtd ON p.restaurant_id = rtd.restaurant_id " +
            "JOIN chooeat.res_type rt ON rtd.res_type_id = rt.res_type_id " +
            "where PROD_NAME LIKE %?% " +
            "GROUP BY p.prod_id, r.restaurant_id, r.res_name", nativeQuery = true)
    Page<Object[]> getByCompositeQuery(String searchText, Pageable pageable);

    @Query(value = "SELECT p.prod_id, r.restaurant_id, p.prod_name, p.prod_text, p.prod_userguide, " +
            "p.prod_price, p.prod_qty, p.prod_state, p.prod_pic, p.prod_comment_number, p.prod_comment_score, " +
            "r.res_name, r.res_add, GROUP_CONCAT(rt.res_type_name SEPARATOR ' / ') AS category_names " +
            "FROM chooeat.prod p " +
            "JOIN chooeat.restaurant r ON p.restaurant_id = r.restaurant_id " +
            "JOIN chooeat.res_type_detail rtd ON p.restaurant_id = rtd.restaurant_id " +
            "JOIN chooeat.res_type rt ON rtd.res_type_id = rt.res_type_id " +
            "where PROD_Id = ? " +
            "GROUP BY p.prod_id, r.restaurant_id, r.res_name ", nativeQuery = true)
    List<Object[]> selectByProdId(Integer prodId);

    @Query(value = "SELECT p.prod_id, r.restaurant_id, p.prod_name, p.prod_text, p.prod_userguide, " +
            "p.prod_price, p.prod_qty, p.prod_state, p.prod_pic, p.prod_comment_number, p.prod_comment_score, " +
            "r.res_name, r.res_add, GROUP_CONCAT(rt.res_type_name SEPARATOR ' / ') AS category_names " +
            "FROM chooeat.prod p " +
            "JOIN chooeat.restaurant r ON p.restaurant_id = r.restaurant_id " +
            "JOIN chooeat.res_type_detail rtd ON p.restaurant_id = rtd.restaurant_id " +
            "JOIN chooeat.res_type rt ON rtd.res_type_id = rt.res_type_id " +
            "GROUP BY p.prod_id, r.restaurant_id, r.res_name " +
            "HAVING category_names LIKE %:category%", nativeQuery = true)
    Page<Object[]> getByCategory(String category, Pageable pageable);


}
