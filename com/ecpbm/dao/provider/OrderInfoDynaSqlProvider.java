package com.ecpbm.dao.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.ecpbm.pojo.OrderInfo;

public class OrderInfoDynaSqlProvider {
	//分页动态查询
	public String selectWithParam(final Map<String , Object> params) {
		String sql = new SQL() {
			{
				SELECT("*");
				FROM("order_info");
				if(params.get("orderInfo")!=null) {
					OrderInfo orderInfo = (OrderInfo) params.get("orderInfo");
					if(orderInfo.getId()!=null&&orderInfo.getId()>0) {
						WHERE("id=#{orderInfo.id}");
					}
					if(orderInfo.getUid()>0) {
						WHERE("uid = #{orderInfo.uid}");
					}
					if(orderInfo.getStatus()!=null&&!(orderInfo.getStatus()).equals("请选择")) {
						WHERE("status = #{orderInfo.status}");
					}
					if(orderInfo.getOrderForm()!=null&&!(orderInfo.getOrderForm()).equals("")) {
						WHERE("ordertime>#{orderInfo.orderFrom}");
					}
					if(orderInfo.getOrderTimeTo()!=null&&!(orderInfo.getOrderTimeTo()).equals("")) {
						WHERE("ordertime<#{orderInfo.orderTimeTo}");
					}
				}
			}
		}.toString();
		if(params.get("pager")!=null) {
			sql += " limit #{pager.firstLimitParam},#{pager.perPageRows}";
		}
		return sql;
	}
	//根据条件动态查询订单总记录数
		public String count(final Map<String, Object> params) {
			return new SQL() {
				{
					SELECT("count(*)");
					FROM("order_info");
					if(params.get("orderInfo")!=null) {
						OrderInfo orderInfo = (OrderInfo) params.get("orderInfo");
						if(orderInfo.getId()!=null&&orderInfo.getId()>0) {
							WHERE("id=#{orderInfo.id}");
						}else {
							if(orderInfo.getUid()>0) {
								WHERE("uid = #{orderInfo.uid}");
							}
							if(orderInfo.getStatus()!=null&&!(orderInfo.getStatus()).equals("请选择")) {
								WHERE("status = #{orderInfo.status}");
							}
							if(orderInfo.getOrderForm()!=null&&!(orderInfo.getOrderForm()).equals("")) {
								WHERE("ordertime>#{orderInfo.orderFrom}");
							}
							if(orderInfo.getOrderTimeTo()!=null&&!(orderInfo.getOrderTimeTo()).equals("")) {
								WHERE("ordertime<#{orderInfo.orderTimeTo}");
							}
						}
					}
				}
			}.toString();
		}
}
