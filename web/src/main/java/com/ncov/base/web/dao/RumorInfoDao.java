package com.ncov.base.web.dao;


import com.ncov.base.web.model.entity.RumorInfo;

import java.util.List;

public interface RumorInfoDao{

    /**
     * <pre>
     *     描述：获取详情
     * </pre>
     */
    RumorInfo get(String id);

    /**
     * <pre>
     *     描述：获取所有
     * </pre>
     */
    List<RumorInfo> findAll();

    /**
     * <pre>
     *     描述：新增
     * </pre>
     */
    long insert(RumorInfo t);

    /**
     * <pre>
     *     描述：更新
     * </pre>
     */
    long update(RumorInfo t);

    /**
     * <pre>
     *     描述：删除
     * </pre>
     */
    long delete(String id);
}
