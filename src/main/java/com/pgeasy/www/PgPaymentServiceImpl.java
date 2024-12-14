package com.pgeasy.www;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Slf4j
public class PgPaymentServiceImpl implements PgPaymentService {
    private final HttpClient httpClient = HttpClient.newHttpClient();

    // 결제 모듈창
    public JSONObject paymentModule(JSONObject jsonObject, String secretKey, PaymentCompany paymentCompany) {
//    public JSONObject paymentModule(ApprovePayment approvePayment) {
//        return sendRequest(jsonObject, secretKey, getCreateModuleUrl(paymentCompany), paymentCompany);
//        return sendRequest(approvePayment.baseApprovePayment(), approvePayment.secretKey(), getApprovePaymentUrl(approvePayment.paymentCompany()), approvePayment.paymentCompany());
        return null;
    }

    private String getCreateModuleUrl(PaymentCompany paymentCompany) {
        if (PaymentCompany.KAKAO == paymentCompany) {
            return "https://open-api.kakaopay.com/online/v1/payment/ready";
        } else {
            return "";
        }
    }

    // 결제 승인
    public JSONObject approvePayment(ApprovePayment approvePayment) {
        return sendRequest(approvePayment.baseApprovePayment(), approvePayment.secretKey(), getApprovePaymentUrl(approvePayment.paymentCompany()), approvePayment.paymentCompany());
    }

    private String getApprovePaymentUrl(PaymentCompany paymentCompany) {
        if (PaymentCompany.TOSS == paymentCompany) {
            return "https://api.tosspayments.com/v1/payments/confirm";
        } else if (PaymentCompany.KAKAO == paymentCompany) {
            return "https://open-api.kakaopay.com/online/v1/payment/approve";
        } else {
            return "";
        }
    }

    private JSONObject sendRequest(BaseApprovePayment baseApprovePayment, String secretKey, String urlString, PaymentCompany paymentCompany) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(urlString))
                    .header("Authorization", getAuthorization(secretKey, paymentCompany))
                    .header("Content-Type", "application/json")
//                    .POST(HttpRequest.BodyPublishers.ofString(baseApprovePayment.toJSONString()))
                    .POST(HttpRequest.BodyPublishers.ofString(new Gson().toJson(baseApprovePayment)))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                return (JSONObject) new JSONParser().parse(response.body());
            }
            else {
                log.error("Error response: {}", response.body());
                throw new RuntimeException("Error sending request: " + response.body());
//                JSONObject errorResponse = new JSONObject();
//                errorResponse.put("error", "Error response");
//                errorResponse.put("statusCode", response.statusCode());
//                errorResponse.put("body", response.body());
//                return errorResponse;
            }
        } catch (Exception e) {
            log.error("Error sending request", e);
            throw new RuntimeException("Error sending request", e);
//            JSONObject errorResponse = new JSONObject();
//            errorResponse.put("error", "Error sending request");
//            return errorResponse;
        }
    }

    private String getAuthorization(String secretKey, PaymentCompany paymentCompany) {
        if (PaymentCompany.TOSS == paymentCompany) {
            return "Basic " + Base64.getEncoder().encodeToString((secretKey + ":").getBytes(StandardCharsets.UTF_8));
        } else if (PaymentCompany.KAKAO == paymentCompany) {
            return "SECRET_KEY " + secretKey;
        } else {
            return "";
        }
    }

//    // 결제 모듈창
//    public JSONObject paymentModule(JSONObject jsonObject, String secretKey, PaymentCompany paymentCompany) {
//        return sendRequest(jsonObject, secretKey, getCreateModuleUrl(paymentCompany), paymentCompany);
//    }
//
//    private String getCreateModuleUrl(PaymentCompany paymentCompany) {
//        if (PaymentCompany.KAKAO == paymentCompany) {
//            return "https://open-api.kakaopay.com/online/v1/payment/ready";
//        }
//        else {
//            return "";
//        }
//    }
//
//    // 결제 승인
////    public JSONObject approvePayment(JSONObject jsonObject, String secretKey, PaymentCompany paymentCompany) {
////        return sendRequest(jsonObject, secretKey, getApprovePaymentUrl(paymentCompany), paymentCompany);
////    }
//    public JSONObject approvePayment(ApprovePayment approvePayment) {
////        return sendRequest(jsonObject, secretKey, getApprovePaymentUrl(paymentCompany), paymentCompany);
//        return sendRequest(approvePayment, approvePayment.secretKey(), getApprovePaymentUrl(paymentCompany), paymentCompany);
//    }
//
////    private String getApprovePaymentUrl(PaymentCompany paymentCompany) {
////        if (PaymentCompany.TOSS == paymentCompany) {
////            return "https://api.tosspayments.com/v1/payments/confirm";
////        }
////        else if (PaymentCompany.KAKAO == paymentCompany) {
////            return "https://open-api.kakaopay.com/online/v1/payment/approve";
////        }
////        else {
////            return "";
////        }
////    }
//
////    private JSONObject sendRequest(JSONObject requestData, String secretKey, String urlString, PaymentCompany paymentCompany)  {
//    private JSONObject sendRequest(JSONObject requestData, String secretKey, String urlString, PaymentCompany paymentCompany)  {
//        HttpURLConnection connection = createConnection(secretKey, urlString, paymentCompany);
//        try (OutputStream os = connection.getOutputStream()) {
//            os.write(requestData.toString().getBytes(StandardCharsets.UTF_8));
//        } catch (IOException e) {
//            log.error("Error write response", e);
//            JSONObject errorResponse = new JSONObject();
//            errorResponse.put("error", "Error write response");
//            return errorResponse;
//        }
//
//        try (InputStream responseStream = connection.getResponseCode() == 200 ? connection.getInputStream() : connection.getErrorStream();
//             Reader reader = new InputStreamReader(responseStream, StandardCharsets.UTF_8)) {
//            return (JSONObject) new JSONParser().parse(reader);
//        } catch (Exception e) {
//            log.error("Error reading response", e);
//            JSONObject errorResponse = new JSONObject();
//            errorResponse.put("error", "Error reading response");
//            return errorResponse;
//        }
//    }
//
//    private HttpURLConnection createConnection(String secretKey, String urlString, PaymentCompany paymentCompany) {
//        try {
//            URL url = new URL(urlString);
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestProperty("Authorization", getAuthorization(secretKey, paymentCompany));
//            connection.setRequestProperty("Content-Type", "application/json");
//            connection.setRequestMethod("POST");
//            connection.setDoOutput(true);
//            return connection;
//        } catch (IOException e) {
//            return null;
//        }
//    }
//
//    private String getAuthorization(String secretKey, PaymentCompany paymentCompany) {
//        if (PaymentCompany.TOSS == paymentCompany) {
//            return "Basic " + Base64.getEncoder().encodeToString((secretKey + ":").getBytes(StandardCharsets.UTF_8));
//        }
//        else if (PaymentCompany.KAKAO == paymentCompany) {
//            return "SECRET_KEY " + secretKey;
//        }
//        else {
//            return "";
//        }
//    }
//
///*
//
//    // 결제 결과 callback
//    public void handlePaymentCallback() {
//        // Implementation here
//    }
//
//    // 가맹점 승인
//    public void approveMerchant() {
//        // Implementation here
//    }
//
//    // 결제 환불
//    public void refundPayment() {
//        // Implementation here
//    }
//
//    // 결제 상태 확인
//    public PaymentStatus checkPaymentStatus() {
//        // Implementation here
//        return null;
//    }
//*/
}
