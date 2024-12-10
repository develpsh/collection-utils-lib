package com.pgeasy.www.dto.request;

import lombok.Builder;

@Builder
public class PgEasyCreatePaymentRequest {
    private String orderId;
    private Integer amount;
    private String paymentKey;
}
