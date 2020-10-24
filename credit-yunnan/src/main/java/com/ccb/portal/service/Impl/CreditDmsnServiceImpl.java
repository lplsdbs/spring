package com.ccb.portal.service.Impl;

import java.util.List;
import java.util.Map;

import com.ccb.portal.entity.Credit.CreditDmsn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccb.portal.dao.credit.CreditDmsnMapper;
import com.ccb.portal.service.CreditDmsnService;
@Service
public class CreditDmsnServiceImpl implements CreditDmsnService{
	@Autowired
	private CreditDmsnMapper creditDmsnMapper;

	@Override
	public List<CreditDmsn> getCreditDmsnByCsID(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return creditDmsnMapper.getCreditDmsnByCsID(map);
	}
}
