package com.pgeasy.www;

import lombok.Builder;
import lombok.Getter;

@Builder
public class KaKaoPaymentModule implements BasePaymentModule {
    private final String apiUrl = "https://open-api.kakaopay.com/online/v1/payment/ready";
    private String cid;
    private Long partner_order_id;
    private String partner_user_id;
    private String item_name;
    private Integer total_amount;
    private Integer tax_free_amount;
    private String approval_url;
    private String cancel_url;
    private String fail_url;

    @Override
    public String getApiUrl() {
        return apiUrl;
    }

    @Override
    public String getAuthorization(String secretKey) {
        return "SECRET_KEY " + secretKey;
    }

    @Override
    public Class<? extends Result> getResultClass() {
        return KaKaoPaymentModule.Result.class;
    }

    @Getter
    public static class Result implements BaseResult {

    }
}
