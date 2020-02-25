package com.ncov.base.web.controller;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import com.ncov.base.web.model.entity.Order;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {
    @GetMapping("/list/{id}")
    @ResponseBody
    public Order getOrder(@PathVariable("id")String id){
        Order order = new Order(IdUtil.randomUUID(), "订单一", RandomUtil.randomDay(1, 7));
        return order;
    }
}
