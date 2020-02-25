package com.ncov.base.web.dao;


import com.ncov.base.web.model.entity.NcovAreaInfo;

import java.util.Date;
import java.util.List;

public interface NcovAreaInfoDao{

    /**
     * <pre>
     *     描述：获取详情
     * </pre>
     */
    NcovAreaInfo get(String id);

    /**
     * <pre>
     *     描述：获取所有
     * </pre>
     */
    List<NcovAreaInfo> findAll();

    /**
     * <pre>
     *     描述：新增
     * </pre>
     */
    long insert(NcovAreaInfo t);

    /**
     * <pre>
     *     描述：更新
     * </pre>
     */
    long update(NcovAreaInfo t);

    /**
     * <pre>
     *     描述：删除
     * </pre>
     */
    long delete(String id);

    /**
     * <pre>
     *     描述：清除今日数据
     * </pre>
     */
    long deleteDataOfToday(Integer lastDays);

    /**
     * <pre>
     *     描述：统计数据库总量
     * </pre>
     */
    Integer countData();
}
