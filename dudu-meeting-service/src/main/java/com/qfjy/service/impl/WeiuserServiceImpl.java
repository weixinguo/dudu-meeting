package com.qfjy.service.impl;

import com.qfjy.entity.po.Weiuser;
import com.qfjy.mapper.WeiuserMapper;
import com.qfjy.service.WeiuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Classname WeiuserServiceImpl
 * @Author guoweixin
 * @Description TODO
 * @Date 2020/6/15 16:44
 * @Created by Administrator
 */
@Service
public class WeiuserServiceImpl implements WeiuserService {
    @Autowired
    private WeiuserMapper weiuserMapper;

    /**
     * 根据openid判断 weiuser表中是否存在该信息
     * @param openid
     * @return
     */
    @Override
    public Weiuser selectByOpenid(String openid) {
        return weiuserMapper.selectByOpenid(openid);
    }

    /**
     * 根据条件进行添加
     *
     * @param record
     * @return
     */
    @Override
    public int insertSelective(Weiuser record) {
        return weiuserMapper.insertSelective(record);
    }
}
