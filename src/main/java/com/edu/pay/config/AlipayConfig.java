package com.edu.pay.config;

/**
 * @author hezhiyu on 15/5/6.
 */
public class AlipayConfig {

    //↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 合作身份者ID，以2088开头由16位纯数字组成的字符串
    public final static String PARTNER = "2088022388258691";

    public final static String SELLER = "mengshizi@yeah.net";

    // 支付宝的公钥
    public final static String ALI_RSA_PUBLIC =
           /* "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRA" +
                    "FljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAopr" +
                    "ih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVO" +
                    "rFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";*/
    		"MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDhHQtSARu5cX6KZ7jHs59fM+0n"
    		+"o8VAJ+KVPie9DgrysZ77MekDij1Ye3qLgXSDC6pmRagNtQbZHBwiV7zwRpqXpHwP"
    		+"NQ67bWWjuGkiRA+awCUQJWVZZ+2VIJciDLBnzhgxWzViK0RKRTCVIUGo0NHfB2er"
    		+"YZ3NZr+e4GQihBECswIDAQAB";

    // 萌狮子的私钥（PKCS8格式）
    public final static String MSZ_RSA_PRIVATE =
           /* "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALVWntC/Tc/OXUwc" +
            "hIdSb+kaLIFq4tVSsWM6gc/7k0PJTir1ljB1ao89QWb50HF+UzIGnI/Cjes8LVqO" +
            "8IlM9AkbTD+ZHfP+bCZKCuQh/Tb5qpDAEc8ffUOhfue7kOR1C2ihSjUUZBYtoNfQ" +
            "AccWZ6UhcIOcuY4Ql1koJT+CnFePAgMBAAECgYBR/OL7YLNOtHAsXGxzPouIqiPb" +
            "M7dxWd0ID0jn/0fbCyZDeBw6tqvCLaDnbSWYWtREtwaMXRuLcv7ShfQtL9TZklZC" +
            "RUFoimloHa4WgFfmqK2TZfNGiv3qBtLRrng83h9HJO+d5L6rYiGhGXKe/QeqVGb9" +
            "DCcLg/7OFrFk9nBE2QJBANu/qSwl3TBoehxuWttCuRmP7J8pGzmIrNsmbrCmb9of" +
            "/HYqtOyiy/AxvMf5faaP/mAmCcw/GTqMBeA8gg6TOLUCQQDTQNSQ3FuT2CizMflN" +
            "YKPMWhb2Kb77BgGY2c2jnOLgvl1d8Xhk0JteF3I67kCBrXGe9FOVOiebO1FMUd6j" +
            "sI2zAkAiJb5cJeYhR5ryIBPuTogE+OCEOxcH8b0qsOYyfSqUcrQAiNJkk0eto4z6" +
            "ZkKoR2f3+PE80/FPr8KZ/c0YBrihAkB2yhm9a2vDcuVP/mXlcNYqbu5Cda8CkJoN" +
            "TUwpGGsrXWA7FzFP302+VwjQwnBbeyBM9a6aVx02WOCaPd8nxHupAkEAoFxMoU9L" +
            "pk7lwBMlsW4E9mJluACmRcbRyZ+Q54bLe4RBMsli172e7icKf2vpofnSRmfwQD24" +
            "FlXE6iw9LHHklQ==";*/
    "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAOEdC1IBG7lxfopn"
    +"uMezn18z7SejxUAn4pU+J70OCvKxnvsx6QOKPVh7eouBdIMLqmZFqA21BtkcHCJX"
    +"vPBGmpekfA81DrttZaO4aSJED5rAJRAlZVln7ZUglyIMsGfOGDFbNWIrREpFMJUh"
    +"QajQ0d8HZ6thnc1mv57gZCKEEQKzAgMBAAECgYBMY4222xdsWz4Ee2PxqFtZdAZy"
    +"4BkMRzyVRk030y8V4f4/4VTf1aYxg103P8uouGmvYM3BMP/M5sQJR1qe8mZ8ka3m"
    +"thfLwAWuyscf6yxZenwbSAIUW5BXWfmLzotcuuGksS60YbQUd54qH9Hvn4JyLWyZ"
    +"fbukXnBdmIBkCAFn+QJBAP4K4Kda2oBgBE62VcGAVCshCAlLifAtU/gko7CgBifj"
    +"Rsp8I+GgMmqPzBIFCA6wLS+wyv4TC+sAx4wbgBwfl88CQQDi2Rn4q+FL29DWTv/S"
    +"8Kdk5ElDMGclF3z6h6GAqwJ7gcg9mItxee/SN7Cqz7zFj6p0MK28OT44D+FeXieH"
    +"nfvdAkEAw6BWvSkOZ5sI1iA8y2Rp7Ehkatv7nbMaHVUBpEqmUuMGqXiNHPsCt+KB"
    +"0EKFoOHz+eyXdK3wGIiiIXcv4PhHKwJAe3wIyjfuNVgcL3zjf5Um2iH2y49epm9J"
    +"xirsvFjayhaUxQrz5TqmVL2h+umIbdbNx9/zwwE6IFRsruz8AbP7NQJBAOgarQwS"
    +"Fjt4C9B2MFYBuln26Lyt7psN1EScBhB7CodxbAYsX+y/m7P4VR73EMHKy9pJN7DY"
    +"xh0wTMJORrbU8KQ=";

    // Aplipay的Callback参数, 参考支付宝支付接口开发包2.0标准版副本 ch7
    public final static String NOTIFY_TIME   = "notify_time";
    public final static String NOTIFY_TYPE   = "notify_type";
    public final static String NOTIFY_ID     = "notify_id";
    public final static String SIGN_TYPE     = "sign_type";
    public final static String SIGN          = "sign";

    public final static String OUT_TRADE_NO  = "out_trade_no";   // 商户订单唯一标识
    public final static String SUBJECT       = "subject";        // 商品名称
    public final static String PAYMENT_TYPE  = "payment_type";
    public final static String TRADE_NO      = "trade_no";
    public final static String TRADE_STATUS  = "trade_status";
    public final static String SELLER_ID     = "seller_id";      // 卖家id
    public final static String SELLER_EMAIL  = "seller_email";
    public final static String BUYER_ID      = "buyer_id";       // 买家支付宝账号id
    public final static String BUYER_EMAIL   = "buyer_email";    // 买家支付宝账号email
    public final static String TOTAL_FEE     = "total_fee";
    public final static String QUANTITY      = "quantity";       // 商品数量
    public final static String PRICE         = "price";          // 商品单价
    public final static String BODY          = "body";           // 商品描述
    public final static String GMT_CREATE    = "gmt_create";     // 交易创建时间
    public final static String GMT_PAYMENT   = "gmt_payment";    // 交易付款时间
    public final static String IS_TOTAL_FEE_ADJUST = "is_total_fee_adjust";
    public final static String USE_COUPON    = "use_coupon";     // 是否使用红包
    public final static String DISCOUNT      = "discount";       // 折扣
    public final static String REFUND_STATUS = "refund_status"; // 退款状态
    public final static String GMT_REFUND    = "gmt_refund";    // 退款时间

}
