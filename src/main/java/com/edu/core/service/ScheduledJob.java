package com.edu.core.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.edu.core.domain.Ordering;
import com.edu.core.service.OrderingService;
@Component
public class ScheduledJob{
	@Resource
	private OrderingService orderingService;
	
	@Scheduled(cron="0/5 * * * * ?")
	public void deleteOvertimeOrdering()  {  
		List<Ordering> orderingList = orderingService.selectBySql("TO_SECONDS(NOW()) - TO_SECONDS(ordertime) <= 120 AND pstate = '进行中' ");
		for(Ordering order: orderingList){
			orderingService.deleteByPrimaryKey(order.getId());
		}
		System.out.println("定时任务运行");
    }  
}
