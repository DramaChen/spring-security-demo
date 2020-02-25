package com.ncov.base.web.controller;

import com.ncov.base.core.entity.JsonResultVO;
import com.ncov.base.web.model.entity.NcovAreaInfo;
import com.ncov.base.web.service.NcovAreaInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/ncovAreaInfo")
@Api(tags = "谣言管理")
public class NcovAreaInfoController {
    @Autowired
    private NcovAreaInfoService ncovAreaInfoService;

    @PostMapping("/insert")
    @ApiOperation("新增")
    public JsonResultVO insert(@RequestBody @Validated NcovAreaInfo ncovAreaInfo, BindingResult bindingResult) {
       /* if(bindingResult.hasErrors()){
            throw new BindingResultException(bindingResult);
        }*/
        long count = ncovAreaInfoService.insert(ncovAreaInfo);
        return count > 0 ? JsonResultVO.success("新增成功") : JsonResultVO.failure("新增失败");
    }

    /*@PostMapping("/list")
        @ApiOperation("列表")
    public JsonResultVO
    <PageInfo<NcovAreaInfo>> findAll(@Validated PageVO page, BindingResult bindingResult){
            if(bindingResult.hasErrors()){
                throw new BindingResultException(bindingResult);
            }
    return JsonResultVO.success(new PageInfo<>(ncovAreaInfoService.findAll(page)));
        }*/
    @PostMapping("/update")
    @ApiOperation("修改")
    public JsonResultVO update(@RequestBody @Validated NcovAreaInfo ncovAreaInfo, BindingResult bindingResult) {
    /*if(bindingResult.hasErrors()){
    throw new BindingResultException(bindingResult);
    }*/
        long count = ncovAreaInfoService.update(ncovAreaInfo);
        return count > 0 ? JsonResultVO.success("修改成功") : JsonResultVO.failure("修改失败");
    }

    @PostMapping("/delete/{id}")
    @ApiOperation("删除")
    public JsonResultVO delete(@PathVariable("id") String id) {
        /*if (id == null) {
            throw new IdNullPointerException();
        }*/
        long count = ncovAreaInfoService.delete(id);
        return count > 0 ? JsonResultVO.success("删除成功") : JsonResultVO.failure("删除失败");
    }

    @GetMapping("/detail/{id}")
    @ApiOperation("详情")
    public JsonResultVO<NcovAreaInfo> get(@PathVariable("id") String id) {
        /*if (id == null) {
            throw new IdNullPointerException();
        }*/
        return JsonResultVO.success(ncovAreaInfoService.get(id));
    }

}