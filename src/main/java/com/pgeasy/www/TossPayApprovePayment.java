package com.pgeasy.www;

import lombok.Builder;

@Builder
public class TossPayApprovePayment implements BaseApprovePayment {
    private String paymentKey;
    private String orderId;
    private Integer amount;
}
