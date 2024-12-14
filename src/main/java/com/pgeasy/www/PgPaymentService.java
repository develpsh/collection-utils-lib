package com.pgeasy.www;

import org.json.simple.JSONObject;

public interface PgPaymentService {
    JSONObject createModule(JSONObject jsonObject, String secretKey, PaymentCompany paymentCompany);
    JSONObject approvePayment(JSONObject jsonObject, String secretKey, PaymentCompany paymentCompany);
}
