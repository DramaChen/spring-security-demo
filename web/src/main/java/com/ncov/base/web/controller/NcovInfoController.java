package com.ncov.base.web.controller;

import com.ncov.base.core.entity.JsonResultVO;
import com.ncov.base.web.model.entity.NcovInfo;
import com.ncov.base.web.service.NcovInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ncov")
public class NcovInfoController {

    @Autowired
    private NcovInfoService ncovInfoService;

    @GetMapping("/list")
    public JsonResultVO<List<NcovInfo>> getPageList(){
        return JsonResultVO.success("获取nCoV时间轴",ncovInfoService.list(null));
    }
}
