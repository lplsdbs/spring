package com.cloud.eurekaclient.controller;

import com.netflix.zuul.ZuulFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Component;
//在链路数据中添加自定义数据
@Component
public class LoogerFilter extends ZuulFilter {
    @Autowired
    Tracer tracer;
    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return 900;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        tracer.addTag("op","lpl");
        System.out.println(tracer.getCurrentSpan().traceIdString());
        return null;
    }
}
