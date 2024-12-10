package com.pgeasy.www;

public class AppConfig {
    public PgPaymentService pgPaymentService() {
        return new PgPaymentServiceImpl();
    }
}
