package com.ncov.base.test.excel;

import cn.hutool.core.io.FileUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 创建人：陈晓程
 * 创建时间：2019年09月19日 14:22
 * 用途：
 */
@Slf4j
public class ExcelOperateTest {
    /**
     * 获取省市区信息
     *
     * @param area
     * @param index
     * @return
     */
    public static String getArea(List<String> area, int index) {
        try {
            return area.get(index);
        } catch (Exception e) {
            log.error("获取区域异常");
        }
        return "";
    }

    private static final Logger logger = LoggerFactory.getLogger(ExcelOperateTest.class);


    @Test
    public void dealExcel(){
        ExcelReader reader = ExcelUtil.getReader(FileUtil.file("E:/1.MyWorkFile/RunTanExcel/source.xls")," 学生选课排课信息表");
        List<StudentVO> sourceList = reader.readAll(StudentVO.class);
        sourceList.forEach(item->{
            if (StringUtils.isNotBlank(item.getTeacherCode())){
                String[] teaacherCodes = item.getTeacherCode().split(",");
                item.setTeacherCode(teaacherCodes[0]);
            }
            if (StringUtils.isNotBlank(item.getTeacherName())){
                String[] teachereNames = item.getTeacherName().split(",");
                item.setTeacherName(teachereNames[0]);
            }
        });
        logger.info("处理学生数据：共{}条",sourceList.size());
        ExcelWriter writer = ExcelUtil.getWriter("E:/1.MyWorkFile/EasierWork/20200219result.xlsx");
        writer.merge(9,"筛选第一个教师编号&名称");
        writer.write(sourceList, true);
        writer.close();
    }
}
