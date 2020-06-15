package com.qfjy.project.api.accessToken;

import com.qfjy.project.weixin.main.MenuManager;
import com.qfjy.project.weixin.util.WeixinUtil;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;

/**
 * @Classname AccessTokenThread
 * @Author guoweixin
 * @Description TODO access_token是公众号的全局唯一接口调用凭据，公众号调用各接口时都需使用access_token。
开发者需要进行妥善保存。access_token的存储至少要保留512个字符空间。
1、access_token的有效期目前为2个小时，需定时刷新，
2、重复获取将导致上次获取的access_token失效。
3、每日调用上限2000次（获取access_token API接口)
 * @Date 2020/6/12 10:28
 * @Created by Administrator
 */
@Slf4j
public class AccessTokenThread  extends  Thread{
    /**accesstoken数据*/
    public static String ACCESS_TOKEN_VAL;
    @Override
    public void run() {
        //获取access_token
        while(true){
            ACCESS_TOKEN_VAL=this.getAccessToken();
            System.out.println("获取到的token:"+ACCESS_TOKEN_VAL);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 获取access_token
     */
    private static String ACCESS_TOKEN_URL="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    public String getAccessToken(){
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
