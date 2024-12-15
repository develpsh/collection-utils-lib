package com.pgeasy.www;

import org.json.simple.JSONObject;

public interface PgPaymentService {
//    JSONObject paymentModule(PaymentModule paymentModule);
    CommonResponse<?> paymentModule(PaymentModule paymentModule);
//    JSONObject approvePayment(ApprovePayment approvePayment);
    CommonResponse<?> approvePayment(ApprovePayment approvePayment);
}
