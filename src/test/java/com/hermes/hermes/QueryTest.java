package com.hermes.hermes;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class QueryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testDatabaseConnection() {
        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            System.out.println("Database Connection Successful!");

            // 제품 테이블의 모든 레코드 조회
            List<Map<String, Object>> products = jdbcTemplate.queryForList("SELECT * FROM product");

            System.out.println("Total Products: " + products.size());

            // 각 제품의 상세 정보 출력
            for (Map<String, Object> product : products) {
                System.out.println("=== Product Details ===");
                for (Map.Entry<String, Object> entry : product.entrySet()) {
                    System.out.println(entry.getKey() + ": " + entry.getValue());
                }
                System.out.println("-------------------");
            }
        } catch (SQLException e) {
            System.err.println("Database Connection Failed: " + e.getMessage());
        }
    }
}