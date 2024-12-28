package com.hermes.hermes.service;

import com.hermes.hermes.dto.Product;
import com.hermes.hermes.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    // ProductMapper 주입
    private final ProductMapper productMapper;

    @Autowired
    public ProductServiceImpl(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    @Override
    public Product getProduct(int product_reg_num) {
        Product product = productMapper.getProduct(product_reg_num);

        if (product == null) {
            System.out.println("(MY_DEBUG) Product not found for ID: " + product_reg_num);
        }

        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        // 모든 상품 목록을 조회
        return productMapper.getAllProducts();
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        // 카테고리별 상품 목록 조회
        return productMapper.getProductsByCategory(category);
    }

    @Override
    public void insertProduct(Product product) {
        // 상품 정보 등록
        productMapper.insertProduct(product);
    }

    @Override
    public void updateProduct(Product product) {
        // 상품 정보 수정
        productMapper.updateProduct(product);
    }

    @Override
    public void deleteProduct(int productId) {
        // 상품 삭제
        productMapper.deleteProduct(productId);
    }

    @Override
    public String getProductImagePath(int productId) {
        Product product = productMapper.getProduct(productId);
        if (product != null) {
            return product.getProduct_image_path();
        }
        return null;
    }
}