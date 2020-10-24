package com.cloud.eurekaclient.config;

import org.springframework.cloud.netflix.zuul.filters.route.ZuulFallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
//在zuul上面台南佳熔断器
@Component
public class MyFallbackProvider implements ZuulFallbackProvider {
    @Override
    public String getRoute() {
        return "eureka-client";
        //如果给所有的路由服务都加熔断器那么返回*，serviceId对应的服务名
//        return "*";
    }

    @Override
    public ClientHttpResponse fallbackResponse() {
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return 200;
            }

            @Override
            public String getStatusText() throws IOException {
                return "ok";
            }

            @Override
            public void close() {

            }

            @Override
            public InputStream getBody() throws IOException {
                return new ByteArrayInputStream("oooops!error,i.m the fallback".getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders header=new HttpHeaders();
                header.setContentType(MediaType.APPLICATION_JSON);
                return header;
            }
        };
    }
}
