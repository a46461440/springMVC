package com.zxc.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Controller
@RequestMapping("/chapter3")
public class HelloWorldAction {

    Log log = LogFactory.getLog(HelloWorldActionChapter4.class);

    private long count = 0;

    private Thread thread = new Thread() {
        @Override
        public void run() {
            while (true) {
                count++;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info(count);
            }
        }
    };

    {
        thread.start();
    }

    @RequestMapping("/{name}")
    @ResponseBody
    public String getHelloContent(@PathVariable("name") String name, HttpServletRequest request) {
        Enumeration enumeration = request.getHeaderNames();
        StringBuilder headersInfo = new StringBuilder();
        while (enumeration.hasMoreElements()) {
            Object o = enumeration.nextElement();
            if (o instanceof String) {
                headersInfo.append(o + ":" + request.getHeader((String) o) + "\r\n");
            }
        }
        this.log.info(headersInfo.toString());
        return name + count;
    }

}
