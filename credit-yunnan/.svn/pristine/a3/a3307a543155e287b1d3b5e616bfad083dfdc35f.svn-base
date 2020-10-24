package com.ccb.portal.dao.publicty;


import com.ccb.portal.entity.Page;
import com.ccb.portal.entity.publicity.Index;
import com.ccb.portal.entity.publicity.XyynHhmdInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface XyynHhmdInfoMapper {
    int insert(XyynHhmdInfo record);

	int insertSelective(XyynHhmdInfo record)throws Exception;

    List<Index> XyynHhmdRedInfolistPage(Page page)throws Exception;

    List<Index> XyynHhmdBlaInfolistPage(Page page)throws Exception;

    XyynHhmdInfo getByIdStatus(Index index)throws Exception;
}