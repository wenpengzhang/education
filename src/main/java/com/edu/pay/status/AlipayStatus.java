package com.edu.pay.status;

/**
 * @author hezhiyu on 15/5/9.
 */
public enum AlipayStatus {

    WAIT_BUYER_PAY(0),
    TRADE_CLOSED(-1),
    TRADE_SUCCESS(1),
    TRADE_FINISHED(-1),

    REFUND_SUCCESS(3),
    REFUND_CLOSED(-1);


    private int code;

    private AlipayStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}