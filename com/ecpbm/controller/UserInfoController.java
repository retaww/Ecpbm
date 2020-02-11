package com.ecpbm.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ecpbm.pojo.Pager;
import com.ecpbm.pojo.UserInfo;
import com.ecpbm.service.UserInfoService;

@Controller
@RequestMapping("/userinfo")
public class UserInfoController {
		@Autowired
		private UserInfoService userInfoService;
		
		@RequestMapping(value = "getValidUser")
		@ResponseBody
		public List<UserInfo> getValidUser(){
			List<UserInfo> list = userInfoService.getVaildUser();
			UserInfo ui = new UserInfo();
			ui.setId(0);
			ui.setUserName("请选择..");
			list.add(ui);
			return list;
			
		}
		
		
		@RequestMapping(value = "/list")
		@ResponseBody
		public Map<String,Object> getList(Integer page,Integer rows,UserInfo userInfo){
			Pager pager = new Pager();
			pager.setCurPage(page);
			pager.setPerPageRows(rows);
			Map<String , Object> params = new HashMap<String, Object>();
			params.put("userInfo", userInfo);
			int totalCount = userInfoService.count(params);
			List<UserInfo> list = userInfoService.findUserInfo(userInfo, pager);
			
			Map<String , Object> result = new HashMap<String, Object>();
			result.put("total", totalCount);
			result.put("rows", list);
			return result;
		}
		
		@RequestMapping(value = "/setIsEnableUser",produces = "text/html;charset=UTF-8")
		@ResponseBody
		public String setIdEnableUser(@RequestParam("uids") String uids,@RequestParam("flag") String flag) {
			try {
				userInfoService.modifyStatus(uids.substring(0, uids.length()-1), Integer.parseInt(flag));
				return "{\"success\":\"true\",\"message\":\"更改成功\"}";
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return "{\"success\":\"false\",\"message\":\"更改失败\"}";
			}
				
		}
}
