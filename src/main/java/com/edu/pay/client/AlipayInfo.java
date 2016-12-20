package com.edu.pay.client;

import org.json.JSONObject;

/**
 * 
 * @author lee
 *
 */
public class AlipayInfo extends PayInfo{

    private String info;
    private int channel = PayChannel.ALIPAY.ordinal();

    public AlipayInfo() {}

    public AlipayInfo(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("info", info);
        jsonObject.put("channel", channel);
        return jsonObject;
    }
}
