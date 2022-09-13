package com.kh.product.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

  private Long productId;  //  PRODUCT_ID	NUMBER(10,0)	No		1
  private String pname;       //  PNAME	VARCHAR2(30 BYTE)	Yes		2
  private Integer count;   //  COUNT	NUMBER(3,0)	Yes		3
  private Integer price;      //  PRICE	NUMBER(4,0)	Yes		4
}
