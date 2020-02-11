package com.ecpbm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ecpbm.dao.UserInfoDao;
import com.ecpbm.pojo.Pager;
import com.ecpbm.pojo.UserInfo;
import com.ecpbm.service.UserInfoService;

@Service("userInfoService")
@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
public class UserInfoServiceImpl implements UserInfoService {
	@Autowired
	private UserInfoDao userInfoDao;
	public List<UserInfo> getVaildUser() {
		// TODO Auto-generated method stub
		return userInfoDao.getVaildUser();
	}

	public UserInfo getUserInfoById(int id) {
		// TODO Auto-generated method stub
		return userInfoDao.getUserInfoById(id);
	}

	public List<UserInfo> findUserInfo(UserInfo userInfo, Pager pager) {
		// TODO Auto-generated method stub
		Map<String ,Object> params = new HashMap<String ,Object>();
		params.put("userInfo", userInfo);
		int recordCount = userInfoDao.count(params);
		pager.setRowCount(recordCount);
		if(recordCount>0) {
			params.put("pager", pager);
		}
		return userInfoDao.selectByPage(params);
	}

	public Integer count(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return userInfoDao.count(params);
	}

	public void modifyStatus(String ids, int flag) {
		// TODO Auto-generated method stub
		userInfoDao.updateStatus(ids, flag);
	}

}
