package com.ecpbm.service;

import java.util.List;

import com.ecpbm.pojo.Type;

public interface TypeService {
	//查询所有商品类型
	List<Type> getAll();
	//添加商品类型
	int addType(Type type);
	//更新商品类型
	void updateType(Type type);
}
