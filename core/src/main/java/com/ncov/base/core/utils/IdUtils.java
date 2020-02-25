package com.ncov.base.core.utils;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;


public class IdUtils extends IdUtil {
    private static Snowflake snowflake = new Snowflake(1L, 1L);

    /**
     * 雪花算法ID
     *
     * @return
     */
    public static String snowflakeId() {
        return snowflake.nextIdStr();
    }

}
