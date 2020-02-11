package com.ecpbm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ecpbm.pojo.Pager;
import com.ecpbm.pojo.Type;
import com.ecpbm.service.TypeService;

@Controller
@RequestMapping("/type")
public class TypeController {
	@Autowired
	private TypeService typeService;
	@RequestMapping("/getType/{flag}")
	@ResponseBody
	public List<Type> getType(@PathVariable("flag") Integer flag){
		List<Type> typeList = typeService.getAll();
		if(flag==1) {
			Type t = new Type();
			t.setId(0);
			t.setName("请选择...");
			typeList.add(0,t);
		}
		return typeList;
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public Map<String , Object> typeList(Integer page,Integer rows){
		Pager pager = new Pager();
		pager.setCurPage(page);
		pager.setPerPageRows(rows);
		List<Type> list = typeService.getAll();
		int totalCount = list.size();
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total", totalCount);
		result.put("rows", list);
		return result;
	}
	
	@RequestMapping(value="/addType",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addType(Type type ) {
		typeService.addType(type);
		return "{\"success\":\"true\",\"message\":\"商品类型添加成功\"}";
	}
	
	@RequestMapping(value = "/updateType",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updateProduct(Type type) {
		try {
			typeService.updateType(type);
			return "{\"success\":\"true\",\"message\":\"商品修改成功\"}";
		}catch(Exception e) {
			e.printStackTrace();
			return	"{\"success\":\"false\",\"message\":\"商品修改失败\"}";
		}
	}
}
