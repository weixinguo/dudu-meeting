package com.qfjy.project.api.hitokoto;

import com.qfjy.project.weixin.util.WeixinUtil;
import net.sf.json.JSONObject;

/**
 * @Classname HitokotoUtil
 * @Author guoweixin
 * @Description TODO
 * @Date 2020/6/10 16:26
 * @Created by Administrator
 */
public class HitokotoUtil {
    /**
     * 一言https://developer.hitokoto.cn/
     */
    private final static  String  HIT_URL="https://v1.hitokoto.cn/";

    public  static  String  getHitokoto(){

      JSONObject jsonObject= WeixinUtil.httpRequest(HIT_URL,"GET",null);
       String hitStr= jsonObject.getString("hitokoto");
        return hitStr;
    }

    public static void main(String[] args) {
        System.out.println( HitokotoUtil.getHitokoto());
    }
}
