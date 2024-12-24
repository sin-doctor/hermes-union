package com.hermes.hermes.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Product{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int product_reg_num;
    private String product_name;
    private int product_price;
    private String product_description;
    private String product_image;
    private String product_category;
    private int product_size;
}
