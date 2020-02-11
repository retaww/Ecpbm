package com.ecpbm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ecpbm.dao.OrderDao;
import com.ecpbm.pojo.OrderDetail;
import com.ecpbm.pojo.OrderInfo;
import com.ecpbm.pojo.Pager;
import com.ecpbm.service.OrderService;

@Service("orderService")
@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDao orderDao;
	
	public List<OrderInfo> findOrderInfo(OrderInfo orderInfo, Pager pager) {
		// TODO Auto-generated method stub
		Map<String , Object> params = new HashMap<String, Object>();
		params.put("orderInfo", orderInfo);
		int recordCount = orderDao.count(params);
		pager.setRowCount(recordCount);
		if(recordCount>0) {
			params.put("pager", pager);
		}
		return orderDao.selectByPage(params);
	}

	public Integer count(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return orderDao.count(params);
	}

	public int addOrderInfo(OrderInfo oi) {
		// TODO Auto-generated method stub
		return orderDao.saveOrderInfo(oi);
	}

	public int addOrderDetail(OrderDetail od) {
		// TODO Auto-generated method stub
		return orderDao.saveOrderDetail(od);
	}

	public OrderInfo getOrderInfoById(int id) {
		// TODO Auto-generated method stub
		return orderDao.getOrderInfoById(id);
	}

	public List<OrderDetail> getOrderDetail(int oid) {
		// TODO Auto-generated method stub
		return orderDao.getOrderDetailByOid(oid);
	}

	public int deleteOrder(int id) {
		// TODO Auto-generated method stub
		int result = 1;
		try {
			orderDao.deleteOrderInfo(id);
			orderDao.deleteOrderDetail(id);
		}catch (Exception e) {
			e.printStackTrace();
			result = 0; 
		}
		return result;
	}

}
