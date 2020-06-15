package com.qfjy.service;

import com.qfjy.entity.po.Weiuser;
import org.apache.ibatis.annotations.Select;

public interface WeiuserService {

    /**
     * 根据openid判断 weiuser表中是否存在该信息
     * @param openid
     * @return
     */
    Weiuser selectByOpenid(String openid);

    /**
     * 根据条件进行添加
     * @param record
     * @return
     */
    int insertSelective(Weiuser record);
}
