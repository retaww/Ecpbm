package com.ecpbm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.ecpbm.dao.provider.UserInfoDynaSqlProvider;
import com.ecpbm.pojo.UserInfo;

public interface UserInfoDao {
	//状态为1的为系统合法客户
	@Select("select * from user_info where status = 1")
	public List<UserInfo> getVaildUser();
	@Select("select * from user_info where id=#{id}")
	public UserInfo getUserInfoById(int id);
	//分页获取客户的信息
	@SelectProvider(type = UserInfoDynaSqlProvider.class,method = "selectWithParam")
	List<UserInfo> selectByPage(Map<String,Object> params);
	//根据条件查询客户总数
	@SelectProvider(type = UserInfoDynaSqlProvider.class,method = "count")
	Integer count(Map<String ,Object> params);
	//更新用户状态
	@Update("update user_info set status=#{flag} where id in (#{ids})")
	void updateStatus(@Param("ids")String ids,@Param("flag")int flag);
}
