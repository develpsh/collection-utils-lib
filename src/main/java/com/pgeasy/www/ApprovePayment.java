package com.pgeasy.www;

import lombok.Builder;

@Builder
class ApprovePayment {
    private PaymentCompany paymentCompany;
    private String secretKey;
    private BaseApprovePayment baseApprovePayment;
}
