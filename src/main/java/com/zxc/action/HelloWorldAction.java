package com.zxc.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/chapter3")
public class HelloWorldAction {

    @RequestMapping("/hello")
    @ResponseBody
    public String getHelloContent(String name) {
        return "index";
    }

}
