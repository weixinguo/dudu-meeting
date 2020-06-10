package com.qfjy.controller;

import com.qfjy.entity.po.Demo;
import com.qfjy.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Classname DemoController
 * @Author guoweixin
 * @Description TODO
 * @Date 2020/6/10 12:13
 * @Created by Administrator
 */
@RestController
@RequestMapping("weixin")
public class DemoController {
    @Autowired
    private DemoService demoService;

    @GetMapping("{id}")
    public Demo selectById(@PathVariable("id") Integer id){
        return  demoService.selectByPrimaryKey(id);
    }
}
