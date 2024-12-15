package com.pgeasy.www;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Builder
public class TossPayApprovePayment implements BaseApprovePayment {
    private final String apiUrl = "https://api.tosspayments.com/v1/payments/confirm";
    private String paymentKey;
    private String orderId;
    private Integer amount;
    private Result result;

    @Override
    public String getApiUrl() {
        return apiUrl;
    }

    @Override
    public String getAuthorization(String secretKey) {
        return "Basic " + Base64.getEncoder().encodeToString((secretKey + ":").getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public Class<Result> getResultClass() {
        return TossPayApprovePayment.Result.class;
    }

    @Getter
    public static class Result implements BaseResult {
        @JsonProperty("mId")
        private String mId;
        private String lastTransactionKey;
        private String paymentKey;
        private String orderId;
        private String orderName;
        private Integer taxExemptionAmount;
        private String status;
        private String requestedAt;
        private String approvedAt;
        private Boolean useEscrow;
        private Boolean cultureExpense;
        private String card;
        private String virtualAccount;
        private String transfer;
        private String mobilePhone;
        private String giftCertificate;
        private String cashReceipt;
        private String cashReceipts;
        private String discount;
        private String cancels;
        private String secret;
        private String type;
        private EasyPay easyPay;
        private String country;
        private String failure;
        private Boolean isPartialCancelable;
        private Receipt receipt;
        private Checkout checkout;
        private String currency;
        private Integer totalAmount;
        private Integer balanceAmount;
        private Integer suppliedAmount;
        private Integer vat;
        private Integer taxFreeAmount;
        private String method;
        private String version;
        private String metadata;

        @Getter
        public static class EasyPay {
            private String provider;
            private Integer amount;
            private Integer discountAmount;
        }

        @Getter
        public static class Receipt {
            private String url;
        }

        @Getter
        public static class Checkout {
            private String url;
        }

        @Override
        public String toString() {
            return "Result{" +
                    "mId='" + mId + '\'' +
                    ", lastTransactionKey='" + lastTransactionKey + '\'' +
                    ", paymentKey='" + paymentKey + '\'' +
                    ", orderId='" + orderId + '\'' +
                    ", orderName='" + orderName + '\'' +
                    ", taxExemptionAmount=" + taxExemptionAmount +
                    ", status='" + status + '\'' +
                    ", requestedAt='" + requestedAt + '\'' +
                    ", approvedAt='" + approvedAt + '\'' +
                    ", useEscrow=" + useEscrow +
                    ", cultureExpense=" + cultureExpense +
                    ", card='" + card + '\'' +
                    ", virtualAccount='" + virtualAccount + '\'' +
                    ", transfer='" + transfer + '\'' +
                    ", mobilePhone='" + mobilePhone + '\'' +
                    ", giftCertificate='" + giftCertificate + '\'' +
                    ", cashReceipt='" + cashReceipt + '\'' +
                    ", cashReceipts='" + cashReceipts + '\'' +
                    ", discount='" + discount + '\'' +
                    ", cancels='" + cancels + '\'' +
                    ", secret='" + secret + '\'' +
                    ", type='" + type + '\'' +
                    ", easyPay=" + easyPay +
                    ", country='" + country + '\'' +
                    ", failure='" + failure + '\'' +
                    ", isPartialCancelable=" + isPartialCancelable +
                    ", receipt=" + receipt +
                    ", checkout=" + checkout +
                    ", currency='" + currency + '\'' +
                    ", totalAmount=" + totalAmount +
                    ", balanceAmount=" + balanceAmount +
                    ", suppliedAmount=" + suppliedAmount +
                    ", vat=" + vat +
                    ", taxFreeAmount=" + taxFreeAmount +
                    ", method='" + method + '\'' +
                    ", version='" + version + '\'' +
                    ", metadata='" + metadata + '\'' +
                    '}';
        }
    }
}
