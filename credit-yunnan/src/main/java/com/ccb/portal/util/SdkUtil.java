package com.ccb.portal.util;
import cn.com.infosec.util.Base64;
import com.ccb.portal.entity.ConfigConstant;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ccb.sdk.CCBSDK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import java.io.File;
import java.net.URL;
@Component
public class SdkUtil {
        private static Log log = LogFactory.getLog(SdkUtil.class);

        private  static String produceLevel=PropertiesUtil.getPropertierByKey("config.produceLevel");//getProduceLevel();//产品大类

        private static String produceId=PropertiesUtil.getPropertierByKey("config.produceId");//服务ID

        private  static String priKey=PropertiesUtil.getPropertierByKey("config.priKey");//密钥

        static{
            //初始化（只需在系统启动时初始化一次，config.properties的路径可以是绝对路径，也可以是当前工程的相对路径）
           //正式上线配域名的时候删除下面代码
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            File file=new File("./conf/e8b009d8-9a76-4bb5-9d16-721f8e8069e0.cer");
            file.exists();
            System.out.println("---------------9e0.cer------------ "+file.exists());
            log.info("---------------9e0.cer------------ "+file.exists());
            System.out.println(  file.getAbsolutePath());
            CCBSDK.init("./conf/config.properties",priKey);
        }
        public void callByBean() throws Exception{
            //这是业务bean（所有JAVA Bean 都在包路径：com.ccb.sdk.bean下，每个接口都有对应的JAVA BEAN）
//            BussinessDemo demo = new BussinessDemo();
//            //封装业务数据
//            BussinessDemo.Request request = (BussinessDemo.Request) demo.getReq();
//            //设置请求数据
//            request.setCARDNO("卡号");
//            //发送请求
//            CCBSDK.send("aadsa",demo);
//            //读取响应信息
//            BussinessDemo.Response response = (BussinessDemo.Response) demo.getResp();
//            //公共字段:返回码和返回信息
//            String code = response.getTxn_Rsp_Cd_Dsc();
//            String msg = response.getTxn_Rsp_Inf();
//            //读取响应的业务数据
//            String name = response.getNAME();
//            int amt = response.getAMT();
//            if (log.isDebugEnabled()) {
//                log.debug("code:"+code);
//                log.debug("msg:"+msg);
//                log.debug("name:"+name);
//                log.debug("amt:"+amt);
//            }
        }

        public static String callByJson(String serviceid,String jsonstr ) throws Exception{
            //发送请求并获取响应
            String jsonResp = CCBSDK.send(produceLevel,produceId,serviceid,jsonstr);
            return jsonResp;
        }

    public static void main(String[] args) {
        JSONObject jsonObject=new JSONObject();
//        jsonObject.put("MblPh_No","18700998999");//手机号
//        jsonObject.put("Aply_Psn_Crdt_TpCd","10");//证件类型 11护照10身份证
//        jsonObject.put("Aply_Psn_Crdt_No","610323199109036858");//证件号Aply_Psn_Crdt_No
//        jsonObject.put("AplY_Psn_Nm","张四");//姓名
//
        jsonObject.put("User_ID","ZF0010028863I");//ZF0010028863I
//        jsonObject.put("Unn_Soc_Cr_Cd","91630100310886716T");//913205085593292022
//        jsonObject.put("Cst_No","1060091329383022592");//
////        jsonObject.put("Lgl_Rprs_IDNo","610323199109036858");
        jsonObject.put("TermTp","10");
        jsonObject.put("Tpl_Vno","1.0");
        jsonObject.put("Chnl_TpCd","9976");
        jsonObject.put("Txn_Cd","IFSPLnLmtEst");
//        jsonObject.put("Txn_Cd","IFSPShrhInfQry");
        jsonObject.put("Txn_Chnl_ID","500010");
//        JSONArray jsonArray=new JSONArray();
//        JSONObject js1=new JSONObject();
//        js1.put("Shrh_Nm","张四");
//        jsonArray.add(js1);
//        jsonObject.put("SkholderInfArr",jsonArray);
        JSONObject js=new JSONObject();
        js.put("Data",jsonObject);
        String serviceid="IFSPLnLmtEst";
        try {
            String res=callByJson(serviceid,js.toString());
            JSONObject jb=JSONObject.fromObject(res);
            String resultdesc= JSONObject.fromObject(jb.get("Head")).get("Txn_Rsp_Inf").toString();
            System.out.println(res);
            System.out.println(resultdesc);
            System.out.println("----------------------"+jb);
            System.out.println(new String(Base64.decode("dW1zMTIz")));
            System.out.println(new String(Base64.encode("ums123")));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
