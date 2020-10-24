package com.ccb.portal.service;

import com.ccb.portal.entity.Credit.CreditDmsn;

import java.util.List;
import java.util.Map;

public interface CreditDmsnService {

	public List<CreditDmsn> getCreditDmsnByCsID(Map<String, Object> map)throws Exception;
	
}
