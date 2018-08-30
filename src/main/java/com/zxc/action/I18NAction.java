package com.zxc.action;

import com.zxc.pojo.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@SessionAttributes(value = {"sessionUser"})
@Controller
@RequestMapping("/i18N")
public class I18NAction {

    private static final String INDEX = "i18N";

    private static final Log log = LogFactory.getLog(I18NAction.class);

    @RequestMapping("/index")
    public String index() {
        return INDEX;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute @Validated User user, Model model,
                        HttpSession session, HttpServletRequest request) {
        this.log.info(user.hashCode());
        if ("zxc".equals(user.getName())) {
            model.asMap().put("sessionUser", user);
            //以下两步可以获得国际化的信息
            RequestContext requestContext = new RequestContext(request);
            String name = requestContext.getMessage("username");
            model.addAttribute("username", name);
        }
        this.log.info(session.getAttribute("user"));
        return INDEX;
    }

}
