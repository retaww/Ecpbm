package com.ecpbm.service;

import java.util.List;
import java.util.Map;

import com.ecpbm.pojo.OrderDetail;
import com.ecpbm.pojo.OrderInfo;
import com.ecpbm.pojo.Pager;

public interface OrderService {
	//分页获取订单信息
	List<OrderInfo> findOrderInfo(OrderInfo orderInfo,Pager pager);
	//订单计数
	Integer count(Map<String, Object> params);
	//添加订单主表
	int addOrderInfo(OrderInfo oi);
	//添加订单明细
	int addOrderDetail(OrderDetail od);
	//根据订单编号获取订单对象
	OrderInfo getOrderInfoById(int id);
	//根据订单编号获取订单明细
	List<OrderDetail> getOrderDetail(int oid);
	//删除订单
	int deleteOrder(int id);
	
}
