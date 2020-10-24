package com.ccb.portal.interceptor;

import com.ccb.portal.entity.ConfigConstant;
import com.ccb.portal.entity.Credit.CreditUserinf;
import com.ccb.portal.service.CreditUserinfService;
import com.ccb.portal.util.HttpClientUtils;
import com.ccb.portal.util.JsonUtil;
import com.ccb.portal.util.SdkUtil;

import com.ccb.portal.vo.Const;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/*
* 页面传来临时token查询用户信息
* */
public class JwtInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private ConfigConstant configConstant;//使用configConstant获得config.propertise参数
    @Autowired
    private CreditUserinfService creditUserinfService;
    private static Logger logger= LoggerFactory.getLogger(JwtInterceptor.class);
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       logger.info("-------------custom interceptor into------------------");
        String authHeader = request.getHeader("Authorization");
        String reurl=request.getServerName()+":"+request.getServerPort()+request.getRequestURI();//请求的完整url
        logger.info("请求url["+reurl+"]");
        Map map1=request.getParameterMap();//
        //|| !authHeader.startsWith("Bearer:")
        logger.info("获取的token["+authHeader+"]");
        if (authHeader != null ) {
            //模拟登陆获取登陆token
            String url="http://128.196.221.92:8080/gsp/fsx01006";
            Map<String,String>map=new HashMap<>();
            map.put("tenant","530000000000");
            map.put("txn_itt_chnl_id","00");
            map.put("txn_itt_chnl_type","11");
            map.put("membername","wangrj");
            map.put("password","111111");
            map.put("logintype","0");
            try {
                Map<String,String>head=new HashMap<>();
                head.put("C-App-Id","GSP_APP_001");
                JSONObject jsonObject= JSONObject.fromObject(map);
//                System.out.println("req--"+jsonObject.toString());
//                System.out.println(new String(HttpClientUtils.Post(url,jsonObject.toString(),head)));
                String res=HttpClientUtils.Post(url,jsonObject.toString(),head);
                JSONObject js=JSONObject.fromObject(res);
                String tok=js.get("C-Response-Body")+"";
                JSONObject toke=JSONObject.fromObject(tok);
                System.out.println(toke);
                String token=toke.getString("Token");
                System.out.println(token);
                head.put("C-Dynamic-Password-Foruser",token);
                String urltem="http://128.196.221.88:8080/auth/crtAccessLinkForUserTmp";//获取临时token
//                System.out.println(HttpClientUtils.Post(urltem,null,head));
                JSONObject jsonObject1=JSONObject.fromObject(HttpClientUtils.Post(urltem,null,head));
                String temtofen=jsonObject1.get("C-Response-Body").toString();
                Map<String,String>usermap=new HashMap<>();
                //模拟结束以下将来要从页面来获取token去查询信息
                String usertemp=String.format(configConstant.getTokenToUser(),temtofen);//"http://128.196.221.88:8080/auth/getAccessLinkForUserTmpMsg?appPwd=123456&accessLink="+temtofen+"&C-App-Id=CYN_APP_001";//临时token获取用户信息
                String userinfjson=HttpClientUtils.Get(usertemp);
                logger.info("getAccessLinkForUserTmpMsg"+"-------["+userinfjson+"]");
                JSONObject jsonObject2=JSONObject.fromObject(userinfjson);
                if(jsonObject2.get("C-Response-Desc").equals("success")){
                    String resbody=jsonObject2.get("C-Response-Body").toString();
                    logger.info("用token获取的用户信息"+"-------["+resbody+"]");
                    CreditUserinf cin=(CreditUserinf) JsonUtil.fastjsonTobean(resbody,CreditUserinf.class);
                    logger.info("用token获取的用户信息javaben"+"-------["+cin+"]");
                    CreditUserinf cred=creditUserinfService.selectByPrimaryKey(cin.getUserId());
                    if(cred==null){
//                        creditUserinfMapper.insertSelective(cred);
                        JSONObject jsonObjectuser= Const.getJsonob(configConstant.getIFSPLnDfaltRgst());
//                        jsonObjectuser.put("Aply_Psn_MblPh_No",cin.getMoble());//手机号
//                        jsonObjectuser.put("Aply_Psn_Crdt_TpCd","10");//证件类型 11护照10身份证
//                        jsonObjectuser.put("Aply_Psn_Crdt_No",cin.getIdcard());//证件号
//                        jsonObjectuser.put("AplY_Psn_Nm",cin.getUserName());//姓名

                        jsonObjectuser.put("Aply_Psn_MblPh_No","18711998999");//手机号
                        jsonObjectuser.put("Aply_Psn_Crdt_TpCd","10");//证件类型 11护照10身份证
                        jsonObjectuser.put("Aply_Psn_Crdt_No","610103196811040071");//证件号
                        jsonObjectuser.put("AplY_Psn_Nm","张八");//姓名
                        JSONObject js2=new JSONObject();
                        js2.put("Data",jsonObjectuser);
                        String serviceid=configConstant.getIFSPLnDfaltRgst();
                        logger.info("sdk默认注册发送json["+js2.toString()+"]");
                        String sdkres= SdkUtil.callByJson(serviceid,js2.toString());
                        logger.info("sdk默认注册返回json["+sdkres+"]");
                        JSONObject jb=JSONObject.fromObject(sdkres);//resultdesc
                        String resultdesc= JSONObject.fromObject(jb.get("Head")).get("Txn_Rsp_Inf").toString();
                        if(resultdesc.equals(Const.SUCCESS)){
                            String usernum= JSONObject.fromObject(jb.get("Data")).get("Usr_ID").toString();
                            cin.setUserNum(usernum);
                            cin.setCreateDate(new Date());
                            creditUserinfService.insertSelective(cin);
                            request.setAttribute("user",cin);
                        }else{
                            response.setStatus(405);
                            return false;
                        }
                    }else{//这里如果用token获得用户信息和库里面存的证件信息不一致那么就要重新注册更新数据库
                        request.setAttribute("user",cred);
                    }
                }else{
                    response.setStatus(401);
                    return false;
                }
            } catch (Exception e) {
                logger.info("-------------拦截器获取token默认注册报错BEGIN------------");
                e.printStackTrace();
                logger.info("------------拦截器获取token默认注册报错END--------");
                response.setStatus(500);
                return false;
            }
            return true;
        }else{
            response.setStatus(401);
            return false;
        }
//org apache catalina connector clientAbortException java.io.IoException
    }

}