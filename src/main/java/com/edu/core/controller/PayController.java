package com.edu.core.controller;



import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edu.alipay.model.pay.AppPayDetail;
import com.edu.core.service.PayService;
/**
 * 
 * @author lee
 *	2016.11.22
 */
@Controller
@RequestMapping("/pay")
public class PayController extends BaseController{
	
	@Resource
	private PayService payservice;
	/**
	 * 支付宝付款接口
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/alipay")
	public void alipay(HttpServletRequest request,Model model){
		String orderId = request.getParameter("orderid");//订单编号
		String name = request.getParameter("name");//商品名称
		String detail = request.getParameter("detail");//详细信息
		String fee = request.getParameter("fee");//

		AppPayDetail appDetail = new AppPayDetail(orderId, name, fee, detail);
		payservice.appPay(appDetail);
	}
}
