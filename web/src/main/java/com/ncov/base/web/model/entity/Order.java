package com.ncov.base.web.model.entity;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <pre>
 *    描述：订单实体
 * </pre>
 * @author ChenJunLin
 * @version v1.0
 */
@Data
@AllArgsConstructor
public class Order implements Serializable {

    private static final long serialVersionUID = 3627879472428334937L;
    //id
    private String id;

    //订单名称
    private String name;

    //订单创建时间
    private Date createTime;
}
