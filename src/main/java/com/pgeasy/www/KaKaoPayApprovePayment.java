package com.pgeasy.www;

import lombok.Builder;

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
        return KaKaoPayApprovePayment.Result.class;
    }

    public record Result(
            String payment_method_type,
            String item_code,
            Amount amount,
            Long quantity,
            String created_at,
            String item_name,
            String tid,
            String sequential_payment_methods,
            String sid,
            String partner_order_id,
            String payload,
            String approved_at,
            String partner_user_id,
            String aid,
            CardInfo card_info,
            String cid) implements BaseResult {
        public record Amount(
                int total,
                int tax_free,
                int vat,
                int discount,
                int point,
                int green_deposit) {
        }

        public record CardInfo(
                String purchase_corp,
                String purchase_corp_code,
                String issuing_corp,
                String issuing_corp_code,
                String kakaopay_purchase_corp,
                String kakaopay_purchase_corp_code,
                String kakaopay_issuing_corp,
                String kakaopay_issuing_corp_code,
                String bin,
                String card_type,
                String install_month,
                String approved_id,
                String card_mid,
                String interest_free_install,
                String card_item_code) {
        }
    }
}
