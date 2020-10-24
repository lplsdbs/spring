package com.ccb.portal.controller;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.ccb.portal.controller.base.BaseController;
import com.ccb.portal.entity.*;
import com.ccb.portal.entity.Credit.*;
import com.ccb.portal.entity.publicity.Index;
import com.ccb.portal.entity.publicity.XyynHhmdInfo;
import com.ccb.portal.entity.publicity.XyynXzcfInfo;
import com.ccb.portal.entity.publicity.XyynXzxkInfo;
import com.ccb.portal.service.*;
import com.ccb.portal.util.*;
import com.ccb.portal.vo.Const;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
//import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import com.ccb.portal.vo.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

//通过自定义的Request，json格式的参数类型都转换为key-value形式，请不要使用@RequestBody
@Api(tags = { "信用分值" }, value = "信用")
@RestController
@RequestMapping("/credit")
public class CreditDmsnController extends BaseController{
	@Autowired
	private CreditDmsnService creditDmsnService;
	@Autowired
	private CreditRecommendTypeService creditRecommendTypeService;
	@Autowired
	private YunnanCreditService yunnanCreditService;
	@Autowired
	XyynHhmdInfoService xyynHhmdInfoService;
	@Autowired
	XyynXzcfInfoService xyynXzcfInfoService;
	@Autowired
	XyynXzxkInfoService xyynXzxkInfoService;
	@Autowired
	CreditLimitInfService creditLimitInfService;

//	private SimpleDateFormat  simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
//
//	protected void initBinder(WebDataBinder binder){
//		binder.registerCustomEditor(Date.class,new CustomDateEditor(simpleDateFormat,true));
//	}

//	@Autowired
//	private ConnectionUtil connection;

	@ApiOperation(value="行政许可",notes="行政许可")
	@RequestMapping(value="/xyynXzxkInfo",produces="application/json")
	public Object xyynXzxkInfo(@RequestBody(required = false) Page page){
		Tidings< List<Index>> tidings=new Tidings<>();
		if(page==null){
			page=new Page();
		}
/*
*  {
    "currentPage":1,
    "pd": {"keywords":""}

  }
*
* */
		try {
			List<Index> list=xyynXzxkInfoService.xyynXzxkInfolistPage(page);
			tidings.setStatus(Const.SUCCESS);
			tidings.setT(list).setPage(page);
			tidings.setMsg("查询成功");
		} catch (Exception e) {
			e.printStackTrace();
			tidings.setStatus(Const.ERROR);
			tidings.setMsg("查询失败");
		}
		return tidings;
	}
	@ApiOperation(value="行政处罚",notes="行政处罚")
	@RequestMapping(value="/xyynXzcfInfo",produces="application/json")
	public Object  XyynXzcfInfo(@RequestBody (required = false)Page page){
		Tidings< List<Index>>tidings=new Tidings<>();
		if(page==null){
			page=new Page();
		}
		try {
			List<Index> list=xyynXzcfInfoService.XyynXzcfInfolistPage(page);
			tidings.setStatus(Const.SUCCESS);
			tidings.setT(list).setPage(page);
			tidings.setMsg("查询成功");
		} catch (Exception e) {
			e.printStackTrace();
			tidings.setStatus(Const.ERROR);
			tidings.setMsg("查询失败");
		}
		return tidings;
	}
	@ApiOperation(value="红名单",notes="红名单")
	@RequestMapping(value="/xyynHhmdRedInfo",produces="application/json")
	public Object  XyynHhmdRedInfo(@RequestBody(required = false) Page page){
		Tidings< List<Index>>tidings=new Tidings<>();
		if(page==null){
			page=new Page();
		}
		try {
			List<Index> list=xyynHhmdInfoService.XyynHhmdRedInfolistPage(page);
			tidings.setStatus(Const.SUCCESS);
			tidings.setT(list).setPage(page);
			tidings.setMsg("查询成功");
		} catch (Exception e) {
			e.printStackTrace();
			tidings.setStatus(Const.ERROR);
			tidings.setMsg("查询失败");
		}
		return tidings;
	}
	@ApiOperation(value="黑名单",notes="黑名单")
	@RequestMapping(value="/xyynHhmdBlaInfo",produces="application/json")
	public Object  XyynHhmdBlaInfo(@RequestBody(required = false) Page page){
		Tidings< List<Index>>tidings=new Tidings<>();
		if(page==null){
			page=new Page();
		}
		try {
			List<Index> list=xyynHhmdInfoService.XyynHhmdBlaInfolistPage(page);
			tidings.setStatus(Const.SUCCESS);
			tidings.setT(list).setPage(page);
			tidings.setMsg("查询成功");
		} catch (Exception e) {
			e.printStackTrace();
			tidings.setStatus(Const.ERROR);
			tidings.setMsg("查询失败");
		}
		return tidings;
	}

	@ApiOperation(value="详情",notes="详情")
	@RequestMapping(value="/xyynDetail",produces="application/json")
	@ResponseBody
	public Object XyynDetail(@RequestBody(required = false) Index index){
		Tidings tidings=new Tidings ();
		try {
			if(index==null||StringUtils.isBlank(index.getId())||StringUtils.isBlank(index.getStatus())){
				tidings.setStatus(Const.ERROR);
				tidings.setMsg("参数为空");
				return tidings;
			}
			if(index.getStatus().equals("2")){
				XyynXzcfInfo xyynXzcfInfo=xyynXzcfInfoService.getByid(index.getId());
				tidings.setStatus(Const.SUCCESS);
				tidings.setT(xyynXzcfInfo);
			}else if(index.getStatus().equals("1")){
				XyynXzxkInfo xyynXzxkInfo=xyynXzxkInfoService.getByid(index.getId());
				tidings.setStatus(Const.SUCCESS);
				tidings.setT(xyynXzxkInfo);
			}else if(index.getStatus().equals("3")||index.getStatus().equals("4")){
				XyynHhmdInfo xyynHhmdInfo=xyynHhmdInfoService.getByIdStatus(index);
				tidings.setStatus(Const.SUCCESS);
				tidings.setT(xyynHhmdInfo);
			}
			tidings.setMsg("查询成功");
		} catch (Exception e) {
			e.printStackTrace();
			tidings.setStatus(Const.ERROR);
			tidings.setMsg("查询失败");
		}
		return tidings;
	}
	@ApiOperation(value="首页搜索",notes="首页搜索")
	@RequestMapping(value="/xyynIndex",produces="application/json")
	public Object XyynIndex(@RequestBody(required = false) Page page){
		Tidings tidings=new Tidings ();
		if(page==null){
			page=new Page();
		}
		try {
			List<Index>list=xyynXzxkInfoService.getIndexlistPage(page);
			tidings.setT(list).setPage(page);
			tidings.setStatus(Const.SUCCESS);
			tidings.setMsg("查询成功");
		} catch (Exception e) {
			e.printStackTrace();
			tidings.setStatus(Const.ERROR);
			tidings.setMsg("查询失败");
		}
		return tidings;
	}
	@ApiOperation(value="获取企业信用得分",notes="获取企业信用得分")
	@RequestMapping(value="/getCreditScore",produces="application/json")
	public Result  getCreditScore(@RequestBody(required = false) Map<String,String>map1) throws Exception {
		if(map1==null){
			return ResultUtil.error(Const.ERROR,"参数为空");
		}
		String Cst_id=map1.get("Cst_id");
		String Cst_type=map1.get("Cst_type");
		String Cst_identity_type=map1.get("Cst_identity_type");
		String Cst_identity_id=map1.get("Cst_identity_id");
		 CreditUserinf cr=(CreditUserinf) this.getRequest().getAttribute("user");
		if(StringUtils.isBlank(Cst_id)){
			return ResultUtil.error(Const.ERROR,"参数Cst_id为空");
		}else if(StringUtils.isBlank(Cst_type)){
			return ResultUtil.error(Const.ERROR,"参数Cst_type为空");
		}else if(StringUtils.isBlank(Cst_identity_type)){
			return ResultUtil.error(Const.ERROR,"参数Cst_identity_type为空");
		}else if(StringUtils.isBlank(Cst_identity_id)){
			return ResultUtil.error(Const.ERROR,"参数Cst_identity_id为空");
		}else if(!Arrays.asList(Const.CSTTYPE).contains(Cst_type)){
		    return ResultUtil.error(Const.ERROR,"参数Cst_type为企业");
        }
		Map map=new HashMap();
		map.put("Cst_id",Cst_id);
		map.put("Cst_type",Cst_type);
		map.put("Cst_identity_type",Cst_identity_type);
		map.put("Cst_identity_id",Cst_identity_id);
		try {
			ResponseCredit yunnanCredit=yunnanCreditService.getYunNanCreditByCstId(map);
			if(null!=yunnanCredit){
                map.put("CreditValue",yunnanCredit.getCredit_value());
                List<CreditRecommendType>typelist=creditRecommendTypeService.getCreditLeveByScore(map);
                JSONObject jsonObject1=JSONObject.fromObject(yunnanCredit);
                jsonObject1.put("credit_recommend_type",new ObjectMapper().writeValueAsString(typelist));
                return ResultUtil.success(jsonObject1);
            }else{
			    return ResultUtil.success(yunnanCredit);
            }

		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(Const.ERROR,"查询失败");
		}
	}

	@ApiOperation(value="获取个人信用得分",notes="获取个人信用得分")
	@RequestMapping(value="/personCredit",produces="application/json")
	public Result  getPersonCreditScore(@RequestBody (required = false) Map<String,String>map1) {
		if(map1==null){
			return ResultUtil.error(Const.ERROR,"参数为空");
		}
		String Crdt_TpCd=map1.get("Crdt_TpCd");
		String Crdt_No=map1.get("Crdt_No");
		String Cst_Nm=map1.get("Cst_Nm");
		if(StringUtils.isBlank(Crdt_TpCd)){
			return ResultUtil.error(Const.ERROR,"参数Crdt_TpCd为空");
		}else if(StringUtils.isBlank(Crdt_No)){
			return ResultUtil.error(Const.ERROR,"参数Crdt_No为空");
		}else if(StringUtils.isBlank(Cst_Nm)){
			return ResultUtil.error(Const.ERROR,"参数Cst_Nm为空");
		}
//		else if(StringUtils.isBlank(MULTI_TENANCY_ID)){
//			return ResultUtil.error(Const.ERROR,"参数MULTI_TENANCY_ID为空");
//		}
		Map map=new HashMap();
		map.put("Crdt_TpCd",Crdt_TpCd);
		map.put("Crdt_No",Crdt_No);
		map.put("Cst_Nm",Cst_Nm);
		map.put("MULTI_TENANCY_ID","CN000");
		try {
			ResponsePersonCredit yunnanCredit=yunnanCreditService.getPersonCreditByCstId(map);
			PersonCredit personCredit=new PersonCredit();
			personCredit.setCrdt_No(yunnanCredit.getCrdt_No());
			personCredit.setCrdt_TpCd(yunnanCredit.getCrdt_TpCd());
			personCredit.setCst_Nm(yunnanCredit.getCst_Nm());
			personCredit.setRsk_Qot(yunnanCredit.getRsk_Qot());
			personCredit.setScor_Crd_Scor(yunnanCredit.getScor_Crd_Scor());
			personCredit.setScor_Rel_Lo_Dsc(yunnanCredit.getScor_Rel_Lo_Dsc());
			personCredit.setDmsn_grplist(yunnanCredit.getDmsn_grplist());
			if(null!=yunnanCredit){
				map.put("CreditValue",yunnanCredit.getScor_Crd_Scor());
				List<CreditRecommendType>typelist=creditRecommendTypeService.getCreditLeveByScore(map);
				JSONObject jsonObject2=JSONObject.fromObject(personCredit);
				jsonObject2.put("credit_recommend_type",new ObjectMapper().writeValueAsString(typelist));
				return ResultUtil.success(jsonObject2);
			}else{
				return ResultUtil.success(yunnanCredit);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(Const.ERROR,"查询失败");
		}
	}
	@ApiOperation(value="验证企业信息",notes="验证企业信息")
	@RequestMapping(value="/checkCompanyInf",produces="application/json")
	public Result checkCompanyInf(String Usr_ID,String Unn_Soc_Cr_Cd,String LglRprs_Nm,String Lgl_Rprs_IDNo){
		CreditUserinf cr=(CreditUserinf) this.getRequest().getAttribute("user");
		try {
			JSONObject obj=yunnanCreditService.checkCompanyInf(cr);
			return ResultUtil.success(obj);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(Const.ERROR,"验证失败");
		}
	}
	@ApiOperation(value="企业授权前置校验",notes="企业授权前置校验")
	@RequestMapping(value="/checkCompanyPrepose",produces="application/json")
	public Result checkCompanyPrepose(String Usr_ID,String Unn_Soc_Cr_Cd,String Cst_No){
		CreditUserinf cr=(CreditUserinf) this.getRequest().getAttribute("user");
		try {
			JSONObject obj=yunnanCreditService.checkCompanyPrepose(cr);
			return ResultUtil.success(obj);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(Const.ERROR,"前置验证失败");
		}
	}
	@ApiOperation(value="股东信息查询",notes="股东信息查询")
	@RequestMapping(value="/partnerInf",produces="application/json")
	public Result partnerInf(String Usr_ID,String Unn_Soc_Cr_Cd,String Cst_No){
		CreditUserinf cr=(CreditUserinf) this.getRequest().getAttribute("user");
		try {
			JSONObject obj=yunnanCreditService.partnerInf(cr);
			return ResultUtil.success(obj);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(Const.ERROR,"股东信息查询失败");
		}
	}

	@ApiOperation(value="授权会审申请",notes="授权会审申请")
	@RequestMapping(value="/autApplic",produces="application/json")
	public Result autApplic(@RequestBody (required = false) Map<String,Object>map ){
		CreditUserinf cr=(CreditUserinf) this.getRequest().getAttribute("user");
		if(map==null){
			return ResultUtil.error(Const.ERROR,"参数SkholderInf空");
		}
		JSONArray SkholderInfArr=JSONArray.fromObject(map.get("SkholderInf"));
		if(SkholderInfArr.size()==0){
			return ResultUtil.error(Const.ERROR,"参数SkholderInf空");
		}
		try {
			JSONObject obj=yunnanCreditService.autApplic(cr,SkholderInfArr);
			return ResultUtil.success(obj);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(Const.ERROR,"授权会审申请失败");
		}
	}

	@ApiOperation(value="授权会审进度查询",notes="授权会审进度查询")
	@RequestMapping(value="/autApplicList",produces="application/json")
	public Result autApplicList(){
		CreditUserinf cr=(CreditUserinf) this.getRequest().getAttribute("user");
		try {
			JSONObject obj=yunnanCreditService.autApplicList(cr);
			return ResultUtil.success(obj);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(Const.ERROR,"授权会审进度查询失败");
		}
	}
	@ApiOperation(value="额度返数",notes="额度返数")
	@RequestMapping(value="/limitBack",produces="application/json")
	public Result limitBack(@RequestBody (required = false) CreditLimitInf creditLimitInf ){
		Pattern pattern=Pattern.compile("[0-9]*");
		Long time=System.currentTimeMillis();
		System.out.println(time);
		if(creditLimitInf==null||creditLimitInf.getLimitList().size()==0){
			return ResultUtil.error(Const.ERROR,"parameter limitList may be null");
		}

		try {
			creditLimitInfService.insertOrUpdate(creditLimitInf);
			return ResultUtil.success(Const.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(Const.ERROR,"失败");
		}
	}

	@ApiOperation(value="额度查询",notes="额度查询")
	@RequestMapping(value="/limit",produces="application/json")
	public Result limit(){
		CreditUserinf cr=(CreditUserinf) this.getRequest().getAttribute("user");
		try {
			List<CreditLimitInf>limitInfs=creditLimitInfService.limit(cr);
			for(CreditLimitInf cred:limitInfs){
				cred.setLoanDate(DateUtil.LongToDate(cred.getLoanDate()));
			}
			return ResultUtil.success(limitInfs);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(Const.ERROR,"额度查询失败");
		}
	}












	@RequestMapping(value="/getCreditScoreDetial",produces="application/json")
	public Result  getCreditScoreDetial( @RequestBody Map<String,String>map1) {
		Map<String,String>map=new HashMap<String,String>();
		String Cst_id=map1.get("Cst_id");
		System.out.println("Cst_id "+Cst_id);
		if(StringUtils.isBlank(Cst_id)){
			return ResultUtil.error(Const.ERROR,"参数Cst_id为空");
		}
		map.put("Cst_id",Cst_id);
		try {
			ResponseCredit yunnanCredit=yunnanCreditService.getYunNanCreditByCstId(map);

			map.put("CreditValue","");
			List<CreditRecommendType>typelist=creditRecommendTypeService.getCreditLeveByScore(map);
//			JsonConfig jsonObject=new JsonConfig();
//			jsonObject.addIgnoreFieldAnnotation(JsonIgnore.class);
			String objectMapper=new ObjectMapper().writeValueAsString(yunnanCredit);
			JSONObject jsonObject1=JSONObject.fromObject(objectMapper);
			jsonObject1.remove("dmsn_grplist");
			jsonObject1.put("credit_recommend_type",new ObjectMapper().writeValueAsString(typelist));
			return ResultUtil.success(jsonObject1);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResultUtil.error(Const.ERROR,"查询异常");
		}


	}
	
}
