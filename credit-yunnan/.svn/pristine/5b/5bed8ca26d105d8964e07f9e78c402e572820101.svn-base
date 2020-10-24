package com.ccb.portal.filter;

/**
 * Created by as on 2018/10/24.
 */

import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 重写doDispatch方法，该方法是所有请求得总入口，把request转到自定义的类里面
 *
 *
 * @version 2017年8月30日
 * @see MyDispatcherServlet
 * @since
 */
public class MyDispatcherServlet extends DispatcherServlet
{

    private static final long serialVersionUID = 1L;

    @Override
    protected void doDispatch(HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        super.doDispatch(new MyRequest(request), response);
    }

}

