package com.pgeasy.www;

import lombok.Builder;

@Builder
public record ApprovePayment(PaymentCompany paymentCompany, String secretKey, BaseApprovePayment baseApprovePayment) {

}
