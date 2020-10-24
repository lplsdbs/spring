package com.ccb.portal.service.Impl;

import com.ccb.portal.dao.publicty.XyynXzcfInfoMapper;
import com.ccb.portal.entity.Page;
import com.ccb.portal.entity.publicity.Index;
import com.ccb.portal.entity.publicity.XyynXzcfInfo;
import com.ccb.portal.service.XyynXzcfInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class XyynXzcfInfoImpl implements XyynXzcfInfoService {
    @Autowired
    XyynXzcfInfoMapper xyynXzcfInfoMapper;

    @Override
    public List<Index> XyynXzcfInfolistPage(Page page) throws Exception {
        return xyynXzcfInfoMapper.XyynXzcfInfolistPage(page);
    }

    @Override
    public XyynXzcfInfo getByid(String id) throws Exception {
        return xyynXzcfInfoMapper.getByid(id);
    }
}
