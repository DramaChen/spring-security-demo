package com.ncov.base.web.model.entity;

import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ncov.base.core.utils.IdUtils;
import lombok.Data;
import org.apache.commons.lang.StringUtils;

import java.util.Date;

@Data
public class NcovInfo {

    private String id;

    private String generalRemark;//全国疫情信息概览

    private Integer deadCount;//累计死亡人数

    private Integer curedCount;//治愈病例

    private Integer seriousCount;//重症病例人数

    private Integer suspectedCount;//疑似感染总数

    private Integer currentConfirmedCount;//当前确诊总数

    private Integer confirmedCount;//累计确诊总数

   private String note3;//传播途径

    private String note2;//传染源

    private String note1;//病毒名称

    private Integer currentConfirmedIncr;//较昨日变化数量

    private Integer suspectedIncr;//较昨日疑似感染人数

    private Integer seriousIncr;//较昨日新增重症人数

    private Integer confirmedIncr;//较昨日新增确诊人数

    private Integer deadIncr;//死亡增加人数

    private Integer curedIncr;//治愈增加人数

    private Date createDate;

    private Date updateDate;

    private String createBy;

    private String updateBy;

    private String deleteFlag;

    @JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private Date updateTime;//更新时间

    public void prepareInsert(){
        if (StringUtils.isBlank(id)){
            this.id= IdUtils.snowflakeId();
        }
        this.createBy="1";
        this.updateBy="1";
        this.createDate=new Date();
        this.updateDate=new Date();
    }


}
