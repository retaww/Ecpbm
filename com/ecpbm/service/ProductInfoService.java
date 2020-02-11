package com.ecpbm.service;

import java.util.List;
import java.util.Map;

import com.ecpbm.pojo.Pager;
import com.ecpbm.pojo.ProductInfo;

public interface ProductInfoService {
	//分页显示商品
	List<ProductInfo> findProductInfo(ProductInfo pi,Pager pager);
	//商品计数
	Integer count(Map<String , Object> params);
	//添加商品
	void addProductInfo(ProductInfo pi);
	//修改商品
	void modifyProductInfo(ProductInfo pi);
	//更新商品状态
	void modifyStatus(String ids,int flag);
	//获取在售商品列表
	List<ProductInfo> getOnSaleProduct();
	//根据商品id获取商品对象
	ProductInfo getProductById(int id);
}
