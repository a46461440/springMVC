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
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;

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
    @RequestMapping("/indexForSession")
    public String indexForSession(String request_local, HttpSession session) {
        this.log.info(request_local);
        Locale locale = null;
        if ("zh_CN".equals(request_local)) {
            locale = new Locale("zh", "CN");
        } else if ("en_US".equals(request_local)) {
            locale = new Locale("en", "US");
        } else
            ;
        if (locale != null)
            session.setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, locale);
        return INDEX;
    }
    @RequestMapping("/indexForCookie")
    public String indexForCookie(String request_local, HttpServletRequest request, HttpServletResponse response) {
        this.log.info(request_local);
        Locale locale = null;
        CookieLocaleResolver cookieLocaleResolver = null;
        if ("zh_CN".equals(request_local)) {
            locale = new Locale("zh", "CN");
        } else if ("en_US".equals(request_local)) {
            locale = new Locale("en", "US");
        } else
            ;
        if (locale != null)
            cookieLocaleResolver = new CookieLocaleResolver();
        cookieLocaleResolver.setLocale(request, response, locale);
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
