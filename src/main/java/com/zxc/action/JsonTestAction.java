package com.zxc.action;

import com.zxc.pojo.RequestMappingPojo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@SessionAttributes(value = {"pojo"}, types = {RequestMappingPojo.class})
@Controller
@RequestMapping("/jsonTest")
public class JsonTestAction {

    private static final Log log = LogFactory.getLog(JsonTestAction.class);

    private static final String SUCCESS = "success";

    //前端ajax如果发送的是非json格式(application/json) post/get请求的参数可以直接获得
    //前端如果发送的是json格式则用RequestBody获取参数到一个对象
    @RequestMapping("/normal")
    public @ResponseBody
    RequestMappingPojo normalGetPutJson(@RequestBody RequestMappingPojo pojo,
                                        Integer age, HttpServletRequest request, Model model) {
        this.log.info(pojo.toString());
        String name = request.getParameter("name");
        this.log.info(name);
        this.log.info(age);
        pojo.setAge(22);
        model.asMap().put("pojo", pojo);
        return pojo;
    }

}
