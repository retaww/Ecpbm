package com.ecpbm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ecpbm.pojo.Pager;
import com.ecpbm.pojo.ProductInfo;
import com.ecpbm.service.ProductInfoService;

@Controller
@RequestMapping("/productinfo")
public class ProductInfoController {
	@Autowired
	private ProductInfoService productInfoService;
	@RequestMapping("/list")
	@ResponseBody
	public Map<String,Object> list(@Param("page")Integer page,@Param("rows")Integer rows,ProductInfo productInfo){
		Pager pager = new Pager();
		pager.setCurPage(page);
		pager.setPerPageRows(rows);
		//创建params对象，封装查询条件
		Map<String , Object> params = new HashMap<String, Object>();
		params.put("productInfo", productInfo);
		//获取满足条件的商品数
		int totalCount = productInfoService.count(params);
		//获取满足条件的商品列表
		List<ProductInfo> productInfos = productInfoService.findProductInfo(productInfo, pager);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total", totalCount);
		result.put("rows", productInfos);
		return result;
	}
	
	@RequestMapping(value = "/addProduct",produces = "text/html;charset=UTF-8")
	@ResponseBody 
	public String addProduct(ProductInfo pi ,HttpServletRequest request,ModelMap model) {
		productInfoService.addProductInfo(pi);
		return "{\"success\":\"true\",\"message\":\"商品添加成功\"}";
	}
	
	@RequestMapping(value = "/deleteProduct",produces = "text/html;charset=UTF-8")
	@ResponseBody 
	public String deleteProduct(@RequestParam("id") String id,@RequestParam("flag") String flag) {
		String str = "";
		try {
			productInfoService.modifyStatus(id.substring(0,id.length()-1), Integer.parseInt(flag));
			str = "{\"success\":\"true\",\"message\":\"下架成功\"}";
		}catch(Exception e) {
			e.printStackTrace();
			str = "{\"success\":\"false\",\"message\":\"下架失败\"}";
		}
		return str;
	}
	
	@RequestMapping(value = "/updateProduct",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updateProduct(ProductInfo pi,HttpServletRequest request,ModelMap model) {
		try {
			productInfoService.modifyProductInfo(pi);
			return "{\"success\":\"true\",\"message\":\"商品修改成功\"}";
		}catch(Exception e) {
			e.printStackTrace();
			return	"{\"success\":\"false\",\"message\":\"商品修改失败\"}";
		}
	}
	
	@ResponseBody
	@RequestMapping("getOnSaleProduct")
	public List<ProductInfo> getOnSaleProduct(){
		List<ProductInfo> list = productInfoService.getOnSaleProduct();
		return list;
	}
	
	@RequestMapping("/getPriceById")
	@ResponseBody
	public String getPriceById(@RequestParam("pid")String pid) {
		if(pid!=null&&!"".equals(pid)) {
			ProductInfo pi = productInfoService.getProductById(Integer.parseInt(pid));
			return pi.getPrice()+"";
		}else {
			return "";
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
