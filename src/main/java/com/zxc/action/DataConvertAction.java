package com.zxc.action;

import com.zxc.convert.DateEditor;
import com.zxc.pojo.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Date;

@Controller
@RequestMapping("/dataConvert")
public class DataConvertAction {

    private static final String INDEX = "dataconvert";

    private static final Log log = LogFactory.getLog(DataConvertAction.class);

    //InitBinder注解可以注册conventer接口 优先级大于全局conventer
    @InitBinder
    public void init(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new DateEditor());
    }

    @RequestMapping("/index")
    public String index(Model model) {
        model.addAttribute("user", new User());
        return INDEX;
    }

    @RequestMapping("/testData")
    public String testData(@RequestParam("date") Date date, User user) {
        this.log.info(user);
        this.log.info(date);
        return INDEX;
    }

    @RequestMapping("/testValidate")
    public String testValidate(@Valid @ModelAttribute User user, BindingResult bindingResult) {
        this.log.info(user);
        this.log.info(bindingResult);
        if (bindingResult.hasErrors())
            return INDEX;
        return "dataconvertsuccess";
    }

}
