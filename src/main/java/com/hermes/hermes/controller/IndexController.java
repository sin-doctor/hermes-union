package com.hermes.hermes.controller;

import com.hermes.hermes.dto.Product;
import com.hermes.hermes.dto.User;
import com.hermes.hermes.service.ProductService;
import com.hermes.hermes.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
public class IndexController {

    private final ProductService productService;
    private final UserService userService;

    @Autowired
    public IndexController(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }

    // 홈 페이지에 이미지 조회 로직 추가
    @GetMapping("/index/{productId}")
    public String getProductImageForIndex(@PathVariable int productId, Model model) {
        String productImage = userService.getImage(productId);
        if (productImage == null) {
            model.addAttribute("error", "이미지를 찾을 수 없습니다.");
        } else {
            model.addAttribute("productImage", productImage);
        }
        return "index"; // 템플릿 이름
    }

    // 회원가입 페이지 이동
    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    // 로그인 페이지 이동
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // 로그인 검색 페이지 이동
    @GetMapping("/login_search")
    public String loginBySearch() {
        return "login_search";
    }

    // 회원가입 완료 처리
    @PostMapping("/signup-success")
    public String signup(@ModelAttribute User user) {
        if (user.getUser_id() == null || user.getUser_id().isEmpty()) {
            throw new IllegalArgumentException("User ID는 필수 항목입니다.");
        }
        userService.insertUser(user);
        return "signup-success";
    }

    // 제품 상세 페이지
    @GetMapping("/product_details_page/{product_reg_num}")
    public String productDetailsPage(@PathVariable int product_reg_num, Model model) {
        Product product = productService.getProduct(product_reg_num);
        if (product == null) {
            model.addAttribute("error", "상품 정보를 찾을 수 없습니다.");
            return "error";
        }

        // 사이즈 선택 옵션 추가
        List<String> sizes = Arrays.asList("250", "260", "270", "280");
        model.addAttribute("product", product);
        model.addAttribute("sizes", sizes);

        return "product_details_page";
    }

    @GetMapping("/purchase_completed_page/{product_reg_num}")
    public String purchaseCompletedPage(@PathVariable int product_reg_num, Model model) {
        // todo 로그인 세션 확인 로직 추가 필요
        Product product = productService.getProduct(product_reg_num);
        model.addAttribute("product", product);
        return "purchase_completed_page";
    }

    // 카테고리 페이지 이동
    @GetMapping("/category_page")
    public String categoryPage() {
        return "category_page";
    }

    // 제품 이미지 제공 API
    @GetMapping("/{productId}/image")
    @ResponseBody
    public String getProductImage(@PathVariable int productId) {
        return productService.getProductImagePath(productId);
    }
}
