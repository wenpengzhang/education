package com.edu.core.service;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.edu.alipay.core.Alipay;
import com.edu.alipay.core.AlipayBuilder;
import com.edu.alipay.model.pay.AppPayDetail;

/**
 * 
 * @author lee
 *
 */
@Service("payService")
public class PayService {
    private String merchantId = "123";

    private String secret = "123";

    private String payNotifyUrl= "123";

    private String refundNotifyUrl= "123";

    private String webReturnUrl= "123";

    private String wapReturnUrl= "123";

    private Alipay alipay;
    
    @PostConstruct
    public void initAlipay(){
        alipay = AlipayBuilder
                    .newBuilder(merchantId, secret)
                    .build();

        System.err.println(alipay);
    }
    
    /**
     * MD5验证
     */
    public Boolean notifyVerifyMd5(Map<String, String> params){
        return alipay.verify().md5(params);
    }

    public String appPay(AppPayDetail detail){
    	detail.setNotifyUrl(payNotifyUrl);
    	return alipay.pay().appPay(detail);
    }
}
