package com.ccb.portal.service;

import java.util.Map;

import com.ccb.portal.entity.Credit.CreditUserinf;
import com.ccb.portal.entity.Credit.ResponseCredit;
import com.ccb.portal.entity.Credit.ResponsePersonCredit;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface YunnanCreditService {

	public ResponseCredit getYunNanCreditByCstId(Map<String, String> map)throws Exception;

	public ResponsePersonCredit getPersonCreditByCstId(Map map)throws Exception;

    public JSONObject checkCompanyInf(CreditUserinf cr)throws Exception;

	JSONObject checkCompanyPrepose(CreditUserinf cr)throws Exception;

	JSONObject partnerInf(CreditUserinf cr)throws Exception;

	JSONObject autApplic(CreditUserinf cr, JSONArray SkholderInfArr)throws Exception;

	JSONObject autApplicList(CreditUserinf cr)throws Exception;
}
