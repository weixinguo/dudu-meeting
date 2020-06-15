package com.qfjy.project.api.accessToken;

import com.qfjy.project.weixin.main.MenuManager;
import com.qfjy.project.weixin.util.WeixinUtil;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Classname AccessTokenRedis
 * @Author guoweixin
 * @Description TODO
 * @Date 2020/6/12 11:04
 * @Created by Administrator
 */
@Slf4j
@Component
public class AccessTokenRedis {

    @Autowired
     private RedisTemplate<String,Object> redisTemplate;
     @Resource(name = "redisTemplate")
    private ValueOperations<String,String> string;

    private static String accessTokenKey="weixin:accessToken:val";

    public String getAccessTokenRedis(){
        String result=null;
      if(redisTemplate.hasKey(accessTokenKey)){
          return  string.get(accessTokenKey);
      }else{
          //1 从微信服务器中获取
          //2 存入redis，并给序过期时间
          result=this.getAccessToken();
          string.set(accessTokenKey,result);
          redisTemplate.expire(accessTokenKey,2, TimeUnit.HOURS);
          return result;
      }
    }


    /**
     * 获取access_token
     */
    private static String ACCESS_TOKEN_URL="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    private String getAccessToken(){
        String url=ACCESS_TOKEN_URL.replace("APPID", MenuManager.appId).replace("APPSECRET",MenuManager.appSecret);

        JSONObject jsonObject= WeixinUtil.httpRequest(url,"GET",null);
        log.info("access_token:"+jsonObject.toString());
        String result=null;
        try{
            result=jsonObject.getString("access_token");
        }catch (Exception ex){
            log.error("access_token调用发生异常"+jsonObject.toString());
        }
        return result;
    }
}
