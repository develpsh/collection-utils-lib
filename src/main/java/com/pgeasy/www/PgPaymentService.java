package com.pgeasy.www;

public interface PgPaymentService {
    CommonResponse<BaseResult> paymentModule(PaymentModule paymentModule);
    CommonResponse<BaseResult> approvePayment(ApprovePayment approvePayment);
}
