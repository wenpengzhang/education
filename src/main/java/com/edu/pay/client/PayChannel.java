package com.edu.pay.client;

/**
 * 
 * @author lee
 *
 */
public enum PayChannel {
    ALIPAY,
    WEIXIN;

    public static PayChannel getPatChannel(int index) {
        if (index < 0 || index > PayChannel.values().length) {
            return null;
        }
        return PayChannel.values()[index];
    }
}