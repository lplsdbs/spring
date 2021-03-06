package com.ccb.portal.filter;


import com.alibaba.fastjson.JSON;
import com.ccb.portal.util.DesUtil;
import com.ccb.portal.util.JsonUtil;
import net.sf.json.JSONObject;
//import org.apache.http.protocol.HTTP;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.Principal;
import java.util.*;

//import com.poly.rbl.utils.FastJsonUtils;
//import com.poly.rbl.utils.RsaEncryptUtil;


/**
 * 自定义HttpServletRequest，统一处理请求数据解密问题
 *
 * @see MyRequest
 * @since
 */
public class MyRequest implements HttpServletRequest {

    public HttpServletRequest originalRequest;

    public Map decryptParameterMap;


    public MyRequest(HttpServletRequest request) {

        originalRequest = request;

        System.out.println(originalRequest.getRemoteAddr() + "---URL:"
                + originalRequest.getRequestURL() + "---URI:"
                + originalRequest.getRequestURI());
        String contype = request.getContentType();
        System.out.println(contype);
        //这里统一把前台的json格式获取到需要解密的解密，解密后又放回到map
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
            String lin = null;
            StringBuffer sb = new StringBuffer();
            while ((lin = br.readLine()) != null) {
                sb.append(lin);
            }
            String re = sb.toString();
            if (StringUtils.isNotBlank(re)) {
                String js = URLDecoder.decode(re, "UTF-8");
                Map map = JsonUtil.jsonToMap(js, true);
                System.out.println(map);
                if (map.containsKey("data")) {//如果是加密的格式则统一解密放回到parammap中
                    String deStr = DesUtil.decrypt(String.valueOf(map.get("data")));
                    System.out.println(deStr);
                    decryptParameterMap = JsonUtil.jsonToMap(deStr, true);
                } else {
                    decryptParameterMap = JsonUtil.jsonToMap(js, true);
                }
            } else {

                decryptParameterMap = new HashMap();
                try {
                    request.setCharacterEncoding("UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                Map<String, String[]> properties = request.getParameterMap();
                Map<String, String> returnMap = new HashMap<String, String>();
                Iterator<Map.Entry<String, String[]>> entries = properties.entrySet().iterator();
                Map.Entry<String, String[]> entry;
                String key = "";
                String value = "";
                while (entries.hasNext()) {
                    entry = (Map.Entry<String, String[]>) entries.next();
                    key = (String) entry.getKey();
                    Object valueObj = entry.getValue();
                    if (null == valueObj) {
                        value = "";
                    } else if (valueObj instanceof String[]) {
                        String[] values = (String[]) valueObj;
                        for (int i = 0; i < values.length; i++) {
                            value = values[i] + ",";
                        }
                        value = value.substring(0, value.length() - 1);
                    } else {
                        value = valueObj.toString();
                    }
                    returnMap.put(key, value);
                }

                decryptParameterMap.putAll(returnMap);
            }
            System.out.println(decryptParameterMap);
//        String data = request.getParameter("data");
            //{'Cst_id':'1','Cst_type':'企业','Cst_identity_type':'12321','Cst_identity_id':'123213'}
            //58B4555F0EB8E4769568DE6BFEA0366EB9CB88707FFD271328C2CED7E37C994EE56B07A6FF4C3DC14E329CE1C1FB03B7644BD62BE1066460FBE2D50A89FBC76A082536C992DA7CF9B7CAE4F7FB3CC748CA3E1F6F8323FB135C507D669E524FE7
        } catch (Exception e) {
            e.printStackTrace();
        }
//         Map prametermap= request.getParameterMap();
////          String data= net.sf.json.JSONObject.fromObject(prametermap).toString();
//        System.out.println("------------------"+data);
//        if (!StringUtils.isEmpty(data))
//        {
//            try
//            {
//                // 通过密钥解密参数,并转成Map
//                String deStr = DesUtil.decrypt(data,"lpl1234444444444");
//                decryptParameterMap = JSONObject.parseObject(deStr);
//            }
//            catch (Exception e)
//            {
//                e.printStackTrace();
//            }
//        }
    }

    @Override
    public Object getAttribute(String s) {

        return originalRequest.getAttribute(s);

    }

    @Override
    public Enumeration getAttributeNames() {

        return originalRequest.getAttributeNames();

    }

    @Override
    public String getCharacterEncoding() {
        return originalRequest.getCharacterEncoding();
    }

    @Override
    public void setCharacterEncoding(String s)
            throws UnsupportedEncodingException {
        originalRequest.setCharacterEncoding(s);
    }

    @Override
    public int getContentLength() {
        return originalRequest.getContentLength();
    }

    @Override
    public long getContentLengthLong() {
        return originalRequest.getContentLengthLong();
    }

    @Override
    public String getContentType() {
        return originalRequest.getContentType();
    }

    @Override
    public ServletInputStream getInputStream()
            throws IOException {
        return originalRequest.getInputStream();
    }

    @Override
    public String getParameter(String s) {

        // 返回解密后的参数
        return String.valueOf(decryptParameterMap.get(s));
    }

    @Override
    public Enumeration getParameterNames() {
        // 这里是通过实体类注入参数
        return Collections.enumeration(decryptParameterMap.keySet());
    }

    @Override
    public String[] getParameterValues(String s) {

        // 这里是注入参数
        Object o = decryptParameterMap.get(s);
        if (o == null) {
            return null;
        } else {
            return new String[]{String.valueOf(o)};
        }

    }

    @Override
    public Map getParameterMap() {
        //return originalRequest.getParameterMap();
        return decryptParameterMap;
    }

    @Override
    public String getProtocol() {
        return originalRequest.getProtocol();
    }

    @Override
    public String getScheme() {
        // TODO Auto-generated method stub
        return originalRequest.getScheme();
    }

    @Override
    public String getServerName() {
        // TODO Auto-generated method stub
        return originalRequest.getServerName();
    }

    @Override
    public int getServerPort() {
        // TODO Auto-generated method stub
        return originalRequest.getServerPort();
    }

    @Override
    public BufferedReader getReader()
            throws IOException {
        // TODO Auto-generated method stub
        return originalRequest.getReader();
    }

    @Override
    public String getRemoteAddr() {
        // TODO Auto-generated method stub
        return originalRequest.getRemoteAddr();
    }

    @Override
    public String getRemoteHost() {

        // TODO Auto-generated method stub
        return originalRequest.getRemoteHost();

    }

    @Override
    public void setAttribute(String s, Object obj) {
        originalRequest.setAttribute(s, obj);
    }

    @Override
    public void removeAttribute(String s) {
        // TODO Auto-generated method stub
        originalRequest.removeAttribute(s);
    }

    @Override
    public Locale getLocale() {

        // TODO Auto-generated method stub
        return originalRequest.getLocale();

    }

    @Override
    public Enumeration<Locale> getLocales() {

        // TODO Auto-generated method stub
        return originalRequest.getLocales();

    }

    @Override
    public boolean isSecure() {

        // TODO Auto-generated method stub
        return originalRequest.isSecure();

    }

    @Override
    public RequestDispatcher getRequestDispatcher(String s) {

        // TODO Auto-generated method stub

        return originalRequest.getRequestDispatcher(s);

    }

    @Override
    public String getRealPath(String s) {

        // TODO Auto-generated method stub
        return originalRequest.getRealPath(s);

    }

    @Override
    public int getRemotePort() {

        // TODO Auto-generated method stub
        return originalRequest.getRemotePort();

    }

    @Override
    public String getLocalName() {

        // TODO Auto-generated method stub
        return originalRequest.getLocalName();

    }

    @Override
    public String getLocalAddr() {

        // TODO Auto-generated method stub
        return originalRequest.getLocalAddr();

    }

    @Override
    public int getLocalPort() {

        // TODO Auto-generated method stub
        return originalRequest.getLocalPort();

    }

    @Override
    public ServletContext getServletContext() {

        // TODO Auto-generated method stub
        return originalRequest.getServletContext();

    }

    @Override
    public AsyncContext startAsync()
            throws IllegalStateException {

        // TODO Auto-generated method stub
        return originalRequest.startAsync();

    }

    @Override
    public AsyncContext startAsync(ServletRequest servletrequest, ServletResponse servletresponse)
            throws IllegalStateException {
        return originalRequest.startAsync(servletrequest, servletresponse);
    }

    @Override
    public boolean isAsyncStarted() {

        return originalRequest.isAsyncStarted();
    }

    @Override
    public boolean isAsyncSupported() {

        return originalRequest.isAsyncSupported();

    }

    @Override
    public AsyncContext getAsyncContext() {

        // TODO Auto-generated method stub
        return originalRequest.getAsyncContext();

    }

    @Override
    public DispatcherType getDispatcherType() {

        // TODO Auto-generated method stub
        return originalRequest.getDispatcherType();

    }

    @Override
    public boolean authenticate(HttpServletResponse httpservletresponse)
            throws IOException, ServletException {

        // TODO Auto-generated method stub
        return originalRequest.authenticate(httpservletresponse);

    }

    @Override
    public String changeSessionId() {

        // TODO Auto-generated method stub
        return originalRequest.changeSessionId();

    }

    @Override
    public String getAuthType() {

        // TODO Auto-generated method stub
        return originalRequest.getAuthType();

    }

    @Override
    public String getContextPath() {

        // TODO Auto-generated method stub
        return originalRequest.getContextPath();

    }

    @Override
    public Cookie[] getCookies() {

        // TODO Auto-generated method stub
        return originalRequest.getCookies();

    }

    @Override
    public long getDateHeader(String s) {

        // TODO Auto-generated method stub
        return originalRequest.getDateHeader(s);

    }

    @Override
    public String getHeader(String s) {

        // TODO Auto-generated method stub
        return originalRequest.getHeader(s);

    }

    @Override
    public Enumeration getHeaderNames() {

        // TODO Auto-generated method stub
        return originalRequest.getHeaderNames();

    }

    @Override
    public Enumeration getHeaders(String s) {

        // TODO Auto-generated method stub
        return originalRequest.getHeaders(s);

    }

    @Override
    public int getIntHeader(String s) {

        // TODO Auto-generated method stub
        return originalRequest.getIntHeader(s);

    }

    @Override
    public String getMethod() {

        return originalRequest.getMethod();
    }

    @Override
    public Part getPart(String s)
            throws IOException, ServletException {

        // TODO Auto-generated method stub
        return originalRequest.getPart(s);

    }

    @Override
    public Collection<Part> getParts()
            throws IOException, ServletException {

        // TODO Auto-generated method stub
        return originalRequest.getParts();

    }

    @Override
    public String getPathInfo() {

        // TODO Auto-generated method stub
        return originalRequest.getPathInfo();

    }

    @Override
    public String getPathTranslated() {

        // TODO Auto-generated method stub
        return originalRequest.getPathTranslated();

    }

    @Override
    public String getQueryString() {

        // TODO Auto-generated method stub
        return originalRequest.getQueryString();

    }

    @Override
    public String getRemoteUser() {

        // TODO Auto-generated method stub
        return originalRequest.getRemoteUser();

    }

    @Override
    public String getRequestURI() {

        // TODO Auto-generated method stub
        return originalRequest.getRequestURI();

    }

    @Override
    public StringBuffer getRequestURL() {

        // TODO Auto-generated method stub
        return originalRequest.getRequestURL();

    }

    @Override
    public String getRequestedSessionId() {

        // TODO Auto-generated method stub
        return originalRequest.getRequestedSessionId();

    }

    @Override
    public String getServletPath() {

        // TODO Auto-generated method stub
        return originalRequest.getServletPath();

    }

    @Override
    public HttpSession getSession() {

        // TODO Auto-generated method stub
        return originalRequest.getSession();

    }

    @Override
    public HttpSession getSession(boolean flag) {

        // TODO Auto-generated method stub
        return originalRequest.getSession(flag);

    }

    @Override
    public Principal getUserPrincipal() {

        // TODO Auto-generated method stub
        return originalRequest.getUserPrincipal();

    }

    @Override
    public boolean isRequestedSessionIdFromCookie() {

        // TODO Auto-generated method stub
        return originalRequest.isRequestedSessionIdFromCookie();

    }

    @Override
    public boolean isRequestedSessionIdFromURL() {

        // TODO Auto-generated method stub
        return originalRequest.isRequestedSessionIdFromURL();

    }

    @Override
    public boolean isRequestedSessionIdFromUrl() {

        // TODO Auto-generated method stub
        return originalRequest.isRequestedSessionIdFromUrl();

    }

    @Override
    public boolean isRequestedSessionIdValid() {

        // TODO Auto-generated method stub
        return originalRequest.isRequestedSessionIdValid();

    }

    @Override
    public boolean isUserInRole(String s) {

        // TODO Auto-generated method stub
        return originalRequest.isUserInRole(s);

    }

    @Override
    public void login(String s, String s1)
            throws ServletException {

        originalRequest.login(s, s1);

    }

    @Override
    public void logout()
            throws ServletException {
        originalRequest.logout();
    }

    @Override
    public <T extends HttpUpgradeHandler> T upgrade(Class<T> class1)
            throws IOException, ServletException {
        return originalRequest.upgrade(class1);
    }

}

