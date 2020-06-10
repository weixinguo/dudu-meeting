package com.qfjy.service.impl;

import com.qfjy.entity.po.Demo;
import com.qfjy.mapper.DemoMapper;
import com.qfjy.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Classname DemoServiceImpl
 * @Author guoweixin
 * @Description TODO
 * @Date 2020/6/10 12:08
 * @Created by Administrator
 */
@Service
public class DemoServiceImpl implements DemoService {
    @Autowired
    private DemoMapper demoMapper;

    /**
     * 通过主键ID查询对象
     *
     * @param id
     * @return
     */
    @Override
    public Demo selectByPrimaryKey(Integer id) {
        return demoMapper.selectByPrimaryKey(id);
    }
}
