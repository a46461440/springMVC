package com.zxc.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tag")
public class MVCTagTest {

    private static final String INDEX = "tagtest";

    @RequestMapping("/index")
    public String index() {
        return INDEX;
    }

}
