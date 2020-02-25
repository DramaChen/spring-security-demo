package com.ncov.base.web.model.entity;

import com.ncov.base.core.utils.IdUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.util.Date;

@Slf4j
@Data
@ApiModel
public class RumorInfo implements Serializable {
    private static final long serialVersionUID = 3236756963105139557L;
    @ApiModelProperty("id")
    private String id;
    @ApiModelProperty("采集数据id")
    private String _id;
    @ApiModelProperty("标题")
    private String title;
    @ApiModelProperty("概述")
    private String mainSummary;
    @ApiModelProperty("文章")
    private String body;
    @ApiModelProperty("谣言类型")
    private String rumorType;
    @ApiModelProperty("源链接")
    private String sourceUrl;
    @ApiModelProperty("")
    private Date createDate;
    @ApiModelProperty("")
    private Date updateDate;
    @ApiModelProperty("创建人")
    private String createBy;
    @ApiModelProperty("操作人")
    private String updateBy;
    @ApiModelProperty("删除标识")
    private String deleteFlag="0";


    public void prepareInsert(){
        if (StringUtils.isBlank(id)){
            this.id= IdUtils.snowflakeId();
        }
        this.createDate=new Date();
        this.updateDate=new Date();
        this.createBy="1";
        this.updateBy="1";
    }

    public void prepareUpdate(){
        this.updateDate=new Date();
        this.updateBy="1";
    }
}
