package com.ccb.portal.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ccb.portal.dao.credit.CreditParamMapper;
import com.ccb.portal.dao.credit.CreditUserinfMapper;
import com.ccb.portal.entity.*;
import com.ccb.portal.entity.Credit.*;
import com.ccb.portal.util.HttpClientUtils;
import com.ccb.portal.util.JsonUtil;
import com.ccb.portal.util.SdkUtil;
import com.ccb.portal.vo.Const;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccb.portal.dao.credit.YunnanCreditMapper;
import com.ccb.portal.service.YunnanCreditService;

@Service
public class YunnanCreditServiceImpl implements YunnanCreditService{
	private static Logger logger= LoggerFactory.getLogger(YunnanCreditServiceImpl.class);
	@Autowired
	YunnanCreditMapper yunnanCreditMapper;
	@Autowired
	CreditParamMapper creditParamMapper;
	@Autowired
	ConfigConstant configConstant;
	@Autowired
	CreditUserinfMapper creditUserinfMapper;
	@Override
	public ResponseCredit getYunNanCreditByCstId(Map<String, String> map) throws Exception {
		JSONObject jsonObject=JSONObject.fromObject(map);
//		System.out.println(configConstant.getGrade());
		String response= HttpClientUtils.doPost(configConstant.getGrade(),jsonObject);
//		logger.info(configConstant.getGrade()+"--------------  responseJson-------------------"+response);
		if(StringUtils.isNotBlank(response)){
			ResponseCredit yunnanCredit=(ResponseCredit) JsonUtil.jsontobean(response, ResponseCredit.class, ResponseDmsn.class,"dmsn_grplist");
			if(yunnanCredit!=null){
				String model_type=yunnanCredit.getModel_type();
				String credit_rating_level=yunnanCredit.getCredit_rating_level();
				String modeltype=creditParamMapper.getModelType(model_type);
				String ratinglevel=creditParamMapper.getRatingLevel(credit_rating_level);
				if(StringUtils.isNotBlank(modeltype)&&StringUtils.isNotBlank(ratinglevel)){
					yunnanCredit.setModel_type(modeltype);
					yunnanCredit.setCredit_rating_level(ratinglevel);
				}
				if(yunnanCredit.getDmsn_grplist().size()>0){
					for(ResponseDmsn responseCredit:yunnanCredit.getDmsn_grplist()){
						String dmsn_type=responseCredit.getDmsn_type();
						String dmsntype=creditParamMapper.getDmsnType(dmsn_type);
						if(StringUtils.isNotBlank(dmsntype)){
							responseCredit.setDmsn_type(dmsntype);
						}
					}
				}
			}
			return  yunnanCredit;
		}else{
			return null;
		}
	}

	@Override
	public ResponsePersonCredit getPersonCreditByCstId(Map map) throws Exception {
		JSONObject jsonObject=JSONObject.fromObject(map);
		String response= HttpClientUtils.doPost(configConstant.getPgrade(),jsonObject);
//		logger.info(configConstant.getPgrade()+"--------------  responseJson-------------------"+response);
		if(StringUtils.isNotBlank(response)){
			ResponsePersonCredit rep=(ResponsePersonCredit) JsonUtil.fastjsonTobean(response,ResponsePersonCredit.class);
			if(rep.getScsInd().equals("1")){//1查询到数据0没查到
				JSONObject jsonObject1=JSONObject.fromObject(rep.getAgncBnkVchsonMRpCntnt());
				Set<String> set=jsonObject1.keySet();
				List<CreditDmsn>list=new ArrayList<>();
				for(String str:set){
					CreditDmsn cm=new CreditDmsn();
					cm.setDmsn_type(str);
					cm.setDmsn_value(jsonObject1.get(str).toString());
					list.add(cm);
				}
				rep.setDmsn_grplist(list);
				return rep;
			}else{
				return null;
			}
		}
		return null;
	}

	@Override
	public JSONObject checkCompanyInf(CreditUserinf cr) throws Exception {
		//模拟数据
		JSONObject jsonObject=Const.getJsonob(configConstant.getIFSPVldEntpInf());
		jsonObject.put("Usr_ID",cr.getUserNum());//"ZF0010028863I"
		jsonObject.put("Unn_Soc_Cr_Cd",cr.getUsco());//91630100310886716T
		jsonObject.put("LglRprs_Nm",cr.getLegalPerson());//张四
        jsonObject.put("Lgl_Rprs_IDNo",cr.getIdcard());//610323199109036858
		JSONObject js=new JSONObject();
		js.put("Data",jsonObject);
		String serviceid=configConstant.getIFSPVldEntpInf();

			logger.info("验证企业信息发送json["+js.toString()+"]"+"serviceid["+serviceid+"]");
			String res= SdkUtil.callByJson(serviceid,js.toString());
			JSONObject jb=JSONObject.fromObject(res);
			logger.info("验证企业信息返回json["+jb.toString()+"]");
			String resultdesc= JSONObject.fromObject(jb.get("Head")).get("Txn_Rsp_Inf").toString();
			JSONObject jsonObject1=new JSONObject();
			if(resultdesc.equals(Const.SUCCESS)){
				String Mbsh_Ctfn_StCd=JSONObject.fromObject(jb.get("Data")).get("Mbsh_Ctfn_StCd").toString();//00未认证01认证通过02认证中
				String Cst_No=JSONObject.fromObject(jb.get("Data")).get("Cst_No").toString();//企业客户号
				cr.setCstNo(Cst_No);
				creditUserinfMapper.updateByPrimaryKey(cr);
				if(Mbsh_Ctfn_StCd.equals(Const.COMPANYPASS)){
					jsonObject1.put("Txn_Rsp_Inf",resultdesc);
					jsonObject1.put("Mbsh_Ctfn_StCd", Mbsh_Ctfn_StCd);
				}else {
					jsonObject1.put("Txn_Rsp_Inf", Const.ERROR);
					jsonObject1.put("Mbsh_Ctfn_StCd", resultdesc);
				}
				return jsonObject1;
			}else{
				jsonObject1.put("Txn_Rsp_Inf",Const.ERROR);
				jsonObject1.put("Mbsh_Ctfn_StCd", resultdesc);
				return jsonObject1;
			}

	}

	@Override
	public JSONObject checkCompanyPrepose(CreditUserinf cr) throws Exception {
		//模拟数据
		JSONObject jsonObject=Const.getJsonob(configConstant.getIFSPEntpAhnFrtVerf());
		jsonObject.put("Usr_ID",cr.getUserNum());//"ZF0010028863I"
		jsonObject.put("Unn_Soc_Cr_Cd",cr.getUsco());//91630100310886716T
		jsonObject.put("Cst_No",cr.getCstNo());//
		JSONObject js=new JSONObject();
		js.put("Data",jsonObject);
		String serviceid=configConstant.getIFSPEntpAhnFrtVerf();

			logger.info("企业授权前置校验发送json["+js.toString()+"]"+"serviceid["+serviceid+"]");
			String res= SdkUtil.callByJson(serviceid,js.toString());
			JSONObject jb=JSONObject.fromObject(res);
			logger.info("企业授权前置校验返回json["+jb.toString()+"]");
			String resultdesc= JSONObject.fromObject(jb.get("Head")).get("Txn_Rsp_Inf").toString();
			JSONObject jsonObject1=new JSONObject();
			if(resultdesc.equals(Const.SUCCESS)){
				String resultcode=JSONObject.fromObject(jb.get("Data")).get("resultcode").toString();//000000通过
//				String Cst_No=JSONObject.fromObject(jb.get("Data")).get("Cst_No").toString();//企业客户号
//				cr.setCstNo(Cst_No);
//				creditUserinfMapper.updateByPrimaryKey(cr);
				if(resultcode.equals(Const.COMPANYPREPOSE)){
					jsonObject1.put("Txn_Rsp_Inf",resultdesc);
					jsonObject1.put("Mbsh_Ctfn_StCd", resultcode);
				}else {
					jsonObject1.put("Txn_Rsp_Inf", Const.ERROR);
					jsonObject1.put("Mbsh_Ctfn_StCd",resultdesc);
				}
				return jsonObject1;
			}else{
				String resultcode=JSONObject.fromObject(jb.get("Data")).get("resultcode").toString();
				if(resultcode.equals("100065")){//当前企业不为建行既有客户，可发起会审或预约开户
					jsonObject1.put("Txn_Rsp_Inf",Const.SUCCESS);
					jsonObject1.put("Mbsh_Ctfn_StCd", resultcode);
				}else {
					jsonObject1.put("Txn_Rsp_Inf", Const.ERROR);
					jsonObject1.put("Mbsh_Ctfn_StCd", resultdesc);
				}
				return jsonObject1;
			}

	}

	@Override
	public JSONObject partnerInf(CreditUserinf cr) throws Exception {
		JSONObject jsonObject=Const.getJsonob(configConstant.getIFSPShrhInfQry());
		jsonObject.put("Usr_ID",cr.getUserNum());//"ZF0010028863I"
		jsonObject.put("Unn_Soc_Cr_Cd",cr.getUsco());//91630100310886716T
		jsonObject.put("Cst_No",cr.getCstNo());//
		JSONObject js=new JSONObject();
		js.put("Data",jsonObject);
		String serviceid=configConstant.getIFSPShrhInfQry();
			logger.info("股东信息查询发送json["+js.toString()+"]"+"serviceid["+serviceid+"]");
			String res= SdkUtil.callByJson(serviceid,js.toString());
			JSONObject jb=JSONObject.fromObject(res);
			logger.info("股东信息查询返回json["+jb.toString()+"]");
			String resultdesc= JSONObject.fromObject(jb.get("Head")).get("Txn_Rsp_Inf").toString();
			JSONObject jsonObject1=new JSONObject();
			if(resultdesc.equals(Const.SUCCESS)){
				String resultcode=JSONObject.fromObject(jb.get("Data")).get("resultcode").toString();//000000通过
//				String Cst_No=JSONObject.fromObject(jb.get("Data")).get("Cst_No").toString();//企业客户号
//				cr.setCstNo(Cst_No);
//				creditUserinfMapper.updateByPrimaryKey(cr);
				if(resultcode.equals(Const.COMPANYPREPOSE)){
					String SkholderInf=JSONObject.fromObject(jb.get("Data")).get("SkholderInf").toString();//股东信息
					JSONArray jsonArray=JSONArray.fromObject(SkholderInf);
					jsonObject1.put("Txn_Rsp_Inf",resultdesc);
					jsonObject1.put("EntNm", JSONObject.fromObject(jb.get("Data")).get("EntNm").toString());
					jsonObject1.put("SkholderInf", jsonArray.toString());//
				}else {
					jsonObject1.put("Txn_Rsp_Inf", Const.ERROR);
					jsonObject1.put("SkholderInf", resultdesc);
				}
				return jsonObject1;
			}else{
				jsonObject1.put("Txn_Rsp_Inf",Const.ERROR);
				jsonObject1.put("SkholderInf", resultdesc);
				return jsonObject1;
			}

	}

	@Override
	public JSONObject autApplic(CreditUserinf cr,JSONArray SkholderInfArr) throws Exception {
		JSONObject jsonObject=Const.getJsonob(configConstant.getIFSPAhnAprvAply());
		jsonObject.put("Usr_ID",cr.getUserNum());//"ZF0010028863I"
		jsonObject.put("Unn_Soc_Cr_Cd",cr.getUsco());//91630100310886716T
		jsonObject.put("Cst_No",cr.getCstNo());//
		jsonObject.put("APly_Psn_Crdt_No",cr.getIdcard());
		jsonObject.put("Apl_Psn_Nm",cr.getLegalPerson());
		JSONObject js=new JSONObject();
//		JSONArray jsonArray=JSONArray.fromObject(SkholderInfArr);
		jsonObject.put("SkholderInfArr",SkholderInfArr);
		js.put("Data",jsonObject);
		String serviceid=configConstant.getIFSPAhnAprvAply();

			logger.info("授权会审申请json["+js.toString()+"]"+"serviceid["+serviceid+"]");
			String res= SdkUtil.callByJson(serviceid,js.toString());
			JSONObject jb=JSONObject.fromObject(res);
			logger.info("授权会审申请返回json["+jb.toString()+"]");
			String resultdesc= JSONObject.fromObject(jb.get("Head")).get("Txn_Rsp_Inf").toString();
			JSONObject jsonObject1=new JSONObject();
			if(resultdesc.equals(Const.SUCCESS)){
				String resultcode=JSONObject.fromObject(jb.get("Data")).get("resultcode").toString();//000000通过
//				String Cst_No=JSONObject.fromObject(jb.get("Data")).get("Cst_No").toString();//企业客户号
//				cr.setCstNo(Cst_No);
//				creditUserinfMapper.updateByPrimaryKey(cr);
				if(resultcode.equals(Const.COMPANYPREPOSE)){
					jsonObject1.put("Txn_Rsp_Inf",resultdesc);
					jsonObject1.put("SkholderInf", resultcode);//
				}else {
					jsonObject1.put("Txn_Rsp_Inf", Const.ERROR);
					jsonObject1.put("SkholderInf", resultdesc);
				}
				return jsonObject1;
			}else{
				jsonObject1.put("Txn_Rsp_Inf",Const.ERROR);
				jsonObject1.put("SkholderInf", resultdesc);
				return jsonObject1;
			}

	}

	@Override
	public JSONObject autApplicList(CreditUserinf cr) throws Exception {
		JSONObject jsonObject=Const.getJsonob(configConstant.getIFSPAhnAprvPrgrsListEnqr());
		jsonObject.put("MblPh_No",cr.getMoble());//"ZF0010028863I"
		JSONObject js=new JSONObject();
		js.put("Data",jsonObject);
		String serviceid=configConstant.getIFSPAhnAprvPrgrsListEnqr();

		logger.info("授权会审进度查询json["+js.toString()+"]"+"serviceid["+serviceid+"]");
		String res= SdkUtil.callByJson(serviceid,js.toString());
		JSONObject jb=JSONObject.fromObject(res);
		logger.info("授权会审进度查询json["+jb.toString()+"]");
		String resultdesc= JSONObject.fromObject(jb.get("Head")).get("Txn_Rsp_Inf").toString();
		JSONObject jsonObject1=new JSONObject();
		if(resultdesc.equals(Const.SUCCESS)){
			String resultcode=JSONObject.fromObject(jb.get("Data")).get("resultcode").toString();//000000通过
			String OrderList=JSONObject.fromObject(jb.get("Data")).get("OrderList").toString();
			if(resultcode.equals(Const.COMPANYPREPOSE)){
				jsonObject1.put("Txn_Rsp_Inf",resultdesc);
				jsonObject1.put("OrderList", OrderList);//
			}else {
				jsonObject1.put("Txn_Rsp_Inf", Const.ERROR);
				jsonObject1.put("OrderList", resultdesc);
			}
			return jsonObject1;
		}else{
			jsonObject1.put("Txn_Rsp_Inf",Const.ERROR);
			jsonObject1.put("OrderList", resultdesc);
			return jsonObject1;
		}
	}

}
