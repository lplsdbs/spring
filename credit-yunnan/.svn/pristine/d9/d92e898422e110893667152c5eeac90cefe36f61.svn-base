package com.ccb.portal.service.Impl;

import com.ccb.portal.dao.credit.CreditLimitInfMapper;
import com.ccb.portal.entity.Credit.CreditLimitInf;
import com.ccb.portal.entity.Credit.CreditUserinf;
import com.ccb.portal.service.CreditLimitInfService;
import com.ccb.portal.util.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class CreditLimitInfServiceImpl implements CreditLimitInfService {
    @Autowired
    CreditLimitInfMapper creditLimitInfMapper;

    @Override
    @Transactional
    public void insertOrUpdate(CreditLimitInf creditLimitInf) throws Exception {
        for(CreditLimitInf cre:creditLimitInf.getLimitList()){
        CreditLimitInf creditLimitInf1=creditLimitInfMapper.selectByloanNum(cre.getLoanNum());
        if(creditLimitInf1==null){
            cre.setId(UuidUtil.get32UUID());
            cre.setCreateDate(new Date());
            creditLimitInfMapper.insertSelective(cre);
        }else{
            cre.setId(creditLimitInf1.getId());
            cre.setCreateDate(creditLimitInf1.getCreateDate());
            creditLimitInfMapper.updateByPrimaryKeySelective(cre);
        }
        }
    }

    @Override
    public List<CreditLimitInf> limit(CreditUserinf cr) throws Exception {
        return creditLimitInfMapper.selectByUserNum(cr);
    }
}
