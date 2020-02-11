package com.ecpbm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ecpbm.pojo.OrderDetail;
import com.ecpbm.pojo.OrderInfo;
import com.ecpbm.pojo.Pager;
import com.ecpbm.service.OrderService;
import com.ecpbm.service.ProductInfoService;
import com.ecpbm.service.UserInfoService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Controller
@RequestMapping("/orderinfo")
public class OrderInfoController {
	@Autowired
	OrderService orderService;
	@Autowired
	UserInfoService userInfoService;
	@Autowired
	ProductInfoService productInfoService;
	
	@ResponseBody
	@RequestMapping("/commitOrder")
	public String commitOrder(String inserted,String orderinfo) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			//设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
			mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);
			//将json字符串orderinfo转换成JavaBean对象
			OrderInfo oi = mapper.readValue(orderinfo	, OrderInfo[].class)[0];
			String date = oi.getOrdertime().toString().substring(1,2);
			String month = oi.getOrdertime().toString().substring(3,5);
			String year = oi.getOrdertime().toString().substring(6,10);
			String time = year+"-"+month+"-"+date;
			oi.setOrdertime(time);
			orderService.addOrderInfo(oi);
			//将json字符串==>List<OrderDetail>
			List<OrderDetail> list = mapper.readValue(inserted, new TypeReference<ArrayList<OrderDetail>>() {
			});
			
			for(OrderDetail od:list) {
				od.setoid(oi.getId());
				orderService.addOrderDetail(od);
			}
			return "success";
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "failure";
		}
	}
	
	//分页显示
	@ResponseBody
	@RequestMapping("/list")
	public Map<String, Object> list(Integer page,Integer rows,OrderInfo orderInfo){
		Pager pager = new Pager();
		pager.setCurPage(page);
		pager.setPerPageRows(rows);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("orderInfo", orderInfo);
		int total = orderService.count(params);
		List<OrderInfo> orderInfos = orderService.findOrderInfo(orderInfo, pager);
		Map<String, Object> result = new HashMap<String, Object>(2);
		result.put("total", total);
		result.put("rows", orderInfos);
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value="/deleteOrder",produces="text/html;charset=UTF-8")
	public String deleteOrder(String oids) {
		String str = "";
		try {
			oids = oids.substring(0,oids.length()-1);
			String[] ids = oids.split(",");
			for(String id:ids) {
				orderService.deleteOrder(Integer.parseInt(id));
			}
			str = "{\"success\":\"true\",\"message\":\"删除成功\"}";
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			str = "{\"success\":\"false\",\"message\":\"删除失败\"}";
		}
		return str;
	}
	
	@RequestMapping("/getOrderInfo")
	public String getOrderInfo(String oid,Model model) {
		OrderInfo oi = orderService.getOrderInfoById(Integer.parseInt(oid));
		model.addAttribute("oi", oi);
		return "orderdetail";
	}
	
	@ResponseBody
	@RequestMapping("/getOrderDetail")
	public List<OrderDetail> getOrderDetails(String oid){
		List<OrderDetail> ods = orderService.getOrderDetail(Integer.parseInt(oid));
		for(OrderDetail od:ods) {
			od.setPrice(od.getPi().getPrice());
			od.setTotalprice(od.getPi().getPrice()*od.getNum());
		}
		return ods;
	}
}


























