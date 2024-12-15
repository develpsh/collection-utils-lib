package com.pgeasy.www;

import lombok.Builder;

@Builder
public record ApprovePayment(String secretKey, BaseApprovePayment baseApprovePayment) {

}
