package com.zxc.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/chapter3")
public class HelloWorldAction {

    Log log = LogFactory.getLog(HelloWorldActionChapter4.class);

    @RequestMapping("/hello")
    @ResponseBody
    public String getHelloContent(String name) {
        this.log.info(this);
        return "index";
    }

}
