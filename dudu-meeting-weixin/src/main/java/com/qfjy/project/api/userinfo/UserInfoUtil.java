package com.qfjy.project.api.userinfo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qfjy.entity.po.Weiuser;
import com.qfjy.project.api.accessToken.AccessTokenRedis;
import com.qfjy.project.weixin.util.WeixinUtil;
import com.qfjy.service.WeiuserService;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Classname UserInfoUtil
 * @Author guoweixin
 * @Description TODO
 * @Date 2020/6/15 16:46
 * @Created by Administrator
 */
@Slf4j
@Service
public class UserInfoUtil {
    /**accesstoken Redis*/
    @Autowired
    private AccessTokenRedis accessTokenRedis;
    /** weiuser业务逻辑*/
    @Autowired
    private WeiuserService weiuserService;

    /**
     * 收集微信个人信息到数据库
     * 1、调用微信API（用户个人信息）获取 JSON对象
     * 2、JSON对象转成weiuser对象
     * 3、将weiuser 添加到 数据库表中
     */
    public void userInfoService(String openid){
      Weiuser w1=weiuserService.selectByOpenid(openid);

      if(w1!=null){
          // 什么都不做.....
      }else {
          //1、调用微信API（用户个人信息）获取 JSON对象
          JSONObject jsonObject= this.getWeixinUserInfo(openid);
          // 2、JSON对象转成weiuser对象
          Weiuser weiuser=this.convertJSONObject(jsonObject);
          //3、将weiuser 添加到 数据库表中
          int num= weiuserService.insertSelective(weiuser);

      }
    }




    /**
     * 1、调用微信API（用户个人信息）获取 JSON对象
     *  http请求方式: GET https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN
     */
    private static String  WEIXIN_GET_USER_INFO_URL="https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
    public JSONObject getWeixinUserInfo(String openid){
        String url=WEIXIN_GET_USER_INFO_URL.replace("ACCESS_TOKEN",accessTokenRedis.getAccessTokenRedis()).replace("OPENID",openid);
       JSONObject jsonObject=WeixinUtil.httpRequest(url,"GET",null);

       log.info("jsonObject:{}",jsonObject);
        return jsonObject;
    }

    /**
     * 2、JSON对象转成weiuser对象
     */
    public Weiuser  convertJSONObject(JSONObject jsonObject){
        //1种方式：    jsonObject.get("openid");  ................
       //2种方式 将JSON对象转为 Java Bean
        ObjectMapper objectMapper=new ObjectMapper();
        Weiuser weiuser=null;
        try {
             weiuser=objectMapper.readValue(jsonObject.toString(),Weiuser.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            log.error("weiuser:{}",e.getMessage());
        }
        return weiuser;
    }
    /**
     * 3、将weiuser 添加到 数据库表中
     */

}
