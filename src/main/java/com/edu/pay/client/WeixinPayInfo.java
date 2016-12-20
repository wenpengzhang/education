package com.edu.pay.client;

import com.edu.core.share.util.StringUtil;
import org.json.JSONObject;

/**
 * 
 * @author lee
 *
 */
public class WeixinPayInfo extends PayInfo{

    private String prepayId;
    private String sign;
    private String nonceStr;
    private long   time;
    private int channel = PayChannel.WEIXIN.ordinal();
    private String result = null;

    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public WeixinPayInfo() {

    }

    public WeixinPayInfo(String prepayId, String sign, String nonceStr, long time) {
        this.prepayId = prepayId;
        this.sign = sign;
        this.nonceStr = nonceStr;
        this.time = time;
    }

    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        if (StringUtil.isEmpty(result)) {
            jsonObject.put("prepay_id", prepayId);
            jsonObject.put("sign", sign);
            jsonObject.put("nonce_str", nonceStr);
            jsonObject.put("time", time);
            jsonObject.put("channel", channel);
        } else {
            jsonObject = new JSONObject(result);
        }
        return jsonObject;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }


}
