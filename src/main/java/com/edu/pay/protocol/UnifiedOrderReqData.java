package com.edu.pay.protocol;

import com.edu.core.share.util.EnvUtil;
import com.edu.pay.config.WeixinConfigure;
import com.edu.pay.util.Signature;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author guoyc on 15/5/11.
 */
public class UnifiedOrderReqData {

    //每个字段具体的意思请查看API文档
    private String appid;
    private String mch_id;
    private String device_info;
    private String nonce_str;
    private String sign;
    private String detail;
    private String body;
    private String attach;
    private String out_trade_no;
    private String fee_type;
    private int    total_fee;
    private String spbill_create_ip;
    private String time_start;
    private String time_expire;
    private String goods_tag;
    private String notify_url;
    private String trade_type;
    private String product_id;
    private String openid;

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
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

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getFee_type() {
        return fee_type;
    }

    public void setFee_type(String fee_type) {
        this.fee_type = fee_type;
    }

    public int getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(int total_fee) {
        this.total_fee = total_fee;
    }

    public String getSpbill_create_ip() {
        return spbill_create_ip;
    }

    public void setSpbill_create_ip(String spbill_create_ip) {
        this.spbill_create_ip = spbill_create_ip;
    }

    public String getTime_start() {
        return time_start;
    }

    public void setTime_start(String time_start) {
        this.time_start = time_start;
    }

    public String getTime_expire() {
        return time_expire;
    }

    public void setTime_expire(String time_expire) {
        this.time_expire = time_expire;
    }

    public String getGoods_tag() {
        return goods_tag;
    }

    public void setGoods_tag(String goods_tag) {
        this.goods_tag = goods_tag;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public UnifiedOrderReqData() {

    }

    public UnifiedOrderReqData(String appId, String mchId, String nonce_str, String body, String out_trade_no, int total_fee, String spbill_create_ip, String trade_type) {
        this.appid = appId;
        this.mch_id = mchId;
        this.nonce_str = nonce_str;
        this.body = body;
        this.out_trade_no = out_trade_no;
        this.total_fee = total_fee;
        this.spbill_create_ip = spbill_create_ip;
        this.notify_url = EnvUtil.getWeiXinBackHost();
        this.trade_type = trade_type;
        //根据API给的签名规则进行签名
        String sign = Signature.getSign(toMap());
        setSign(sign);//把签名数据设置到Sign这个属性中;
    }

    public UnifiedOrderReqData(String appId, String mchId, String nonce_str, String body, String out_trade_no, int total_fee, String spbill_create_ip, String trade_type, String openId) {
        this.appid = appId;
        this.mch_id = mchId;
        this.nonce_str = nonce_str;
        this.body = body;
        this.out_trade_no = out_trade_no;
        this.total_fee = total_fee;
        this.spbill_create_ip = spbill_create_ip;
        this.notify_url = EnvUtil.getWeiXinBackHost();
        this.trade_type = trade_type;
        this.openid = openId;
        //根据API给的签名规则进行签名
        String sign = Signature.getSign(toMap());
        setSign(sign);//把签名数据设置到Sign这个属性中;
    }

    public static UnifiedOrderReqData getAppPayUnifiedOrderReqData(String nonce_str, String body, String out_trade_no, int total_fee, String spbill_create_ip) {
        return new UnifiedOrderReqData (WeixinConfigure.APP_ID, WeixinConfigure.MCH_ID, nonce_str, body, out_trade_no, total_fee, spbill_create_ip, "APP");
    }

    public static UnifiedOrderReqData getJsApiPayUnifiedOrderReqData(String nonce_str, String body, String out_trade_no, int total_fee, String spbill_create_ip, String openId) {
        return new UnifiedOrderReqData (WeixinConfigure.APP_ID_4_GZH, WeixinConfigure.MCH_ID_4_GZH, nonce_str, body, out_trade_no, total_fee, spbill_create_ip, "JSAPI", openId);
    }

    public Map<String,Object> toMap(){
        Map<String,Object> map = new HashMap<String, Object>();
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            Object obj;
            try {
                obj = field.get(this);
                if(obj!=null){
                    map.put(field.getName(), obj);
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }


}
