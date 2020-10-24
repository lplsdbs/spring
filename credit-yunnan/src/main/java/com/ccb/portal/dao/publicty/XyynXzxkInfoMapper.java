package com.ccb.portal.dao.publicty;


import com.ccb.portal.entity.Page;
import com.ccb.portal.entity.publicity.Index;
import com.ccb.portal.entity.publicity.XyynXzcfInfo;
import com.ccb.portal.entity.publicity.XyynXzxkInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface XyynXzxkInfoMapper {
    int insert(XyynXzxkInfo record);

    int insertSelective(XyynXzxkInfo record);

    List<Index> xyynXzxkInfolistPage(Page page)throws Exception;

    XyynXzxkInfo getByid(String id)throws Exception;

    List<Index> getIndexlistPage(Page page)throws Exception;
}