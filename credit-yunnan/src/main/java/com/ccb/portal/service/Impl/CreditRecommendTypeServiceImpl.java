package com.ccb.portal.service.Impl;

import com.ccb.portal.dao.credit.CreditRecommendTypeMapper;
import com.ccb.portal.entity.Credit.CreditRecommendType;
import com.ccb.portal.service.CreditRecommendTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CreditRecommendTypeServiceImpl  implements CreditRecommendTypeService {
    @Autowired
    CreditRecommendTypeMapper creditRecommendType;

    @Override
    public List<CreditRecommendType> getCreditLeveByScore(Map<String, String> creditValue) throws Exception {
        return creditRecommendType.getCreditLeveByScore(creditValue);
//        return null;
    }
}
