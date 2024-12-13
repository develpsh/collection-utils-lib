package com.pgeasy.www;

import org.json.simple.JSONObject;

public interface PgPaymentService {
//    JSONObject createPayment(PgEasyCreatePaymentRequest pgEasyCreatePaymentRequest, String secretKey) throws Exception;
//    JSONObject createPayment(Object object, PaymentCompany paymentCompany, String secretKey) throws Exception;
    JSONObject createPayment(JSONObject jsonObject, String secretKey, PaymentCompany paymentCompany);
}
