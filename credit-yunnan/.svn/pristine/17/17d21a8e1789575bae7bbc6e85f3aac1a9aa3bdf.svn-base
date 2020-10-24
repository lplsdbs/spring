package com.ccb.portal.dao.credit;

import com.ccb.portal.entity.Credit.CreditLimitInf;
import com.ccb.portal.entity.Credit.CreditUserinf;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CreditLimitInfMapper {
    int deleteByPrimaryKey(String id);

    int insert(CreditLimitInf record);

    int insertSelective(CreditLimitInf record);

    CreditLimitInf selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CreditLimitInf record);

    int updateByPrimaryKey(CreditLimitInf record);

    CreditLimitInf selectByloanNum(String loanNum);

    void updateByLoanNum(CreditLimitInf creditLimitInf);

    List<CreditLimitInf> selectByUserNum(CreditUserinf cr);
}