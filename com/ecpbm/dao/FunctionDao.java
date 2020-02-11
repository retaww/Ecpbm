package com.ecpbm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.ecpbm.pojo.Functions;

public interface FunctionDao {
	//根据管理员的id获取功能权限
	@Select("select * from functions where id in (select fid from powers where aid=#{aid})")
	public List<Functions> selectByAdminId(Integer aid);
}
