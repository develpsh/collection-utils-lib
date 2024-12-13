package com.pgeasy.www;

import lombok.Builder;

@Builder
public class PgEasyCreatePaymentRequest {
    private String orderId;
    private Integer amount;
    private String paymentKey;
}
