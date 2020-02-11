package com.ecpbm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ecpbm.dao.ProductInfoDao;
import com.ecpbm.pojo.Pager;
import com.ecpbm.pojo.ProductInfo;
import com.ecpbm.service.ProductInfoService;

@Service("productInfoService")
@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
public class ProductInfoServiceImpl implements ProductInfoService {

	@Autowired
	private ProductInfoDao productInfoDao;
	public List<ProductInfo> findProductInfo(ProductInfo pi, Pager pager) {
		// TODO Auto-generated method stub
		Map<String , Object> params = new HashMap<String, Object>();
		params.put("productInfo", pi);
		int recordCount = productInfoDao.count(params);
		pager.setRowCount(recordCount);
		if(recordCount>0) {
			params.put("pager", pager);
		}
		return productInfoDao.selectByPage(params);
	}

	public Integer count(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return productInfoDao.count(params);
	}

	public void addProductInfo(ProductInfo pi) {
		// TODO Auto-generated method stub
		productInfoDao.save(pi);
	}

	public void modifyProductInfo(ProductInfo pi) {
		// TODO Auto-generated method stub
		productInfoDao.edit(pi);
	}

	public void modifyStatus(String ids, int flag) {
		// TODO Auto-generated method stub
		productInfoDao.updateStatus(ids, flag);
	}

	public List<ProductInfo> getOnSaleProduct() {
		// TODO Auto-generated method stub
		return productInfoDao.getonSaleProduct();
	}

	public ProductInfo getProductById(int id) {
		// TODO Auto-generated method stub
		return productInfoDao.getProductInfoById(id);
	}

}
