package com.edu.pay.handler;

import com.edu.pay.Common.HttpsRequest;
import com.edu.pay.client.PayInfo;
import com.edu.pay.client.WeixinPayInfo;
import com.edu.pay.config.WeixinConfigure;
import com.edu.pay.protocol.UnifiedOrderReqData;
import com.edu.pay.protocol.UnifiedOrderResData;
import com.edu.pay.util.RandomStringGenerator;
import com.edu.pay.util.Signature;
import com.edu.pay.util.Util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author guoyc on 15/5/11.
 */
public class WeixinpayHandler {

    public static final String GET_UNIFIED_ORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";

    enum WeixinpayType {
        APP,
        JSAPI;

        public static WeixinpayType getWeixinpayType(int origin) {
            if (origin < 0 || origin > WeixinpayType.values().length) {
                return null;
            }
            return WeixinpayType.values()[origin];
        }
    }

    public static PayInfo getPatInfo(String orderId, String detail, String price, String ip, WeixinpayType weixinpayType, String openId) {
        UnifiedOrderReqData unifiedOrderReqData = null;
        switch (weixinpayType) {
            case APP:
                unifiedOrderReqData = createUnifiedOrderReqData4App(orderId, detail, price, ip);
                break;
            case JSAPI:
                unifiedOrderReqData = createUnifiedOrderReqData4JSApi(orderId, detail, price, ip, openId);
                break;
        }
        if (unifiedOrderReqData == null) {
            return null;
        }
        return getUnifiedOrderResData(unifiedOrderReqData);
    }

    private static UnifiedOrderReqData createUnifiedOrderReqData4App(String orderId, String detail, String price, String ip) {
        String randomStr = RandomStringGenerator.getRandomStringByLength(20);
        int total_fee = Integer.parseInt(price);
        return UnifiedOrderReqData.getAppPayUnifiedOrderReqData(randomStr, detail, orderId, total_fee, ip);
    }

    private static UnifiedOrderReqData createUnifiedOrderReqData4JSApi(String orderId, String detail, String price, String ip, String openId) {
        String randomStr = RandomStringGenerator.getRandomStringByLength(20);
        int total_fee = Integer.parseInt(price);
        return UnifiedOrderReqData.getJsApiPayUnifiedOrderReqData(randomStr, detail, orderId, total_fee, ip, openId);
    }

    private static PayInfo getUnifiedOrderResData(UnifiedOrderReqData unifiedOrderReqData) {
        try {
            HttpsRequest httpsRequest = new HttpsRequest();
            String result = httpsRequest.sendPost(GET_UNIFIED_ORDER_URL, unifiedOrderReqData);
            System.out.print(result);
            UnifiedOrderResData unifiedOrderResData = (UnifiedOrderResData) Util.getObjectFromXML(result, UnifiedOrderResData.class);
            if (unifiedOrderResData.getResult_code().equals("SUCCESS") && unifiedOrderResData.getReturn_code().equals("SUCCESS")) {
                // 换算成秒
                long currentTimeMillis = System.currentTimeMillis() / 1000;
                String partnerId = unifiedOrderResData.getPrepay_id();
                String nonceStr = RandomStringGenerator.getRandomStringByLength(32);
                return new WeixinPayInfo(partnerId, createSign(partnerId, nonceStr, currentTimeMillis, unifiedOrderReqData.getAppid(), unifiedOrderReqData.getMch_id()), nonceStr, currentTimeMillis);
            }

        } catch (Exception e) {

        }
        return null;

    }

    private static String createSign(String prepayId, String nonceStr, long timeStamp, String appId, String mchId) {
        Map<String, Object> paramMap = new HashMap<>();
        if (appId.equals(WeixinConfigure.APP_ID_4_GZH)) {
            paramMap.put("appId", appId);
            paramMap.put("signType", "MD5");
            paramMap.put("nonceStr", nonceStr);
            paramMap.put("timeStamp", timeStamp);
            paramMap.put("package", String.format("prepay_id=%s", prepayId));
        } else {
            paramMap.put("appid", appId);
            paramMap.put("partnerid", mchId);
            paramMap.put("prepayid", prepayId);
            paramMap.put("noncestr", nonceStr);
            paramMap.put("timestamp", timeStamp);
            paramMap.put("package", "Sign=WXPay");
        }
        return Signature.getSign(paramMap);
    }

}
