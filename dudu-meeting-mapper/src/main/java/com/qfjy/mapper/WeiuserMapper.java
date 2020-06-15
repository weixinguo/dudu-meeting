package com.qfjy.mapper;

import com.qfjy.entity.po.Weiuser;
import org.apache.ibatis.annotations.Select;

public interface WeiuserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Weiuser record);

    int insertSelective(Weiuser record);

    Weiuser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Weiuser record);

    int updateByPrimaryKey(Weiuser record);


    /**
     * 根据openid判断 weiuser表中是否存在该信息
     * @param openid
     * @return
     */
    @Select(" select * from weiuser where openid=#{openid}")
    Weiuser  selectByOpenid(String openid);

}