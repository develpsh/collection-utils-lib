package com.pgeasy.www;

import com.pgeasy.www.dto.request.PgEasyCreatePaymentRequest;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

class PgPaymentServiceImplTest {
    Properties properties = new Properties();

    public PgPaymentServiceImplTest() {
        try (FileInputStream fis = new FileInputStream("src/main/resources/application.properties")) {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void createPayment() throws Exception {
        String secretKey = properties.getProperty("secretKey");
        PgEasyCreatePaymentRequest createPaymentRequest =
                PgEasyCreatePaymentRequest.builder()
                                    .orderId("MC45NzMyMDcwNzM1MzAw")
                                    .amount(50000)
                                    .paymentKey("tgen_20241211060602XOWg2")
                                    .build();
        AppConfig appConfig = new AppConfig();
        JSONObject payment = appConfig.pgPaymentService().createPayment(createPaymentRequest, secretKey);
        System.out.println(payment);
    }
}
