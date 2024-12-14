package com.pgeasy.www;

import lombok.Builder;

@Builder
public class KaKaoPayApprovePayment implements BaseApprovePayment {
    private String cid;
    private String tid;
    private String partner_order_id;
    private String partner_user_id;
    private String pg_token;
}
