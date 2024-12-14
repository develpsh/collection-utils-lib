package com.pgeasy.www;

import org.json.simple.JSONObject;

public interface PgPaymentService {
    JSONObject paymentModule(JSONObject jsonObject, String secretKey, PaymentCompany paymentCompany);
    JSONObject approvePayment(ApprovePayment approvePayment);
}
