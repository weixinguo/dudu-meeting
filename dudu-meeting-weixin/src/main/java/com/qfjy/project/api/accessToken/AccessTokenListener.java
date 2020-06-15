package com.qfjy.project.api.accessToken;

import org.springframework.stereotype.Component;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @Classname AccessTokenListener
 * @Author guoweixin
 * @Description TODO
 * @Date 2020/6/12 10:36
 * @Created by Administrator
 */
//@Component
public class AccessTokenListener  implements ServletContextListener {

    @Override
     public void contextInitialized(ServletContextEvent sce) {
        System.out.println("我的应用服务器启动啦----");
        new AccessTokenThread().start();
    }

    @Override
    public  void contextDestroyed(ServletContextEvent sce) {
    }


}
