package com.ccb.portal.service.Impl;

import com.ccb.portal.dao.publicty.XyynXzxkInfoMapper;
import com.ccb.portal.entity.Page;
import com.ccb.portal.entity.publicity.Index;
import com.ccb.portal.entity.publicity.XyynXzcfInfo;
import com.ccb.portal.entity.publicity.XyynXzxkInfo;
import com.ccb.portal.service.XyynXzxkInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class XyynXzxkInfoImpl implements XyynXzxkInfoService {
    @Autowired
    XyynXzxkInfoMapper xyynXzxkInfoMapper;

    @Override
    public List<Index> xyynXzxkInfolistPage(Page page) throws Exception {
        return xyynXzxkInfoMapper.xyynXzxkInfolistPage(page);
    }

    @Override
    public XyynXzxkInfo getByid(String id) throws Exception {
        return xyynXzxkInfoMapper.getByid(id);
    }

    @Override
    public List<Index> getIndexlistPage(Page page) throws Exception {
        return xyynXzxkInfoMapper.getIndexlistPage(page);
    }


}
