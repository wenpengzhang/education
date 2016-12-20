package com.edu.pay.handler;

import com.edu.pay.client.PayChannel;
import com.edu.pay.client.PayInfo;
import com.edu.pay.util.MonetaryUnitUtils;

import java.io.UnsupportedEncodingException;


/**
 * @author lizhi 2016.11.22
 */
public class PayHandler {

    public static PayInfo getPayInfo(String orderId, String name, String detail, long fee, int mode, String ip, int weixinPayType, String openId) throws UnsupportedEncodingException {
        PayChannel payChannel = PayChannel.getPatChannel(mode);
        PayInfo payInfo;
        switch (payChannel) {
            case ALIPAY:
                String strFee = MonetaryUnitUtils.changeUnit(fee);
                payInfo = AlipayHandler.getPayInfo(orderId, name, detail, strFee);
                break;
            case WEIXIN:
                // 参数最后一位是IP地址暂时写死以供测试
                fee = fee / 10;
                WeixinpayHandler.WeixinpayType weixinpayType = WeixinpayHandler.WeixinpayType.getWeixinpayType(weixinPayType);
                payInfo = WeixinpayHandler.getPatInfo(orderId, name, fee + "", ip, weixinpayType, openId);
                break;
            default:
                payInfo = new PayInfo();
                break;
        }
        return payInfo;
    }


}
