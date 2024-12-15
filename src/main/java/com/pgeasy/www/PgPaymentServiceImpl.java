package com.pgeasy.www;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Slf4j
public class PgPaymentServiceImpl implements PgPaymentService {
    private final HttpClient httpClient = HttpClient.newHttpClient();

    // 결제 모듈창
    public CommonResponse<BaseResult> paymentModule(PaymentModule paymentModule) {
        BasePaymentModule basePaymentModule = paymentModule.basePaymentModule();
        return sendRequest(
                basePaymentModule,
                basePaymentModule.getAuthorization(paymentModule.secretKey())
        );
    }

    // 결제 승인
    public CommonResponse<BaseResult> approvePayment(ApprovePayment approvePayment) {
        BaseApprovePayment baseApprovePayment = approvePayment.baseApprovePayment();
        return sendRequest(
                baseApprovePayment,
                baseApprovePayment.getAuthorization(approvePayment.secretKey())
        );
    }

    private CommonResponse<BaseResult> sendRequest(BasePayment<?> baseApprovePayment, String authorization) {
        try {
            HttpRequest request = createHttpRequest(baseApprovePayment, authorization);
            log.info("HttpRequest : {}", request);

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            log.info("HttpResponse : {}", response);

            int statusCode = response.statusCode();
            JSONObject responseBody = processStringToJson(response.body());

            return CommonResponse.<BaseResult>builder()
                                 .code(response.statusCode())
                                 .message(getMessage(statusCode, responseBody))
                                 .data(getData(statusCode, responseBody, baseApprovePayment.getResultClass()))
                                 .build();
        } catch (Exception e) {
            log.error("sendRequest Exception : {}", e.getMessage(), e);
            throw new RuntimeException("Exception: ", e);
        }
    }

    public JSONObject processStringToJson(String body) {
        try {
            return (JSONObject) new JSONParser().parse(body);
        } catch (ParseException e) {
            log.error("processStringToJson Exception: {}", e.getMessage(), e);
            throw new RuntimeException("Exception: ", e);
        }
    }

    private static String getMessage(int statusCode, JSONObject responseBody) {
        return 200 == statusCode ? "OK" : (String) responseBody.get("message");
    }

    private BaseResult getData(int statusCode, JSONObject responseBody, Class<BaseResult> resultClass) {
        if (200 != statusCode) {
            return null;
        }

        return processMapToPaymentClass(responseBody, resultClass);
    }

    public BaseResult processMapToPaymentClass(JSONObject responseBody, Class<BaseResult> targetClass) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); // 인식 못하는 필드는 무시

        try {
            return objectMapper.readValue(responseBody.toJSONString(), targetClass);
        } catch (JsonProcessingException e) {
            log.error("mapToPaymentClass Exception: {}", e.getMessage(), e);
            throw new RuntimeException("Exception: ", e);
        }
    }

    private HttpRequest createHttpRequest(BasePayment<?> basePayment, String authorization) {
        return HttpRequest.newBuilder()
                          .uri(URI.create(basePayment.getApiUrl()))
                          .header("Authorization", authorization)
                          .header("Content-Type", "application/json")
                          .POST(HttpRequest.BodyPublishers.ofString(new Gson().toJson(basePayment)))
                          .build();
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
