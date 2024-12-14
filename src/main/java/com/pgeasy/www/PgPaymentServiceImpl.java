package com.pgeasy.www;

import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Slf4j
public class PgPaymentServiceImpl implements PgPaymentService {
    // 결제 모듈창
    public JSONObject paymentModule(JSONObject jsonObject, String secretKey, PaymentCompany paymentCompany) {
        return sendRequest(jsonObject, secretKey, getCreateModuleUrl(paymentCompany), paymentCompany);
    }

    private String getCreateModuleUrl(PaymentCompany paymentCompany) {
        if (PaymentCompany.KAKAO == paymentCompany) {
            return "https://open-api.kakaopay.com/online/v1/payment/ready";
        }
        else {
            return "";
        }
    }

    // 결제 승인
//    public JSONObject approvePayment(JSONObject jsonObject, String secretKey, PaymentCompany paymentCompany) {
//        return sendRequest(jsonObject, secretKey, getApprovePaymentUrl(paymentCompany), paymentCompany);
//    }
    public JSONObject approvePayment(ApprovePayment approvePayment) {
        log.info("Request Data: {}", approvePayment);
        return null;
    }

//    private String getApprovePaymentUrl(PaymentCompany paymentCompany) {
//        if (PaymentCompany.TOSS == paymentCompany) {
//            return "https://api.tosspayments.com/v1/payments/confirm";
//        }
//        else if (PaymentCompany.KAKAO == paymentCompany) {
//            return "https://open-api.kakaopay.com/online/v1/payment/approve";
//        }
//        else {
//            return "";
//        }
//    }

    private JSONObject sendRequest(JSONObject requestData, String secretKey, String urlString, PaymentCompany paymentCompany)  {
        HttpURLConnection connection = createConnection(secretKey, urlString, paymentCompany);
        try (OutputStream os = connection.getOutputStream()) {
            os.write(requestData.toString().getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            log.error("Error write response", e);
            JSONObject errorResponse = new JSONObject();
            errorResponse.put("error", "Error write response");
            return errorResponse;
        }

        try (InputStream responseStream = connection.getResponseCode() == 200 ? connection.getInputStream() : connection.getErrorStream();
             Reader reader = new InputStreamReader(responseStream, StandardCharsets.UTF_8)) {
            return (JSONObject) new JSONParser().parse(reader);
        } catch (Exception e) {
            log.error("Error reading response", e);
            JSONObject errorResponse = new JSONObject();
            errorResponse.put("error", "Error reading response");
            return errorResponse;
        }
    }

    private HttpURLConnection createConnection(String secretKey, String urlString, PaymentCompany paymentCompany) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Authorization", getAuthorization(secretKey, paymentCompany));
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            return connection;
        } catch (IOException e) {
            return null;
        }
    }

    private String getAuthorization(String secretKey, PaymentCompany paymentCompany) {
        if (PaymentCompany.TOSS == paymentCompany) {
            return "Basic " + Base64.getEncoder().encodeToString((secretKey + ":").getBytes(StandardCharsets.UTF_8));
        }
        else if (PaymentCompany.KAKAO == paymentCompany) {
            return "SECRET_KEY " + secretKey;
        }
        else {
            return "";
        }
    }

/*

    // 결제 결과 callback
    public void handlePaymentCallback() {
        // Implementation here
    }

    // 가맹점 승인
    public void approveMerchant() {
        // Implementation here
    }

    // 결제 환불
    public void refundPayment() {
        // Implementation here
    }

    // 결제 상태 확인
    public PaymentStatus checkPaymentStatus() {
        // Implementation here
        return null;
    }
*/
}
