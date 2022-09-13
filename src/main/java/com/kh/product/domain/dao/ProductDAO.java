package com.kh.product.domain.dao;

import com.kh.product.domain.Product;

import java.util.List;

public interface ProductDAO {

  /**
   * 상품등록
   * @param product
   * @return 등록건수
   */
  int add(Product product);

  /**
   * 수정
   * @param productId
   * @param product
   * @return 수정건수
   */
  int update(Long productId, Product product);

  /**
   * 조회
   * @param productId
   * @return 상품정보
   */
  Product findById(Long productId);

  /**
   * 삭제
   * @param productId
   * @return 삭제건수
   */
  int del(Long productId);

  /**
   * 목록
   * @return 상품목록
   */
  List<Product> all();

  /**
   * 상품아이디 시퀀스
   * @return
   */
  Long generatePid();
}
