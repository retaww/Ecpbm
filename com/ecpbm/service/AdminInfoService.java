package com.ecpbm.service;

import com.ecpbm.pojo.AdminInfo;

public interface AdminInfoService {
	//登录验证
	public AdminInfo login(AdminInfo ai);
	////根据id获取管理员对象即关联的功能集合
	public AdminInfo getAdminInfoAndFunctions(Integer id);
}
