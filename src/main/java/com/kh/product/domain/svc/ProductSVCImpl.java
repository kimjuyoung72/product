package com.kh.product.domain.svc;

import com.kh.product.domain.Product;
import com.kh.product.domain.dao.ProductDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductSVCImpl implements ProductSVC{

  private final ProductDAO productDAO;
  /**
   * 등록
   *
   * @param product
   * @return 상품정보
   */
  @Override
  public Long add(Product product) {
    Long generatedProductId = productDAO.generatePid();
    log.info("pid:{}", generatedProductId);
    product.setProductId(generatedProductId);
    productDAO.add(product);
//    return productDAO.findById(generatedProductId);
    return generatedProductId;
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
    return productDAO.update(productId, product);
  }

  /**
   * 조회
   *
   * @param productId
   * @return 상품정보
   */
  @Override
  public Product findById(Long productId) {
    return productDAO.findById(productId);
  }

  /**
   * 삭제
   *
   * @param productId
   * @return 삭제건수
   */
  @Override
  public int del(Long productId) {
    return productDAO.del(productId);
  }

  /**
   * 목록
   *
   * @return 상품목록
   */
  @Override
  public List<Product> all() {
    return productDAO.all();
  }
}
