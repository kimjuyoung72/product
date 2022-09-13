package com.kh.product.web.product;

import com.kh.product.domain.Product;
import com.kh.product.domain.svc.ProductSVC;
import com.kh.product.web.product.form.AddForm;
import com.kh.product.web.product.form.EditForm;
import com.kh.product.web.product.form.ItemForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

  private final ProductSVC productSVC;

  //등록양식
  @GetMapping("/add")
  public String addForm() {

    return "/product/addForm";
  }
  //등록처리
  @PostMapping("/add")
  public String add(@Valid @ModelAttribute("form") AddForm addForm, RedirectAttributes redirectAttributes) {

    Product product = new Product();
    product.setPname(addForm.getPname());
    product.setCount(addForm.getCount());
    product.setPrice(addForm.getPrice());

//    Product addedProduct = productSVC.add(product);
    Long pid = productSVC.add(product);

//    Long pid = addedProduct.getProductId();
    log.info("pid", pid);
    redirectAttributes.addAttribute("pid", pid);

    return "redirect:/products/{pid}";
  }
  //상품상세조회
  @GetMapping("/{pid}")
  public String findById(@PathVariable("pid") Long pid, Model model) {

    Product findedProduct = productSVC.findById(pid);
    ItemForm itemForm = new ItemForm();
    itemForm.setProductId(findedProduct.getProductId());
    itemForm.setPname(findedProduct.getPname());
    itemForm.setCount(findedProduct.getCount());
    itemForm.setPrice(findedProduct.getPrice());
//    BeanUtils.copyProperties(findedProduct, itemForm);
    model.addAttribute("itemForm",itemForm);

    return "product/itemForm";
  }
  //수정양식
  @GetMapping("/{pid}/edit")
  public String editForm(@PathVariable("pid") Long pid, Model model) {

    Product findedProduct = productSVC.findById(pid);
    EditForm editForm = new EditForm();
    BeanUtils.copyProperties(findedProduct,editForm);
    model.addAttribute("editForm", editForm);

    return "product/editForm";
  }
  //수정처리
  @PostMapping("/{pid}/edit")
  public String edit(@PathVariable("pid") Long pid, EditForm editForm) {

    Product product = new Product();
    product.setProductId(pid);
    product.setPname(editForm.getPname());
    product.setCount(editForm.getCount());
    product.setPrice(editForm.getPrice());

    productSVC.update(pid, product);

    return "redirect:/products/{pid}";
  }
  //상품삭제
  @GetMapping("/{pid}/del")
  public String del(@PathVariable("pid") Long pid) {
    int result = 0;

    result = productSVC.del(pid);

    log.info("del:{}", result);

    return "redirect:/products";
  }
  //상품목록
  @GetMapping
  public String all(Model model) {

    List<Product> list = productSVC.all();
    model.addAttribute("list", list);

    return "product/all";
  }
}
