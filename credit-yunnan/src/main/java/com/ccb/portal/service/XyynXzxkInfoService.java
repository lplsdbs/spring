package com.ccb.portal.service;

import com.ccb.portal.entity.Page;
import com.ccb.portal.entity.publicity.Index;
import com.ccb.portal.entity.publicity.XyynXzcfInfo;
import com.ccb.portal.entity.publicity.XyynXzxkInfo;

import java.util.List;

public interface XyynXzxkInfoService {
    List<Index> xyynXzxkInfolistPage(Page page)throws Exception;

    XyynXzxkInfo getByid(String id)throws Exception;


    List<Index> getIndexlistPage(Page page)throws Exception;

//    List<XyynXzcfInfo> XyynXzcfInfolistPage(Page page)throws Exception;
}
