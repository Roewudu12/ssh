package com.roe.service;

import com.roe.domain.BaseDict;

import java.util.List;

public interface BaseDictService {
    //根据数据字典类型字段获得数据字典对象
    List<BaseDict> getListByTypeCode(String dict_type_code);
}
