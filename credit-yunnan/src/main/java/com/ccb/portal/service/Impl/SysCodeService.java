package com.ccb.portal.service.Impl;

import java.util.List;

import com.ccb.portal.entity.Credit.SysCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccb.portal.dao.credit.SysCodeDao;

@Service
public class SysCodeService {

	@Autowired
	private SysCodeDao sysCodeDao;
	
	public List<SysCode> getSysCodeByKeys(List<String> list){
		
		return this.sysCodeDao.getSysCodeByKeys(list);
	}
}
