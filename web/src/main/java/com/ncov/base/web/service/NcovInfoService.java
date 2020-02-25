package com.ncov.base.web.service;

import com.ncov.base.web.dao.NcovInfoDao;
import com.ncov.base.web.model.entity.NcovInfo;
import com.ncov.base.web.model.vo.QueryNcovInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class NcovInfoService {

    @Autowired
    private NcovInfoDao ncovInfoDao;

    public long save(NcovInfo ncovInfo){
       return  ncovInfoDao.insert(ncovInfo);
    }

    public List<NcovInfo> list(QueryNcovInfoVO queryNcovInfoVO){
        return ncovInfoDao.list();
    }
}
