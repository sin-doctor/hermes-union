package com.hermes.hermes.service;

import com.hermes.hermes.dto.Product;
import java.util.List;

public interface ProductService {
    // 단일 상품 조회
    Product getProduct(int productId);

    // 모든 상품 목록 조회
    List<Product> getAllProducts();

    // 카테고리별 상품 조회
    List<Product> getProductsByCategory(String category);

    // 상품 등록
    void insertProduct(Product product);

    // 상품 정보 수정
    void updateProduct(Product product);

    // 상품 삭제
    void deleteProduct(int productId);

    String getProductImagePath(int productId);
}