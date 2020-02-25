package com.ncov.base.web.service;

import com.ncov.base.web.dao.RumorInfoDao;
import com.ncov.base.web.model.entity.RumorInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
public class RumorInfoService {

    @Autowired
    protected RumorInfoDao dao;

    public RumorInfo get(String id) {
        return dao.get(id);
    }

    public List<RumorInfo> findAll() {
//        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        return dao.findAll();
    }

    @Transactional
    public long insert(RumorInfo t) {
            t.prepareInsert();
        return dao.insert(t);
    }

    public long update(RumorInfo t) {
        t.prepareUpdate();
        return dao.update(t);
    }

    public long delete(String id) {
        return dao.delete(id);
    }
}
