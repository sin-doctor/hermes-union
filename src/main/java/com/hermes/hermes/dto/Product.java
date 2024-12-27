package com.hermes.hermes.dto;

import lombok.*;

import java.sql.Blob;
@AllArgsConstructor    // 필수 생성자
@NoArgsConstructor     // 기본생성자
@Setter                // setter 줄임말로 사용
@Getter                // getter 줄임말로 사용
@ToString             //toString 줄임말로 사용


public class Product {
    private int product_reg_num;
    private String product_name;
    private int product_price;
    private String product_description;
    private Blob product_image;
    private String product_category;
    private int product_size;
}
