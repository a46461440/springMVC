package com.zxc.action;

import com.zxc.pojo.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/interceptor")
@SessionAttributes(value = "user")
public class LoginAction {

    private static final String INDEX = "login";

    private Log log = LogFactory.getLog(LoginAction.class);

    @RequestMapping("/index")
    public String index() {
        return INDEX;
    }

    @RequestMapping("/login")
    public String login(User user, Model model) {
        this.log.info(user);
        model.addAttribute("user", user);
        if (user != null && !user.getName().equals("zxc"))
            return INDEX;
        return "success";
    }

}
