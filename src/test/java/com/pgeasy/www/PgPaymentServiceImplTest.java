package com.pgeasy.www;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

class PgPaymentServiceImplTest {
    private PgPaymentServiceImpl pgPaymentService = new PgPaymentServiceImpl();

    Properties properties = new Properties();

    public PgPaymentServiceImplTest() {
        try (FileInputStream fis = new FileInputStream("src/main/resources/application.properties")) {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void createPayment_toss() {
        String secretKey = properties.getProperty("toss-widget-secret-key");
        ApprovePayment approvePayment =
                ApprovePayment.builder()
                              .secretKey(secretKey)
                              .baseApprovePayment(
                                      TossPayApprovePayment.builder()
                                                           .paymentKey("tgen_20241215202745gMAW2")
                                                           .orderId("MC40MzAwMjk0MDAzOTIx")
                                                           .amount(50000)
                                                           .build())
                              .build();
        CommonResponse<?> commonResponse = pgPaymentService.approvePayment(approvePayment);
        System.out.println(commonResponse);
    }

    @Test
    void mapToPaymentClass() throws ParseException {
        String jsonString = """
                {
                  "country" : "KR",
                  "metadata" : null,
                  "orderId" : "MC45Njk5NjkwOTY2Mzk3",
                  "cashReceipts" : null,
                  "isPartialCancelable" : true,
                  "lastTransactionKey" : "txrd_a01jf4hhvzrdb0rteae9saepb42",
                  "discount" : null,
                  "taxExemptionAmount" : 0,
                  "suppliedAmount" : 45455,
                  "secret" : "ps_kYG57Eba3GZYYKndlnwL8pWDOxmA",
                  "type" : "NORMAL",
                  "cultureExpense" : false,
                  "taxFreeAmount" : 0,
                  "requestedAt" : "2024-12-15T15:54:02+09:00",
                  "currency" : "KRW",
                  "paymentKey" : "tgen_20241215155402jviQ2",
                  "virtualAccount" : null,
                  "checkout" : {
                    "url" : "https://api.tosspayments.com/v1/payments/tgen_20241215155402jviQ2/checkout"
                  },
                  "orderName" : "토스 티셔츠 외 2건",
                  "method" : "간편결제",
                  "useEscrow" : false,
                  "vat" : 4545,
                  "mId" : "tgen_docs",
                  "approvedAt" : "2024-12-15T15:54:35+09:00",
                  "balanceAmount" : 50000,
                  "version" : "2022-11-16",
                  "easyPay" : {
                    "amount" : 50000,
                    "provider" : "토스페이",
                    "discountAmount" : 0
                  },
                  "totalAmount" : 50000,
                  "cancels" : null,
                  "transfer" : null,
                  "mobilePhone" : null,
                  "failure" : null,
                  "receipt" : {
                    "url" : "https://dashboard.tosspayments.com/receipt/redirection?transactionId=tgen_20241215155402jviQ2&ref=PX"
                  },
                  "giftCertificate" : null,
                  "cashReceipt" : null,
                  "card" : null,
                  "status" : "DONE"
                }
                """;
        BasePayment basePayment = TossPayApprovePayment.builder().build();
        JSONObject jsonObject = pgPaymentService.processStringToJson(jsonString);
        pgPaymentService.processMapToPaymentClass(jsonObject, basePayment.getResultClass());
    }
}
