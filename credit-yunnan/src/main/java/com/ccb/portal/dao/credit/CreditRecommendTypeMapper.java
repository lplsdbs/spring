package com.ccb.portal.dao.credit;

import com.ccb.portal.entity.Credit.CreditRecommendType;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
@Mapper
public interface CreditRecommendTypeMapper {
    int deleteByPrimaryKey(BigDecimal id);

    int insert(CreditRecommendType record);

    int insertSelective(CreditRecommendType record);

    CreditRecommendType selectByPrimaryKey(BigDecimal id);

    int updateByPrimaryKeySelective(CreditRecommendType record);

    int updateByPrimaryKey(CreditRecommendType record);

    public  List<CreditRecommendType> getCreditLeveByScore(Map<String,String> creditValue)throws Exception;
}