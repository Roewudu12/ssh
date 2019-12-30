package com.roe.dao;

import com.roe.domain.BaseDict;

import java.util.List;

public interface BaseDictDao extends BaseDao<BaseDict>{
    List<BaseDict> getListByTypeCode(String dict_type_code);
}
