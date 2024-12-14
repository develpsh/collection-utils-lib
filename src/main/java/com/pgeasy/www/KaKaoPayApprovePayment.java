package com.pgeasy.www;

import lombok.Builder;

@Builder
public class KaKaoPayApprovePayment implements BaseApprovePayment {
    private String paymentKey;
    private Long orderId;
    private Integer amount;
}
