package com.pgeasy.www;

public interface BasePayment {
    String getApiUrl();
    String getAuthorization(String secretKey);
    Class<? extends TossPayApprovePayment.Result> getResultClass();
}
