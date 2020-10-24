package com.ccb.portal.dao.publicty;


import com.ccb.portal.entity.Page;
import com.ccb.portal.entity.publicity.Index;
import com.ccb.portal.entity.publicity.XyynXzcfInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface XyynXzcfInfoMapper {
    int insert(XyynXzcfInfo record)throws Exception;

    int insertSelective(XyynXzcfInfo record)throws Exception;

    List<Index> XyynXzcfInfolistPage(Page page)throws Exception;

    XyynXzcfInfo getByid(String id)throws Exception;

}