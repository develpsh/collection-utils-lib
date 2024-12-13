package com.pgeasy.www;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class PgPaymentServiceImpl implements PgPaymentService {
    // 결제 생성
//    public JSONObject createPayment(PgEasyCreatePaymentRequest pgEasyCreatePaymentRequest, String secretKey) throws Exception {
//    public JSONObject createPayment(Object object, PaymentCompany paymentCompany, Str
    public JSONObject createPayment(JSONObject jsonObject, String secretKey, PaymentCompany paymentCompany) {
//        validateCreatePaymentVO(paymentCompany, object);
        String url = getCreatePaymentUrl(paymentCompany);;
//        String jsonPayload = new Gson().toJson(jsonObject);
//        return sendRequest(parseRequestData(jsonPayload), secretKey, url);
        return sendRequest(jsonObject, secretKey, url, paymentCompany);
    }

    private String getCreatePaymentUrl(PaymentCompany paymentCompany) {
        if (PaymentCompany.TOSS == paymentCompany) {
            return "https://api.tosspayments.com/v1/payments/confirm";
        }
        else if (PaymentCompany.KAKAO == paymentCompany) {
            return "https://open-api.kakaopay.com/online/v1/payment/approve";
        }
        else {
            return "";
        }
    }

    private void validateCreatePaymentVO(PaymentCompany paymentCompany, Object object) {
        if (PaymentCompany.TOSS == paymentCompany) {
            TossCreatePayment tossCreatePayment = (TossCreatePayment) object;
            System.out.println(tossCreatePayment);
        }
        else if (PaymentCompany.KAKAO == paymentCompany) {
            KaKaoCreatePayment kaKaoCreatePayment = (KaKaoCreatePayment) object;
            System.out.println(kaKaoCreatePayment);
        }
    }

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

    private JSONObject parseRequestData(String jsonBody) {
        try {
            return (JSONObject) new JSONParser().parse(jsonBody);
        } catch (ParseException e) {
//            log.error("JSON Parsing Error", e);
            return new JSONObject();
        }
    }

    private JSONObject sendRequest(JSONObject requestData, String secretKey, String urlString, PaymentCompany paymentCompany)  {
        HttpURLConnection connection = createConnection(secretKey, urlString, paymentCompany);
        try (OutputStream os = connection.getOutputStream()) {
            os.write(requestData.toString().getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            JSONObject errorResponse = new JSONObject();
            errorResponse.put("error", "Error write response");
            return errorResponse;
        }

        try (InputStream responseStream = connection.getResponseCode() == 200 ? connection.getInputStream() : connection.getErrorStream();
             Reader reader = new InputStreamReader(responseStream, StandardCharsets.UTF_8)) {
            return (JSONObject) new JSONParser().parse(reader);
        } catch (Exception e) {
//            log.error("Error reading response", e);
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
}
