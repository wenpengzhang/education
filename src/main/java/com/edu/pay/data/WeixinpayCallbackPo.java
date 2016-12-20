package com.edu.pay.data;

import java.util.Map;

/**
 * @author guoyc on 15-11-18.
 */
public class WeixinpayCallbackPo {

    private int id;
    private String return_code;
    private String return_msg;
    private String appid;
    private String mch_id;
    private String device_info;
    private String nonce_str;
    private String sign;
    private String result_code;
    private String err_code;
    private String err_code_des;
    private String openid;
    private String is_subscribe;
    private String trade_type;
    private String bank_type;
    private Long total_fee;
    private String fee_type;
    private Long cash_fee;
    private String cash_fee_type;
    private Long coupon_fee;
    private String coupon_count;
    private String coupon_id_0;
    private String coupon_fee_0;
    private String transaction_id;
    private String out_trade_no;
    private String attach;
    private String time_end;

    public WeixinpayCallbackPo(){}

    public WeixinpayCallbackPo(Map<String, Object> resultMap) {
        this.return_code = (String) resultMap.get("return_code");
        this.result_code = (String) resultMap.get("result_code");
        this.return_msg = (String) resultMap.get("return_msg");
        this.appid = (String) resultMap.get("appid");
        this.mch_id = (String) resultMap.get("mch_id");
        this.device_info = (String) resultMap.get("device_info");
        this.nonce_str = (String) resultMap.get("nonce_str");
        this.sign = (String) resultMap.get("sign");
        this.err_code = (String) resultMap.get("err_code");
        this.err_code_des = (String) resultMap.get("err_code_des");
        this.openid = (String) resultMap.get("openid");
        this.is_subscribe = (String) resultMap.get("is_subscribe");
        this.trade_type = (String) resultMap.get("trade_type");
        this.bank_type = (String) resultMap.get("bank_type");
        if (resultMap.containsKey("total_fee")) {
            this.total_fee = Long.parseLong((String)resultMap.get("total_fee"));
        } else {
            this.total_fee = 0L;
        }
        this.fee_type = (String) resultMap.get("fee_type");
        if (resultMap.containsKey("cash_fee")) {
            this.cash_fee = Long.parseLong((String)resultMap.get("cash_fee"));
        } else {
            this.cash_fee = 0L;
        }
        this.cash_fee_type = (String) resultMap.get("cash_fee_type");
        if (resultMap.containsKey("coupon_fee")) {
            this.coupon_fee = Long.parseLong((String)resultMap.get("coupon_fee"));
        } else {
            this.coupon_fee = 0L;
        }
        this.coupon_count = (String) resultMap.get("coupon_count");
        this.coupon_id_0 = (String) resultMap.get("coupon_id_0");
        this.coupon_fee_0 = (String) resultMap.get("coupon_fee_0");
        this.transaction_id = (String) resultMap.get("transaction_id");
        this.out_trade_no = (String) resultMap.get("out_trade_no");
        this.attach = (String) resultMap.get("attach");
        this.time_end = (String) resultMap.get("time_end");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReturn_code() {
        return return_code;
    }

    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }

    public String getReturn_msg() {
        return return_msg;
    }

    public void setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getDevice_info() {
        return device_info;
    }

    public void setDevice_info(String device_info) {
        this.device_info = device_info;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getResult_code() {
        return result_code;
    }

    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }

    public String getErr_code() {
        return err_code;
    }

    public void setErr_code(String err_code) {
        this.err_code = err_code;
    }

    public String getErr_code_des() {
        return err_code_des;
    }

    public void setErr_code_des(String err_code_des) {
        this.err_code_des = err_code_des;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getIs_subscribe() {
        return is_subscribe;
    }

    public void setIs_subscribe(String is_subscribe) {
        this.is_subscribe = is_subscribe;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getBank_type() {
        return bank_type;
    }

    public void setBank_type(String bank_type) {
        this.bank_type = bank_type;
    }

    public Long getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(Long total_fee) {
        this.total_fee = total_fee;
    }

    public String getFee_type() {
        return fee_type;
    }

    public void setFee_type(String fee_type) {
        this.fee_type = fee_type;
    }

    public Long getCash_fee() {
        return cash_fee;
    }

    public void setCash_fee(Long cash_fee) {
        this.cash_fee = cash_fee;
    }

    public String getCash_fee_type() {
        return cash_fee_type;
    }

    public void setCash_fee_type(String cash_fee_type) {
        this.cash_fee_type = cash_fee_type;
    }

    public Long getCoupon_fee() {
        return coupon_fee;
    }

    public void setCoupon_fee(Long coupon_fee) {
        this.coupon_fee = coupon_fee;
    }

    public String getCoupon_count() {
        return coupon_count;
    }

    public void setCoupon_count(String coupon_count) {
        this.coupon_count = coupon_count;
    }

    public String getCoupon_id_0() {
        return coupon_id_0;
    }

    public void setCoupon_id_0(String coupon_id_0) {
        this.coupon_id_0 = coupon_id_0;
    }

    public String getCoupon_fee_0() {
        return coupon_fee_0;
    }

    public void setCoupon_fee_0(String coupon_fee_0) {
        this.coupon_fee_0 = coupon_fee_0;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getTime_end() {
        return time_end;
    }

    public void setTime_end(String time_end) {
        this.time_end = time_end;
    }


}
