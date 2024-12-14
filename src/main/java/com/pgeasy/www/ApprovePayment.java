package com.pgeasy.www;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApprovePayment {
    private PaymentCompany paymentCompany;
    private String secretKey;
    private BaseApprovePayment baseApprovePayment;
}
