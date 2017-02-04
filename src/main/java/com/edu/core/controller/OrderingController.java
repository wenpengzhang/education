package com.edu.core.controller;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.edu.core.dao.OrderingMapper;
import com.edu.core.domain.Course;
import com.edu.core.domain.Ordering;
import com.edu.core.domain.Red;
import com.edu.core.domain.SubCourse;
import com.edu.core.domain.Teacher;
import com.edu.core.domain.ViewOrder;
import com.edu.core.service.CourseService;
import com.edu.core.service.OrderingService;
import com.edu.core.service.RedService;
import com.edu.core.service.SubCourseService;
import com.edu.core.service.TeacherService;
import com.edu.core.service.ViewOrderService;
import com.edu.pay.util.JsonUtil;
import com.edu.pay.util.TenpayUtil;

import weixin.Utils.HttpXmlUtils;
import weixin.Utils.ParseXMLUtils;
import weixin.Utils.RandCharsUtils;
import weixin.Utils.WXSignUtils;
import weixin.Utils.WeixinConfigUtils;
import weixin.entity.Unifiedorder;

@Controller
@RequestMapping("/ordering")
public class OrderingController extends BaseController {

	@Resource
	private CourseService courseService;

	@Resource
	private SubCourseService subCourseService;

	@Resource
	private TeacherService teacherService;

	@Resource
	private OrderingService orderingService;

	@Resource
	private ViewOrderService viewOrderService;

	@Resource
	private RedService redService;

	public OrderingController() {
		// TODO Auto-generated constructor stub
	}

	@ResponseBody
	@RequestMapping(value = "/getListbyPage")
	public Map<String, Object> getListbyPage(HttpServletRequest request, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			request.setCharacterEncoding("UTF-8");
			String skeyword = request.getParameter("keyword");
			System.out.println("skeyword=" + skeyword);
			String skeytjr = request.getParameter("skeytjr");
			System.out.println("skeytjr=" + skeytjr);
			int iPageIndex = Integer.parseInt(request.getParameter("pageindex"));
			String sqlwhere = null;
			if (!"".equals(skeytjr) && skeytjr != null) {
				sqlwhere = "(tjr like '%" + skeytjr + "%') and paystate='已付款'";
			} else {
				if (!"".equals(skeyword) && skeyword != null) {
					sqlwhere = "studentmobile like '%" + skeyword + "%' or coursename like '%" + skeyword
							+ "%' and paystate='已付款'";
				} else {
					sqlwhere = "paystate='已付款'";
				}
			}
			List<ViewOrder> list = this.viewOrderService.selectBylimit(sqlwhere, (iPageIndex - 1) * iLimit,
					iPageIndex * iLimit);
			for (ViewOrder item : list) {
				String teacherids = "";
				String teachernames = "";
				List<SubCourse> sublist = subCourseService.selectBySql("courseid='" + item.getCourseid() + "'");
				for (int i = 0; i < sublist.size(); i++) {
					Teacher teacher = teacherService.selectByPrimaryKey(sublist.get(i).getTeacherid());
					if (!teacherids.contains(teacher.getId())) {
						teachernames += teacher.getRealname() + ",";
						teacherids += teacher.getId() + ",";
					}
				}
				if (!teachernames.equals("")) {
					teachernames = teachernames.substring(0, teachernames.length() - 1);
				}
				item.setTeacherid(teachernames);
			}
			// 计算总页数
			List<ViewOrder> list1 = this.viewOrderService.selectBySql(sqlwhere);
			// 计算总页数
			int totalpage = 0;
			if (list1.size() > 0) {
				if (list1.size() % 20 > 0) {
					totalpage = (list1.size() / 20) + 1;
				} else {
					totalpage = list1.size() / 20;
				}

			}
			map.put("total", list.size());
			map.put("totalpage", totalpage);
			System.out.println("list=" + list.toString());
			map.put("records", list);

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}

	@ResponseBody
	@RequestMapping(value = "/getOrderListBystudentidAndConditions")
	public Map<String, Object> getOrderListBystudentidAndConditions(HttpServletRequest request, Model model) {
		String studentid = request.getParameter("studentid");
		String pstate = request.getParameter("pstate");
		String sqlwhere = "studentid='" + studentid + "'";
		if (pstate != null && !pstate.equals("")) {
			sqlwhere = sqlwhere + "and pstate = '" + pstate + "'";
		}
		sqlwhere = sqlwhere + "order by ordertime desc";
		List<ViewOrder> list = this.viewOrderService.selectBySql(sqlwhere);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", list.size());
		map.put("records", list);
		return map;
	}

	@ResponseBody
	@RequestMapping(value = "/getOrderListByteacheridAndConditions")
	public Map<String, Object> getOrderListByteacheridAndConditions(HttpServletRequest request, Model model) {
		String teacherid = request.getParameter("teacherid");
		String pstate = request.getParameter("pstate");
		String sqlwhere = "pstate = '" + pstate + "' and studentid='" + teacherid + "'";
		List<ViewOrder> list = this.viewOrderService.selectBySqlAndTeacherid(sqlwhere, teacherid);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", list.size());
		map.put("records", list);
		return map;
	}

	@ResponseBody
	@RequestMapping("/show")
	public Map<String, Object> show(HttpServletRequest request, Model model) {
		String id = request.getParameter("id");
		Ordering ordering = this.orderingService.selectByPrimaryKey(id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", ordering);
		return map;
	}

	/**
	 * 生成订单信息
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping("/setOrder")
	public Map<String, Object> setOrder(HttpServletRequest request, Model model) throws ParseException {
		String studentid = request.getParameter("studentid");
		String courseid = request.getParameter("courseid");
		/* String paymode = request.getParameter("paymode"); */
		String sqlwhere = "studentid = '" + studentid + "' and courseid ='" + courseid + "'";
		List<Ordering> list = this.orderingService.selectBySql(sqlwhere);
		Map<String, Object> map = new HashMap<String, Object>();
		if (list.size() <= 0) {
			Course course = courseService.selectByPrimaryKey(courseid);
			Ordering ordering = new Ordering();
			Date currentDate = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			String formatStr = formatter.format(currentDate);
			ordering.setOrdercode(formatStr);
			ordering.setStudentid(studentid);
			ordering.setCourseid(courseid);
			ordering.setSummoney(course.getPrice());
			/* ordering.setPaymode(paymode); */
			ordering.setOrdertime(currentDate);
			Long chage = Math.round(course.getPrice() * 0.1);
			ordering.setChage(chage);
			ordering.setPaystate("未付款");
			ordering.setPstate("进行中");
			String id = UUID.randomUUID().toString().replace("-", "");
			ordering.setId(id);

			Integer Cnumber = course.getCnumber();
			if (Cnumber + 1 <= course.getCcount()) {
				System.out.println("=======cnumber=" + Cnumber);
				// course.setCnumber(Cnumber + 1);
				// this.courseService.updateByPrimaryKey(course);
				this.orderingService.insert(ordering);
				map.put("code", "1111");
				map.put("success", true);
				map.put("message", "添加成功！");
				map.put("orderingid", id);
			} else {
				map.put("code", "1112");
				map.put("success", true);
				map.put("message", "下单失败，因为课程已经满员。");
				map.put("orderingid", "");
			}

		} else {
			map.put("code", "0000");
			map.put("success", false);
			map.put("message", "订单已经存在！");
		}
		return map;
	}

	@ResponseBody
	@RequestMapping("/payOrder")
	public Map<String, Object> payOrder(HttpServletRequest request, Model model) throws ParseException {
		String id = request.getParameter("orderid");// 订单id
		String paymode = request.getParameter("paymode");// 付款方式
		Ordering ordering = this.orderingService.selectByPrimaryKey(id);
		Map<String, Object> map = new HashMap<String, Object>();
		if (ordering != null && ordering.getPaystate().equals("未付款")) {

			ordering.setPaymode(paymode);
			ordering.setPaystate("已付款");
			this.orderingService.updateByPrimaryKey(ordering);
			map.put("code", "1111");
			map.put("success", true);
			map.put("message", "添加成功！");

		} else {
			map.put("code", "0000");
			map.put("success", false);
			map.put("message", "订单不存在或者已经付款");
		}
		return map;
	}

	@ResponseBody
	@RequestMapping("/prePayOrder")
	public Map<String, Object> prePayOrder(HttpServletRequest request, Model model) throws ParseException {

		String id = request.getParameter("orderid");// 订单id
		String paymode = request.getParameter("paymode");// 付款方式
		String redid = request.getParameter("redid");// 红包id
		Ordering ordering = this.orderingService.selectByPrimaryKey(id);
		// 修改订单支付方式
		ordering.setPaymode(paymode);
		this.orderingService.updateByPrimaryKey(ordering);
		// 获取子课程
		Course course = this.courseService.selectByPrimaryKey(ordering.getCourseid());
		Map<String, Object> map = new HashMap<String, Object>();
		// 付款金额
		double subtract = ordering.getSummoney();
		// 判断是否使用红包,然后做付款金额计算
		if (!"".equals(redid)) {
			Red red = this.redService.selectRedById(Integer.parseInt(redid));
			// double d = (double)ordering.getSummoney();
			double d2 = (double) red.getPrice();
			BigDecimal bigDecimal = new BigDecimal(ordering.getSummoney());
			BigDecimal bigDecimal2 = new BigDecimal(Double.toString(d2));
			if (bigDecimal2.compareTo(bigDecimal) < 0) {
				BigDecimal bigDecimalSubtract = bigDecimal.subtract(bigDecimal2);
				subtract = bigDecimalSubtract.doubleValue();
			} else {
				subtract = 0.01;
			}
		}
		// 判断支付方式
		if ("微信支付".equals(paymode)) {
			// 参数组
			String appid = "wxca8f832d6c6c732b";
			String mch_id = "1421196802";
			String nonce_str = RandCharsUtils.getRandomString(16);
			// String nonce_str = "ibuaiVcKdpRxkhJA";
			// 改成课程名字
			String body = null;
			if (course != null) {
				body = course.getName();

			} else {
				body = "课程费用";
			}
			String detail = "支付课程费用";
			String attach = "redid=" + redid;
			System.out.println("attach=" + attach);
			String out_trade_no = ordering.getOrdercode();
			int total_fee = (int) (subtract * 100);// 单位是分，即是0.01元
			String spbill_create_ip = "127.0.0.1";
			String time_start = RandCharsUtils.timeStart();
			// System.out.println(time_start);
			String time_expire = RandCharsUtils.timeExpire();
			// System.out.println(time_expire);
			String notify_url = "http://123.56.153.57:8080/education/weixin/weixin";
			// System.out.println("notify_url是：" + notify_url);
			String trade_type = "APP";
			System.out.println("nonce_str=" + nonce_str);
			System.out.println("out_trade_no=" + out_trade_no);
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
			System.out.println("sign" + sign);
			unifiedorder.setSign(sign);
			unifiedorder.setBody(body);
			unifiedorder.setDetail(detail);
			unifiedorder.setAttach(attach);
			unifiedorder.setOut_trade_no(out_trade_no);
			unifiedorder.setTotal_fee(total_fee);
			unifiedorder.setSpbill_create_ip(spbill_create_ip);
			unifiedorder.setTime_start(time_start);
			unifiedorder.setTime_expire(time_expire);
			unifiedorder.setNotify_url(notify_url);
			unifiedorder.setTrade_type(trade_type);

			// 构造xml参数
			String xmlInfo = HttpXmlUtils.xmlInfo(unifiedorder);
			System.out.println("xmlInfo=" + xmlInfo);
			String wxUrl = "https://api.mch.weixin.qq.com/pay/unifiedorder";

			String method = "POST";

			String weixinPost = HttpXmlUtils.httpsRequest(wxUrl, method, xmlInfo).toString();

			System.out.println("weixinPost=" + weixinPost);

			Map<String, String> map1 = ParseXMLUtils.jdomParseXml(weixinPost);

			// 二次签名
			SortedMap<Object, Object> parameters1 = new TreeMap<Object, Object>();
			parameters1.put("appid", appid);
			parameters1.put("partnerid", "1421196802");
			parameters1.put("prepayid", map1.get("prepay_id"));
			parameters1.put("noncestr", map1.get("nonce_str"));
			long time1 = Long.parseLong(String.valueOf(System.currentTimeMillis()).toString().substring(0, 10));
			System.out.println("时间戳是=" + time1);
			parameters1.put("timestamp", time1);
			parameters1.put("package", "Sign=WXPay");

			String sign1 = WXSignUtils.createSign("UTF-8", parameters1);
			System.out.println("二次签名是：" + sign1);
			map.put("appid", appid);
			map.put("partnerid", "1421196802");
			map.put("prepayid", map1.get("prepay_id"));
			map.put("noncestr", map1.get("nonce_str"));
			map.put("timestamp", time1);
			map.put("package", "Sign=WXPay");
			map.put("sign", sign1);
		}
		if ("支付宝支付".equals(paymode)) {
			map.put("code", "0000");
			map.put("success", false);
			map.put("message", "暂不支持支付宝支付");
		}
		map.put("code", "1111");
		map.put("success", true);
		map.put("message", "添加成功！");

		return map;
	}

	@RequestMapping("/cancePrelOrder")
	@ResponseBody
	public Map<String, Object> cancePrelOrder(HttpServletRequest request, Model model) {

		Map<String, Object> map = new HashMap<String, Object>();
		String id = request.getParameter("id");
		int result = this.orderingService.deleteByPrimaryKey(id);
		if (result > 0) {
			map.put("code", "1111");
			map.put("success", true);
			map.put("message", "删除成功！");
		} else {
			map.put("code", "0000");
			map.put("success", false);
			map.put("message", "删除失败！");
		}
		return map;
	}

	@ResponseBody
	@RequestMapping("/statistics")
	public Map<String, Object> statistics(HttpServletRequest request, Model model) throws ParseException {
		Date datetime = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.setTime(datetime);
		Integer week = calendar.get(Calendar.WEEK_OF_YEAR);
		week = week - 1;
		Integer year = calendar.get(Calendar.YEAR);
		Map<String, Object> map = new HashMap<String, Object>();
		SimpleDateFormat dayformat = new SimpleDateFormat("yyyyMMdd");
		String sday = dayformat.format(datetime);
		map.put("daysale", this.orderingService.getMoneyByDay(sday, "已付款"));
		map.put("dayrefund", this.orderingService.getMoneyByDay(sday, "退款"));
		map.put("dayfee", this.orderingService.getChageByDay(sday));
		String sweak = year.toString() + week.toString();
		map.put("weaksale", this.orderingService.getMoneyByWeak(sweak, "已付款"));
		map.put("weakrefund", this.orderingService.getMoneyByWeak(sweak, "退款"));
		map.put("weakfee", this.orderingService.getChageByWeak(sweak));
		SimpleDateFormat monthformat = new SimpleDateFormat("yyyyMM");
		String smonth = monthformat.format(datetime);
		map.put("monthsale", this.orderingService.getMoneyByMonth(smonth, "已付款"));
		SimpleDateFormat yearformat = new SimpleDateFormat("yyyy");
		String syear = yearformat.format(datetime);
		map.put("yearsale", this.orderingService.getMoneyByYear(syear, "已付款"));
		return map;
	}
}
