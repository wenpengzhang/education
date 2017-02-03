package com.edu.core.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.edu.core.domain.Course;
import com.edu.core.domain.Ordering;
import com.edu.core.domain.Red;
import com.edu.core.service.CourseService;
import com.edu.core.service.OrderingService;
import com.edu.core.service.RedService;
import com.edu.pay.client.ClientResponseHandler;
import com.edu.pay.client.TenpayHttpClient;
import com.edu.pay.handler.RequestHandler;
import com.edu.pay.handler.ResponseHandler;

import weixin.Utils.JdomParseXmlUtils;
import weixin.entity.WXPayResult;

/**
 * 
 * @author lee 2016.11.22
 */
@Controller
@RequestMapping("/weixin")
public class WeiXinPayController extends BaseController {

	// private Logger log = Logger.getLogger(WeiXinPayController.class);
	@Resource
	private OrderingService orderingService;
	@Resource
	private RedService redService;
	@Resource
	private CourseService courseService;

	@ResponseBody
	@RequestMapping("/weixin")
	public void doWeinXinRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 商户号
		String partner = "1421196802";
		// 密钥
		String key = "3nqf0bjJ00lTkVVLMeIwgWHXSm4fn7E6";

		StringBuffer sb = new StringBuffer();
		InputStream inputStream = request.getInputStream();
		String s;
		BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
		while ((s = in.readLine()) != null) {
			sb.append(s);
		}
		System.out.println("sb=" + sb.toString());
		WXPayResult wxresult = JdomParseXmlUtils.getWXPayResult(sb.toString());
		System.out.println("wxresult=" + wxresult.toString());
		System.out.println("wxresult.getAttach()=" + wxresult.getAttach());
		if ("SUCCESS".equals(wxresult.getReturn_code())) {
			System.out.println("修改订单状态");
			// 获取商户订单号
			String ordercode = wxresult.getOut_trade_no();
			System.out.println("ordercode=" + ordercode);
			// 获取返回的红包ID
			String attach = wxresult.getAttach();
			System.out.println("attach=" + attach);

			// 修改订单信息
			Ordering ordering = orderingService.selectByCode(ordercode);
			if(!"已付款".equals(ordering.getPaystate())){
				ordering.setPaystate("已付款");
				this.orderingService.updateByPrimaryKey(ordering);
				System.out.println("修改订单状态");
				// 修改该课程的参加人数
				Course course = courseService.selectByPrimaryKey(ordering.getCourseid());
				Integer Cnumber = course.getCnumber();
				System.out.println("=======支付结束cnumber=" + Cnumber);
				course.setCnumber(Cnumber + 1);
				this.courseService.updateByPrimaryKey(course);
				// 修改红包状态
				if (!"redid=".equals(attach) && attach != null) {
					System.out.println("修改红包状态");
					String[] reds = attach.split("=");
					Red red = new Red();
					red.setId(Integer.parseInt(reds[1]));
					red.setRstatus(1);
					this.redService.updateRedById(red);
				}
			}
			String stwxml = sendToWx();
			System.out.println("1c54u>>>xml_back>>" + stwxml);
			printHandle(response, new StringBuffer(stwxml));
			return;
		}
	}

	private void printHandle(HttpServletResponse response, StringBuffer sb) {
		PrintWriter out = null;
		try {
			response.setContentType("text/xml");
			out = response.getWriter();
			out.println(sb.toString());
			out.flush();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (Exception e) {
				}
			}
		}
	}

	public String sendToWx() {
		String xml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg>"
				+ "</xml>";
		return xml;
	}
}
