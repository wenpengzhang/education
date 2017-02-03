package weixin;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import weixin.Utils.HttpXmlUtils;
import weixin.Utils.MD5Util;
import weixin.Utils.ParseXMLUtils;
import weixin.Utils.RandCharsUtils;
import weixin.Utils.WXSignUtils;
import weixin.Utils.WeixinConfigUtils;
import weixin.entity.Unifiedorder;

/**
 * 微信支付测试
 * 
 * @author xiebin
 * @date 2015年11月26日上午9:58:19
 */
public class WeixinPayTest {
	private static String Key = "YQApenj7malZW7wXSY1ew6uYyY3ZkcAL";

	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		WeixinConfigUtils config = new WeixinConfigUtils();
		// 参数组
		String appid = "wxca8f832d6c6c732b";
		String mch_id = "1421196802";
		String nonce_str = RandCharsUtils.getRandomString(16);
		//sys
		// String nonce_str = "ibuaiVcKdpRxkhJA";
		String body = "test";
		String detail = "test";
		String attach = "test";
		
		String out_trade_no = RandCharsUtils.getRandomString(16);
		
		int total_fee = 1;// 单位是分，即是0.01元
		String spbill_create_ip = "127.0.0.1";
		String time_start = RandCharsUtils.timeStart();
		// System.out.println(time_start);
		String time_expire = RandCharsUtils.timeExpire();
		// System.out.println(time_expire);
		String notify_url = "http://127.0.0.1:8080/payNotifyUrl.jsp";
		// System.out.println("notify_url是：" + notify_url);
		String trade_type = "APP";

		SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
		parameters.put("appid", appid);
		parameters.put("mch_id", mch_id);
		parameters.put("nonce_str", nonce_str);
		parameters.put("body", body);
		parameters.put("detail", detail);
		parameters.put("attach", attach);
		parameters.put("out_trade_no", out_trade_no);
		parameters.put("total_fee", total_fee);
		parameters.put("time_start", time_start);
		parameters.put("time_expire", time_expire);
		parameters.put("notify_url", notify_url);
		parameters.put("trade_type", trade_type);
		parameters.put("spbill_create_ip", spbill_create_ip);
		//
		String sign = WXSignUtils.createSign("UTF-8", parameters);
		System.out.println("签名是：" + sign);

		Unifiedorder unifiedorder = new Unifiedorder();
		unifiedorder.setAppid(appid);
		unifiedorder.setMch_id(mch_id);
		unifiedorder.setNonce_str(nonce_str);
		unifiedorder.setSign(sign);
		unifiedorder.setBody(body);
		unifiedorder.setDetail(detail);
		unifiedorder.setAttach(attach);
		unifiedorder.setOut_trade_no(out_trade_no);
		// unifiedorder.setTotal_fee(total_fee);
		unifiedorder.setSpbill_create_ip(spbill_create_ip);
		// unifiedorder.setTime_start(time_start);
		// unifiedorder.setTime_expire(time_expire);
		unifiedorder.setNotify_url(notify_url);
		unifiedorder.setTrade_type(trade_type);

		// 构造xml参数
		String xmlInfo = HttpXmlUtils.xmlInfo(unifiedorder);
		System.out.println("xmlInfo=" + xmlInfo);
		//
		// String wxUrl = "https://api.mch.weixin.qq.com/pay/unifiedorder";
		//
		// String method = "POST";
		//
		// String weixinPost = HttpXmlUtils.httpsRequest(wxUrl, method,
		// xmlInfo).toString();
		//
		// System.out.println(weixinPost);
		//
		// ParseXMLUtils.jdomParseXml(weixinPost);
		System.out.println(">>>模拟微信支付<<<");
		System.out.println("==========华丽的分隔符==========");
		// 微信api提供的参数
		// String appid = "wxca8f832d6c6c732b";
		// String mch_id = "1421196802";
		// String device_info = "1000";
		// String body = "test";
		// String nonce_str = "ibuaiVcKdpRxkhJA";
		// System.out.println("随机字符串是：" + nonce_str);
		// //String nonce_str = "ibuaiVcKdpRxkhJA";
		//
		// SortedMap<Object,Object> parameters = new TreeMap<Object,Object>();
		// parameters.put("appid", appid);
		// parameters.put("mch_id", mch_id);
		// parameters.put("device_info", device_info);
		// parameters.put("body", body);
		// parameters.put("nonce_str", nonce_str);

		String characterEncoding = "UTF-8";
		String weixinApiSign = "D52A1E0BBC300B9AD0564A60F1F4EB83";
		System.out.println("微信的签名是：" + weixinApiSign);
		String mySign = createSign(characterEncoding, parameters);
		System.out.println("我     的签名是：" + mySign);

		if (weixinApiSign.equals(mySign)) {
			System.out.println("恭喜你成功了~");
		} else {
			System.out.println("注定了你是个失败者~");
		}

		String userAgent = "Mozilla/5.0(iphone;CPU iphone OS 5_1_1 like Mac OS X) AppleWebKit/534.46(KHTML,like Geocko) Mobile/9B206 MicroMessenger/5.0";

		char agent = userAgent.charAt(userAgent.indexOf("MicroMessenger") + 15);

		System.out.println("微信的版本号：" + new String(new char[] { agent }));

	}

	/**
	 * 微信支付签名算法sign
	 * 
	 * @param characterEncoding
	 * @param parameters
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String createSign(String characterEncoding, SortedMap<Object, Object> parameters) {
		StringBuffer sb = new StringBuffer();
		Set es = parameters.entrySet();// 所有参与传参的参数按照accsii排序（升序）
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			Object v = entry.getValue();
			if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		sb.append("key=" + Key);
		String sign = MD5Util.MD5Encode(sb.toString(), characterEncoding).toUpperCase();
		return sign;
	}

}
