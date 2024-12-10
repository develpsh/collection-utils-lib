package com.pgeasy.www;

import com.google.gson.Gson;
import com.pgeasy.www.dto.request.PgEasyCreatePaymentRequest;
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
    public JSONObject createPayment(PgEasyCreatePaymentRequest pgEasyCreatePaymentRequest, String secretKey) throws Exception {
        String url = "https://api.tosspayments.com/v1/payments/confirm";
        String jsonPayload = new Gson().toJson(pgEasyCreatePaymentRequest);
        return sendRequest(parseRequestData(jsonPayload), secretKey, url);
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

    private JSONObject sendRequest(JSONObject requestData, String secretKey, String urlString) throws IOException {
        HttpURLConnection connection = createConnection(secretKey, urlString);
        try (OutputStream os = connection.getOutputStream()) {
            os.write(requestData.toString().getBytes(StandardCharsets.UTF_8));
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

    private HttpURLConnection createConnection(String secretKey, String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("Authorization", "Basic " + Base64.getEncoder().encodeToString((secretKey + ":").getBytes(StandardCharsets.UTF_8)));
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        return connection;
    }
}
