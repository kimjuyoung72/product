package com.kh.product.web.product.form;

import lombok.Data;

@Data
public class AddForm {


  private String pname;       //  PNAME	VARCHAR2(30 BYTE)	Yes		2

  private Integer count;   //  COUNT	NUMBER(3,0)	Yes		3
  private Integer price;      //  PRICE	NUMBER(4,0)	Yes		4
}
