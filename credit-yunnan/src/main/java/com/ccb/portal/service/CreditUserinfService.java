package com.ccb.portal.service;

import com.ccb.portal.entity.Credit.CreditUserinf;

public interface CreditUserinfService {
    CreditUserinf selectByPrimaryKey(String userId)throws Exception;

    void insertSelective(CreditUserinf cin)throws Exception;

    void update(CreditUserinf cr)throws Exception;
}
