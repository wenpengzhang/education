package com.edu.pay.status;

import com.edu.pay.client.PayChannel;

/**
 * @author lee 
 *
 * 对于一份Order来说，他的支付状态应该仅存在如下`2类、6种`
 *     a. 待支付（创建订单时生成，由用户的UGC产生）
 *         a1. 支付成功
 *         a2. 支付失败
 *     b. 申请退款（由用户的UGC产生）
 *         b1. 退款成功
 *         b2. 退款失败
 *
 */
public class PayStatus {

    public static int checkPayStatus(int channel, String status) {
        int code = -1;
        switch (PayChannel.values()[channel]) {
            case ALIPAY:
                wrapperAlipayStatus(status);
                break;
            case WEIXIN:
                break;
        }
        return code;
    }

    private static int wrapperAlipayStatus(String status) {
        return AlipayStatus.valueOf(status).getCode();
    }
}
