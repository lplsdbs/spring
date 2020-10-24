package com.ccb.portal.service.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ccb.portal.entity.Credit.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccb.portal.dao.credit.RoleDao;

@Service
public class RoleService{

	@Autowired
	private RoleDao roleDao;
	
	/**
	 * 获取角色列表
	 * @param roleInfo 查询参数
	 * @return 角色列表
	 */
	public List<Role> getRoleList(String roleInfo){
		Map<String, String> map = new HashMap<>();
		map.put("roleInfo", roleInfo);
		List<Role> list = roleDao.getRoleList(map);
		return list;
	}
}
