package com.qfjy.service;

import com.qfjy.entity.po.Demo;

public interface DemoService {
    /**
     * 通过主键ID查询对象
     * @param id
     * @return
     */
    Demo selectByPrimaryKey(Integer id);
}
