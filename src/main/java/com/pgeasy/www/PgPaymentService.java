package com.pgeasy.www;

import com.pgeasy.www.dto.request.PgEasyCreatePaymentRequest;
import org.json.simple.JSONObject;

public interface PgPaymentService {
    JSONObject createPayment(PgEasyCreatePaymentRequest pgEasyCreatePaymentRequest, String secretKey) throws Exception;
}
