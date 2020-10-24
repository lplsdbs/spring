package com.ccb.portal.service.Impl;

import com.ccb.portal.dao.credit.CreditUserinfMapper;
import com.ccb.portal.entity.Credit.CreditUserinf;
import com.ccb.portal.service.CreditUserinfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreditUserinfServiceImpl implements CreditUserinfService {
    @Autowired
    CreditUserinfMapper creditUserinfMapper;
    @Override
    public CreditUserinf selectByPrimaryKey(String userId) throws Exception {
        return creditUserinfMapper.selectByPrimaryKey(userId);
    }

    @Override
    @Transactional
    public void insertSelective(CreditUserinf cin) throws Exception {
        creditUserinfMapper.insertSelective(cin);
    }

    @Override
    @Transactional
    public void update(CreditUserinf cr) throws Exception {
        creditUserinfMapper.updateByPrimaryKey(cr);
    }
}
