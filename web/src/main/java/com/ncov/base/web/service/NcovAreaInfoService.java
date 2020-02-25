package com.ncov.base.web.service;

import com.ncov.base.web.dao.NcovAreaInfoDao;
import com.ncov.base.web.model.entity.NcovAreaInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


@Slf4j
@Service
@Transactional(readOnly = true)
public class NcovAreaInfoService {

    @Autowired
    protected NcovAreaInfoDao dao;

    public NcovAreaInfo get(String id) {
        return dao.get(id);
    }

        /*public List<NcovAreaInfo> findAll(PageVO page){
                PageHelper.startPage(page.getPageNo(), page.getPageSize());
                return dao.findAll();
        }*/

    @Transactional
    public long insert(NcovAreaInfo t) {
        t.prepareInsert();
        return dao.insert(t);
    }

    public long update(NcovAreaInfo t) {
        t.prepareUpdate();
        return dao.update(t);
    }

    public long delete(String id) {
        return dao.delete(id);
    }

    @Transactional
    public long deleteDataOfToday(Integer  day) {
        return dao.deleteDataOfToday(day);
    }

    public Integer countData() {
        return dao.countData();
    }
}
