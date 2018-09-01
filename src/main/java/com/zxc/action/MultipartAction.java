package com.zxc.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/multipart")
public class MultipartAction {

    private static final String INDEX = "multipart";

    private Log log = LogFactory.getLog(MultipartAction.class);

    @RequestMapping("/index")
    public String index() {
        return INDEX;
    }

    @RequestMapping(value = "/upFile", method = RequestMethod.POST)
    public String upfile(@RequestParam("name") String name, @RequestParam("file0") MultipartFile file0) {
        this.log.info(name);
        if (!file0.isEmpty()) {
            this.log.info(file0);
            String fileName = file0.getOriginalFilename();
            File file0Path = new File("F://" + fileName);
            try {
                file0.transferTo(file0Path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return INDEX;
    }
}
