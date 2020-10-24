package com.ccb.portal.dao.credit;

import java.util.List;
import java.util.Map;

import com.ccb.portal.entity.Credit.CreditDmsn;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CreditDmsnMapper {
    int deleteByPrimaryKey(Short id);

    int insert(CreditDmsn record);

    int insertSelective(CreditDmsn record);

    CreditDmsn selectByPrimaryKey(Short id);

    int updateByPrimaryKeySelective(CreditDmsn record);

    int updateByPrimaryKey(CreditDmsn record);

	public List<CreditDmsn> getCreditDmsnByCsID(Map<String, Object> map)throws Exception;
}