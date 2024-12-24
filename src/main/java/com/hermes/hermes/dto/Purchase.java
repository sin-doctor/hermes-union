package com.hermes.hermes.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Purchase {
    private int purchase_reg_num;
    private String purchase_id;
    private String purchase_user_id;
    private String purchase_date;
    private String purchase_status;
}
