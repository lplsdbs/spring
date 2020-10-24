package com.ccb.portal.service;

import com.ccb.portal.entity.Page;
import com.ccb.portal.entity.publicity.Index;
import com.ccb.portal.entity.publicity.XyynXzcfInfo;

import java.util.List;

public interface XyynXzcfInfoService {
    List<Index> XyynXzcfInfolistPage(Page page)throws Exception;

    XyynXzcfInfo getByid(String id)throws Exception;
}
