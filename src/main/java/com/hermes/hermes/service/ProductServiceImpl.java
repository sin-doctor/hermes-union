package com.hermes.hermes.service;

import com.hermes.hermes.dto.Product;
import com.hermes.hermes.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    // ProductMapper 주입
    private final ProductMapper productMapper;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductServiceImpl(ProductMapper productMapper, JdbcTemplate jdbcTemplate) {
        this.productMapper = productMapper;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Product getProduct(int product_reg_num) {
        // 로그 추가
        Product product = productMapper.getProduct(product_reg_num);

        // 상세 로깅
        if (product == null) {
            System.out.println("(MYDEBUG) Product not found for ID: " + product_reg_num);
            // 직접 데이터베이스 확인 쿼리 실행
            int count = jdbcTemplate.queryForObject(
                    "SELECT COUNT(*) FROM product WHERE product_reg_num = ?",
                    Integer.class,
                    product_reg_num
            );
            System.out.println("(MYDEBUG) Record count for this ID: "+ count);
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
    public byte[] getProductImage(int productId) {
        Product product = productMapper.getProduct(productId);
        if (product != null) {
            return product.getProduct_image();
        }
        return null;
    }

}