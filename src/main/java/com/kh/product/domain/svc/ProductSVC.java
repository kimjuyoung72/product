package com.kh.product.domain.svc;

import com.kh.product.domain.Product;

import java.util.List;

public interface ProductSVC {

  /**
   * 등록
   * @param product
   * @return 상품정보
   */
  Long add(Product product);

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
}
