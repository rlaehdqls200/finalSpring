package com.example.final_201930403.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Long productId;
    private String productName;
    private String userId;
    private String userName;
    private int price;
}
