package com.kh.product.domain.dao;

import com.kh.product.domain.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ProductDAOImpl implements ProductDAO{

  private final JdbcTemplate jt;
  /**
   * 등록
   *
   * @param product
   * @return 상품정보
   */
  @Override
  public int add(Product product) {
    int result = 0;

    StringBuffer sql = new StringBuffer();
    sql.append("insert into product(product_id, pname, count, price) ");
    sql.append("values (?,?,?,?) ");

    result = jt.update(sql.toString(), product.getProductId(), product.getPname(), product.getCount(), product.getPrice());

    return result;
  }

  /**
   * 수정
   *
   * @param productId
   * @param product
   * @return 수정건수
   */
  @Override
  public int update(Long productId, Product product) {
    int result = 0;

    StringBuffer sql = new StringBuffer();
    sql.append("update product ");
    sql.append("   set pname = ?, ");
    sql.append("       count = ?, ");
    sql.append("       price = ? ");
    sql.append(" where product_id = ? ");

    result = jt.update(sql.toString(), product.getPname(), product.getCount(), product.getPrice(), productId);
    return result;
  }

  /**
   * 조회
   *
   * @param productId
   * @return 상품정보
   */
  @Override
  public Product findById(Long productId) {
    StringBuffer sql = new StringBuffer();
    sql.append("select product_id, pname, count, price ");
    sql.append("  from product ");
    sql.append(" where product_id = ? ");

    Product findedProduct = null;
    try {
//      BeanPropertyRowMapper는 매핑되는 자바클래스에 디플토 생성자 필수!
      findedProduct = jt.queryForObject(sql.toString(), new BeanPropertyRowMapper<>(Product.class), productId);
    } catch (DataAccessException e) {
      log.info("찾고자하는 상품이 없습니다.{}", productId);
    }


    return findedProduct;
  }

  /**
   * 삭제
   *
   * @param productId
   * @return 삭제건수
   */
  @Override
  public int del(Long productId) {
    int result = 0;
    String sql = "delete from product where product_id = ? ";
    result = jt.update(sql, productId);

    return result;
  }

  /**
   * 목록
   *
   * @return 상품목록
   */
  @Override
  public List<Product> all() {

    StringBuffer sql = new StringBuffer();

    sql.append("select product_id, pname, count, price ");
    sql.append("  from product ");

    List<Product> result = jt.query(sql.toString(), new BeanPropertyRowMapper<>(Product.class));

    return result;
  }

  /**
   * 상품아이디 시퀀스
   *
   * @return
   */
  @Override
  public Long generatePid() {
    String sql = "select product_product_id_seq.nextval from dual ";
    Long productId = jt.queryForObject(sql, Long.class);


    return productId;
  }
}
