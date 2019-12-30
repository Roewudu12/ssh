package com.roe.service.impl;

import com.roe.dao.BaseDao;
import com.roe.dao.BaseDictDao;
import com.roe.domain.BaseDict;
import com.roe.service.BaseDictService;

import java.util.List;

public class BaseDictServiceImpl implements BaseDictService {
    private BaseDictDao bdd;


    @Override
    public List<BaseDict> getListByTypeCode(String dict_type_code) {
        return bdd.getListByTypeCode(dict_type_code);
    }

    public void setBdd(BaseDictDao bdd) {
        this.bdd = bdd;
    }
}
