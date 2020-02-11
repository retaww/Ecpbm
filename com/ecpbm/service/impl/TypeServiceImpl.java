package com.ecpbm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ecpbm.dao.TypeDao;
import com.ecpbm.pojo.Type;
import com.ecpbm.service.TypeService;

@Service("typeService")
@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
public class TypeServiceImpl implements TypeService{

	@Autowired
	private TypeDao typeDao;
	public List<Type> getAll() {
		// TODO Auto-generated method stub
		return typeDao.selectAll();
	}

	public int addType(Type type) {
		// TODO Auto-generated method stub
		return typeDao.add(type);
	}

	public void updateType(Type type) {
		// TODO Auto-generated method stub
		typeDao.update(type);
	}
	
}
