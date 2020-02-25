package com.ncov.base.web.dao;


import com.ncov.base.web.model.entity.NcovInfo;

import java.util.Date;
import java.util.List;

public interface NcovInfoDao {

    /**
     * <pre>
     *     描述：新增
     * </pre>
     */
    long insert(NcovInfo ncovInfo);

    /**
     * <pre>
     *     描述：获取列表
     * </pre>
     */
    List<NcovInfo> list();

    /**
     * <pre>
     *     描述：删除今日采集的疫情数据
     * </pre>
     */
    long deleteDataOfToday(Integer  lastDays);

    Integer countNcvoInfo();
}
