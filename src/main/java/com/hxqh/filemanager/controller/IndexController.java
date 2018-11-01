package com.hxqh.filemanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Ocean Lin on 2018/10/30.
 *
 * @author Lin
 */
@Controller
public class IndexController {

    /**
     * index
     *
     * @return
     */
    @RequestMapping("/")
    public String index() {
        return "user/index";
    }

}
