package com.edu.pay.handler;

import com.edu.core.share.util.EnvUtil;
import com.edu.pay.client.AlipayInfo;
import com.edu.pay.client.PayInfo;
import com.edu.pay.config.AlipayConfig;
import com.edu.pay.util.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author guoyc on 15/5/8.
 */
public class AlipayHandler {

    private static final Logger LOG = LogManager.getLogger(AlipayHandler.class);
    public static final String CREATE_ORDER_SERVICE_NAME = "mobile.securitypay.pay";
    public static final String CREATE_ORDER_PAYMENT_TYPE = "1";
    public static final String CREATE_ORDER_PARAM_CHARSET = "utf-8";
    public static final String NON_PAYMENT_ORDER_TIMEOUT_VALUE = "30m";
    public static final String CREATE_ORDER_RETURN_URL = "m.alipay.com";
    public static final String CREATE_ORDER_NOTIFY_URL = EnvUtil.getAliPayBackHost();


    public static PayInfo getPayInfo(String orderId, String name, String detail, String price) throws UnsupportedEncodingException {
        // 订单
        String orderInfo = getOrderInfo(orderId, name, detail, price);

        // 对订单做RSA 签名
        String sign = sign(orderInfo);

        // 仅需对sign 做URL编码
        sign = URLEncoder.encode(sign, "UTF-8");

        // 完整的符合支付宝参数规范的订单信息
        return new AlipayInfo(orderInfo + "&sign=\"" + sign + "\"&" + getSignType());
    }
    /**
     * create the order info. 创建订单信息
     *
     */
    private static String getOrderInfo(String orderId, String name, String detail, String price) {
        // 签约合作者身份ID
        String orderInfo = "partner=" + "\"" + AlipayConfig.PARTNER + "\"";

        // 签约卖家支付宝账号
        orderInfo += "&seller_id=" + "\"" +  AlipayConfig.SELLER + "\"";

        // 商户网站唯一订单号
        orderInfo += "&out_trade_no=" + "\"" + orderId + "\"";

        // 商品名称
        orderInfo += "&subject=" + "\"" + name + "\"";

        // 商品详情
        orderInfo += "&body=" + "\"" + detail + "\"";

        // 商品金额
        orderInfo += "&total_fee=" + "\"" + price + "\"";

        // 服务器异步通知页面路径
        orderInfo += "&notify_url=" + "\"" + CREATE_ORDER_NOTIFY_URL
                + "\"";

        // 服务接口名称， 固定值
        orderInfo += "&service=\"" + CREATE_ORDER_SERVICE_NAME + "\"";

        // 支付类型， 固定值
        orderInfo += "&payment_type=\"" + CREATE_ORDER_PAYMENT_TYPE + "\"";

        // 参数编码， 固定值
        orderInfo += "&_input_charset=\"" + CREATE_ORDER_PARAM_CHARSET + "\"";

        // 设置未付款交易的超时时间
        // 默认30分钟，一旦超时，该笔交易就会自动被关闭。
        // 取值范围：1m～15d。
        // m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
        // 该参数数值不接受小数点，如1.5h，可转换为90m。
        orderInfo += "&it_b_pay=\"" + NON_PAYMENT_ORDER_TIMEOUT_VALUE + "\"";

        // extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
        // orderInfo += "&extern_token=" + "\"" + extern_token + "\"";

        // 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
        orderInfo += "&return_url=\"" + CREATE_ORDER_RETURN_URL + "\"";

        // 调用银行卡支付，需配置此参数，参与签名， 固定值 （需要签约《无线银行卡快捷支付》才能使用）
        // orderInfo += "&paymethod=\"expressGateway\"";

        return orderInfo;
    }

    private static String sign(String content) {
        return StringUtils.sign(content, AlipayConfig.MSZ_RSA_PRIVATE);
    }

    /**
     * get the sign type we use. 获取签名方式
     *
     */
    private static String getSignType() {
        return "sign_type=\"RSA\"";
    }
}
