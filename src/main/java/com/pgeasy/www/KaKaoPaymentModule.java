package com.pgeasy.www;

import lombok.Builder;

@Builder
public class KaKaoPaymentModule implements BasePaymentModule {
    private final String apiUrl = "https://open-api.kakaopay.com/online/v1/payment/ready";

    @Override
    public String getApiUrl() {
        return apiUrl;
    }

    @Override
    public String getAuthorization(String secretKey) {
        return "SECRET_KEY " + secretKey;
    }

    @Override
    public Class<? extends TossPayApprovePayment.Result> getResultClass() {
        return null;
    }
}
