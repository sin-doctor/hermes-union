package com.hermes.hermes.controller;

import com.hermes.hermes.dto.Product;
import com.hermes.hermes.dto.Purchase;
import com.hermes.hermes.dto.User;
import com.hermes.hermes.service.ProductService;
import com.hermes.hermes.service.PurchaseService;
import com.hermes.hermes.service.UserService;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
public class IndexController {

    private final ProductService productService;
    private final UserService userService;
    private final PurchaseService purchaseService;

    @Autowired
    public IndexController(ProductService productService, UserService userService, PurchaseService purchaseService) {
        this.productService = productService;
        this.userService = userService;
        this.purchaseService = purchaseService;
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

    @PostMapping("/login-process")
    public String loginProcess(
            @RequestParam("login_Id") String userId,
            @RequestParam("login_Pw") String password,
            HttpSession session,
            RedirectAttributes redirectAttributes
    ) {
        // 로그인 검증
        User user = userService.loginUser(userId, password);

        if (user != null) {
            // 로그인 성공
            session.setAttribute("userId", user.getUser_id());
            session.setAttribute("userName", user.getUser_name());

            // 이전 페이지가 있는 경우 해당 페이지로 리다이렉트
            String previousPage = (String) session.getAttribute("previousPage");
            if (previousPage != null) {
                session.removeAttribute("previousPage");
                return "redirect:" + previousPage;
            }

            return "redirect:/"; // 메인 페이지로 리다이렉트
        } else {
            // 로그인 실패
            redirectAttributes.addFlashAttribute("errorMessage", "아이디 또는 비밀번호가 올바르지 않습니다.");
            return "redirect:/login";
        }
    }

    // 로그아웃 처리
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
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

    @PostMapping("/purchase_completed_page")
    public String purchaseCompletedPage(
            @RequestParam("productId") int productRegNum,
            @RequestParam("size") String selectedSize,
            HttpSession session,
            Model model
    ) {
        // 로그인한 사용자 정보 가져오기
        String userId = (String) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login";
        }

        // 상품 정보 조회
        Product product = productService.getProduct(productRegNum);
        if (product == null) {
            return "redirect:/error";
        }

        // 구매 정보 생성
        Purchase purchase = new Purchase();
        purchase.setPurchase_product_reg_num(productRegNum);
        purchase.setOrder_id(generateOrderId());
        purchase.setPurchase_product_size(selectedSize);
        purchase.setPurchase_user_id(userId);
        purchase.setPurchase_date(new java.util.Date()); // java.util.Date 명시적 사용
        purchase.setPurchase_status("완료");

        // 구매 정보 저장
        purchaseService.savePurchase(purchase);

        // 모델에 데이터 추가
        model.addAttribute("product", product);
        model.addAttribute("selectedSize", selectedSize);
        model.addAttribute("purchase", purchase);

        return "purchase_completed_page";
    }

    // 주문번호 생성 메서드
    private String generateOrderId() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String timestamp = sdf.format(new Date());
        String randomStr = String.format("%04d", (int)(Math.random() * 10000));
        return "ORD-" + timestamp + "-" + randomStr;
    }

    // 카테고리 페이지 이동
    @GetMapping("/category_page")
    public String categoryPage() {
        return "category_page";
    }
}
