package com.pgeasy.www;

import lombok.Builder;
import lombok.Getter;

@Builder
public class KaKaoPayApprovePayment implements BaseApprovePayment {
    private final String apiUrl = "https://open-api.kakaopay.com/online/v1/payment/approve";
    private String cid;
    private String tid;
    private String partner_order_id;
    private String partner_user_id;
    private String pg_token;

    @Override
    public String getApiUrl() {
        return apiUrl;
    }

    @Override
    public String getAuthorization(String secretKey) {
        return "SECRET_KEY " + secretKey;
    }

    @Override
    public Class<Result> getResultClass() {
        return null;
    }

    @Getter
    public static class Result implements BaseResult {

    }
}
