package com.qfjy.project.api.tuling;

import com.qfjy.project.api.tuling.bean.InputText;
import com.qfjy.project.api.tuling.bean.Perception;
import com.qfjy.project.api.tuling.bean.TulingBean;
import com.qfjy.project.api.tuling.bean.UserInfo;
import com.qfjy.project.weixin.util.WeixinUtil;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname TulingUtil
 * @Author guoweixin
 * @Description TODO
 * @Date 2020/6/11 10:25
 * @Created by Administrator
 */
@Slf4j
@Component
public class TulingUtil {
    /**
     *  TODO 图灵智能回复机器人
     *  https://www.kancloud.cn/turing/www-tuling123-com/718227
      */
    private final static String TULING_URL="http://openapi.tuling123.com/openapi/api/v2";


    private static TulingBean  getTulingObject(String text,String apiKey){
        /**1封装输入信息的 JAVA BEAN */
        TulingBean tulingBean=new TulingBean();
        tulingBean.setReqType(0);

        Perception perception=new Perception();
        InputText inputText=new InputText();
        inputText.setText(text);
        perception.setInputText(inputText);

        tulingBean.setPerception(perception);

        UserInfo userInfo=new UserInfo();
        userInfo.setApiKey(apiKey); //acc513be8b5e4b26929247e83132f116
        userInfo.setUserId("java1906");
        tulingBean.setUserInfo(userInfo);
        return tulingBean;
    }

    /**
     *
     * @param text  用户输入的文本内容
     * @param apiKey 机器人key
     * @return
     */
    public String sendMessageText(String text,String apiKey){
        /**2 将JAVA BEAN转换成JSON 字符串 */
       JSONObject jsonTulingBean= JSONObject.fromObject(TulingUtil.getTulingObject(text,apiKey));
        /**3 发送POST请求*/
      JSONObject jsonObject= WeixinUtil.httpRequest(TULING_URL,"POST",jsonTulingBean.toString());
        return TulingUtil.getJsonObject(jsonObject);
    }

    public static String  getJsonObject(JSONObject jsonObject){
        /**4返回结果集 */
        JSONArray jsonArray= (JSONArray) jsonObject.get("results");
        JSONObject jsonObject1= (JSONObject) jsonArray.get(0);
        JSONObject jsonObject2= (JSONObject) jsonObject1.get("values");
        String result=jsonObject2.getString("text");
        return result;
    }
    // 集合数组下标
    public static  int apiKeysIndex=0;
    private static List<String> apiKeys=new ArrayList<>();
    //0  用于标识（是否当天全部执行完一次）。全部有效
    public static  int  flag=0;
    static{
        apiKeys.add("acc513be8b5e4b26929247e83132f116");
        apiKeys.add("fb5a78bb2e79482d8075acd90b13231d");
        apiKeys.add("cd6f7bfdecba4d56a08a6956ea32c0f1");
    }

    public String invoke(String text){

        String apiKey=apiKeys.get(apiKeysIndex);
        String result= this.sendMessageText(text,apiKey);
        //如果 超过了使用范围
        if(result.equals("请求次数超限制!")) {
            //换到下一个KEY 。依次执行。
            if(flag==1){
                return "感谢您的使用，今天工作超负荷了，明天再来服务";
            }
            apiKeysIndex++;
            if(apiKeysIndex>=apiKeys.size()) {
                apiKeysIndex = 0;
                flag=1;
            }
            result = invoke(text);
        }

        return result;
    }



}

