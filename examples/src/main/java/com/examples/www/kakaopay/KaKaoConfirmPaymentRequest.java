package com.examples.www.kakaopay;

import lombok.Builder;

@Builder
public record KaKaoConfirmPaymentRequest(Long orderId, String userId, String itemName, Integer quantity, Integer totalAmount) {

}
