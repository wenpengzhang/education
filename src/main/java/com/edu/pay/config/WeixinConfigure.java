package com.edu.pay.config;

/**
 * User: rizenguo
 * Date: 2014/10/29
 * Time: 14:40
 * 这里放置各种配置数据
 */
public class WeixinConfigure {
//这个就是自己要保管好的私有Key了（切记只能放在自己的后台代码里，不能放在任何可能被看到源代码的客户端程序中）
	// 每次自己Post数据给API的时候都要用这个key来对所有字段进行签名，生成的签名会放在Sign这个字段，API收到Post数据的时候也会用同样的签名算法对Post过来的数据进行签名和验证
	// 收到API的返回的时候也要用这个key来对返回的数据算下签名，跟API的Sign数据进行比较，如果值不一致，有可能数据被第三方给篡改

	public static final String KEY = "df1a4daf4e14a8d705a409cc61b0595f";

	//微信分配的公众号ID（开通公众号之后可以获取到）
	public static final String APP_ID = "wx4cfed37b0b652209";

	//微信分配的公众号ID（开通公众号之后可以获取到）
	public static final String APP_ID_4_GZH = "wx9354f423ef8e4b47";

	//微信支付分配的商户号ID（开通公众号的微信支付功能之后可以获取到）
	public static final String MCH_ID = "1270054301";

	//微信支付分配的商户号ID（开通公众号的微信支付功能之后可以获取到）
	public static final String MCH_ID_4_GZH = "1270060201";

	//受理模式下给子商户分配的子商户号
	public static final String SUB_MCH_ID = "";

	//HTTPS证书的本地路径
	public static final String CERT_LOCAL_PATH = "";

	//HTTPS证书密码，默认密码等于商户号MCHID
	public static final String CERT_PASSWORD = "";

	//是否使用异步线程的方式来上报API测速，默认为异步模式
	public static final boolean USE_THREAD_TO_DO_REPORT = true;

	// 统一下单的测试回调地址
	public static final String UNIFIED_ORDER_NOTIFY_TEST_URL = "http://tstest.zlapi.com/order/cl/wx";

	// 统一下单的线上回调地址
	public static final String UNIFIED_ORDER_NOTIFY_URL = "http://ts.zlapi.com/order/cl/wx";

	//机器IP(测试用)
	public static final String ip = "";

	//以下是几个API的路径：
	//1）被扫支付API
	public static final String PAY_API = "https://api.mch.weixin.qq.com/pay/micropay";

	//2）被扫支付查询API
	public static final String PAY_QUERY_API = "https://api.mch.weixin.qq.com/pay/orderquery";

	//3）退款API
	public static final String REFUND_API = "https://api.mch.weixin.qq.com/secapi/pay/refund";

	//4）退款查询API
	public static final String REFUND_QUERY_API = "https://api.mch.weixin.qq.com/pay/refundquery";

	//5）撤销API
	public static final String REVERSE_API = "https://api.mch.weixin.qq.com/secapi/pay/reverse";

	//6）下载对账单API
	public static final String DOWNLOAD_BILL_API = "https://api.mch.weixin.qq.com/pay/downloadbill";

	//7) 统计上报API
	public static final String REPORT_API = "https://api.mch.weixin.qq.com/payitil/report";

}
