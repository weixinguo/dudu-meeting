package com.qfjy.project.api.tuling;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Classname TulingTask
 * @Author guoweixin
 * @Description TODO
 * @Date 2020/6/11 15:01
 * @Created by Administrator
 */
@Component
public class TulingTask {
    @Scheduled(cron="0 0 0 * * ?")
    public void task(){
        System.out.println("Hello  Task");
        //图灵智能回复机器人 每天恢复初始值
        TulingUtil.apiKeysIndex=0;
        TulingUtil.flag=0;
    }
}
