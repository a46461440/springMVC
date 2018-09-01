package com.zxc.interceptor;

import com.zxc.pojo.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {

    private static final String[] NO_NEED = {"/interceptor/index", "/interceptor/login"};

    private Log log = LogFactory.getLog(LoginInterceptor.class);

    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        boolean canPass = false;
        String path = httpServletRequest.getServletPath();
        for (String no_need : NO_NEED) {
            if (no_need.equals(path)) {
                canPass = true;
                break;
            }
        }
        HttpSession session = httpServletRequest.getSession();
        if (!canPass) {
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                if (user != null && user.getName().equals("zxc"))
                    canPass = true;
            }
        }
        this.log.info("loginHandlerPre");
        if (!canPass)
            httpServletRequest.getRequestDispatcher("/interceptor/login").forward(httpServletRequest, httpServletResponse);
        return canPass;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        this.log.info("loginHandlerPost");
    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        this.log.info("loginHandlerAfter");
    }
}
