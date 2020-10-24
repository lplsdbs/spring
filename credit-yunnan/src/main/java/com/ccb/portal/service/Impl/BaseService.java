package com.ccb.portal.service.Impl;

import java.util.List;

import com.ccb.portal.dao.BaseDao;

public class BaseService<T> {
	
	private BaseDao<T> dao;
	
	protected BaseService(BaseDao<T> dao){
		this.dao = dao;
	}
	
	public List<T> query(T obj){
		return dao.query(obj);
	}
	
	public Integer add(T obj) {
		return dao.add(obj);
	}

	public Integer modi(T obj) {
		return dao.modi(obj);
	}

	public Integer del(Integer id) {
		return dao.del(id);
	}

	public T get(Integer id) {
		return dao.get(id);
	}

}
