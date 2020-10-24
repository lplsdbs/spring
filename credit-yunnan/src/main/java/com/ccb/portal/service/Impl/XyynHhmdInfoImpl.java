package com.ccb.portal.service.Impl;

import com.ccb.portal.dao.publicty.XyynHhmdInfoMapper;
import com.ccb.portal.entity.Page;
import com.ccb.portal.entity.publicity.Index;
import com.ccb.portal.entity.publicity.XyynHhmdInfo;
import com.ccb.portal.service.XyynHhmdInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class XyynHhmdInfoImpl implements XyynHhmdInfoService {
    @Autowired
    XyynHhmdInfoMapper xyynHhmdInfoMapper;

    @Override
    public List<Index> XyynHhmdRedInfolistPage(Page page) throws Exception {
        return xyynHhmdInfoMapper.XyynHhmdRedInfolistPage(page);
    }

    @Override
    public List<Index> XyynHhmdBlaInfolistPage(Page page) throws Exception {
        return xyynHhmdInfoMapper.XyynHhmdBlaInfolistPage(page);
    }

    @Override
    public XyynHhmdInfo getByIdStatus(Index index) throws Exception {
        return xyynHhmdInfoMapper.getByIdStatus(index);
    }
}
