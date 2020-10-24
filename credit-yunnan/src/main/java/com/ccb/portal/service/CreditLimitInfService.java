package com.ccb.portal.service;

import com.ccb.portal.entity.Credit.CreditLimitInf;
import com.ccb.portal.entity.Credit.CreditUserinf;

import java.util.List;

public interface CreditLimitInfService {
    void insertOrUpdate(CreditLimitInf creditLimitInf)throws Exception;

    List<CreditLimitInf> limit(CreditUserinf cr)throws Exception;
}
