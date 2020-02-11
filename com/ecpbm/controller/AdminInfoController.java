package com.ecpbm.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.ecpbm.pojo.AdminInfo;
import com.ecpbm.pojo.Functions;
import com.ecpbm.pojo.TreeNode;
import com.ecpbm.service.AdminInfoService;
import com.ecpbm.util.JsonFactory;

@SessionAttributes(value = {"admin"})
@Controller
@RequestMapping("/admininfo")
public class AdminInfoController {
	
	@Autowired
	private AdminInfoService adminInfoService;
	@RequestMapping(value = "/login",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String login(AdminInfo ai ,ModelMap model) {
		AdminInfo adminInfo = adminInfoService.login(ai);
		if(adminInfo!=null&&adminInfo.getName()!=null) {
			if(adminInfoService.getAdminInfoAndFunctions(adminInfo.getId()).getFs().size()>0) {
				model.put("admin", adminInfo);
				//以json的格式发送成功信息
				return "{\"success\":\"true\",\"message\":\"登录成功\"}";
			}else {
				return "{\"success\":\"false\",\"message\":\"您没有权限，请练习超级管理员!\"}";
			}
		}
		else {
			return "{\"success\":\"false\",\"message\":\"登录失败!\"}";
		}
	}
	
	@RequestMapping("getTree")
	@ResponseBody
	public List<TreeNode> getTree(@RequestParam(value = "adminid")String adminid){
		AdminInfo adminInfo = adminInfoService.getAdminInfoAndFunctions(Integer.parseInt(adminid));
		List<TreeNode> nodes = new ArrayList<TreeNode>();
		//获取关联的Function对象集合
		List<Functions> functionList = adminInfo.getFs();
		//对List<Functions>类型的Functions对象集合排序
		Collections.sort(functionList);
		//将排序后的lsit集合转换到List<TreeNode>类型的列表nodes
		for(Functions functions :functionList) {
			TreeNode treeNode = new TreeNode();
			treeNode.setId(functions.getId());
			treeNode.setFid(functions.getParentid());
			treeNode.setText(functions.getName());
			nodes.add(treeNode);
		}
		//List<TreeNode> treeNodes = new ArrayList<TreeNode>();
		List<TreeNode> treeNodes = JsonFactory.buildtree(nodes,0);
		return treeNodes;
	}
	
	@RequestMapping(value = "/logout",method = RequestMethod.GET)
	@ResponseBody
	public String logout(SessionStatus status) {
		status.setComplete();
		return "{\"succuss\":\"true\",\"title\":\"注销成功\"}";
	}
	
	
	
	
	
	
	
	
}
