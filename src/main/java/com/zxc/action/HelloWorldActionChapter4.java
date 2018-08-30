package com.zxc.action;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.mvc.LastModified;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component("/lastModified")
public class HelloWorldActionChapter4 extends AbstractController implements LastModified{

    private long lastModified;

    @RequestMapping("/hello")
    public String getHelloContent(String name) {
        return "index";
    }

    public long getLastModified(HttpServletRequest httpServletRequest) {
        if (lastModified == 0L)
            lastModified = System.currentTimeMillis();
        return lastModified;
    }

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }
}
