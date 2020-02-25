package com.ncov.base.web.model.entity;

import com.ncov.base.core.utils.IdUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.util.Date;
import java.util.List;

@Slf4j
@Data
@ApiModel
public class NcovAreaInfo {
    private static final long serialVersionUID = -2885569051871374440L;
    @ApiModelProperty
    private String id;
    @ApiModelProperty("累计死亡人数")
    private String deadCount;
    @ApiModelProperty("累计治愈人数")
    private String curedCount;
    @ApiModelProperty("累计疑似感染总数")
    private String suspectedCount;
    @ApiModelProperty("累计确诊人数")
    private String confirmedCount;
    @ApiModelProperty("当前确诊总数")
    private String currentConfirmedCount;
    @ApiModelProperty("城市名称")
    private String cityName;
    @ApiModelProperty("城市名称")
    private String cityEnglishName;
    @ApiModelProperty("区域Id")
    private String locationId;
    @ApiModelProperty("省份名称")
    private String provinceName;
    @ApiModelProperty("省份名称")
    private String parentAreaId;
    @ApiModelProperty("国籍")
    private String countryName;
    @ApiModelProperty("国籍英文名")
    private String countryEnglishName;
    @ApiModelProperty("大洲")
    private String continentName;
    @ApiModelProperty("大洲英文名")
    private String continentEnglishName;
    @ApiModelProperty("省份英文名")
    private String provinceEnglishName;
    @ApiModelProperty("评论")
    private String comment;
    @ApiModelProperty("数据时间")
    private Date updateTime;
    @ApiModelProperty("创建时间")
    private Date createDate;
    @ApiModelProperty("更新时间")
    private Date updateDate;
    @ApiModelProperty("创建人")
    private String createBy;
    @ApiModelProperty("操作人")
    private String updateBy;
    @ApiModelProperty("删除标识")
    private String deleteFlag="0";
    @ApiModelProperty("城市")
    private List<NcovAreaInfo> cities;


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
