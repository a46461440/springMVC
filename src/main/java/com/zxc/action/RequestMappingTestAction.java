package com.zxc.action;

import com.zxc.pojo.RequestMappingPojo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
@RequestMapping("/requestMappingTest")
@SessionAttributes(types = {String.class, AtomicInteger.class}, value = {"sessionId", "atomicIntegerA"})
public class RequestMappingTestAction {

    private static final Log log = LogFactory.getLog(RequestMappingTestAction.class);

    private static final String SUCCESS = "success";

    @Autowired
    private HttpServletRequest request;

    private static Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            while (true) {
                atomicIntegerA.getAndIncrement();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {

                }
            }
        }
    }, "自己创建的线程");

    //该方法会在所有requestMapping执行之前执行
    //并且给model设置ModelAttribute的value和该方法的返回值为k、v
    @ModelAttribute("ModelAttributeSetKey")
    public String testModelAttributeWithValueAndReturn(@RequestParam(value = "object", required = false) String name) {
        this.log.info(thread.isAlive());
        if (!thread.isAlive()) {
            thread.interrupt();
            //如果以下条件正确则这条线程未启动
            if (!thread.isAlive())
                thread.start();
        }
        this.log.info(thread.isAlive());
        return name;
    }

    //该方法会在所有requestMapping执行之前执行
    //并且设置该方法的返回值的类名的shortName 如：Integer -> integer 为key，返回值为value设置进入model
    @ModelAttribute
    public Integer testModelAttributeWithFunctionReturnButNoValue() {
        return new Integer(1);
    }

    //ModelAttribute属性设置在无返回值的方法上 该方法会在所有requestMapping执行之前执行(每次请求)
    @ModelAttribute
    public void testModelAttributeWithoutReturnNeitherValue(ModelMap map) {
        map.addAttribute("string", "stringValue");
        RequestMappingPojo pojo = new RequestMappingPojo();
        pojo.setName("zxc");
        map.addAttribute("requestMappingPojo", pojo);
    }

    @RequestMapping("/index")
    @ModelAttribute("something")
    public String mix() {
        return "mix";
    }

    //设置http方式 设置quertString 将queryString自动设置进入pojo实例中
    @RequestMapping(value = "/testModel", method = {RequestMethod.GET})
    public String testModelAndParamAutoInject(Model model, @RequestParam(value = "name2", required = false) String name,
                            RequestMappingPojo pojo) {
        model.addAttribute("object", name);
        //这里springMVC判断当name为空的时候取类名会报错
        if (name != null)
            model.addAttribute(name);
        Map map = model.asMap();
        Iterator iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            this.log.info(entry.getKey() + ":" + entry.getValue());
        }
        return SUCCESS;
    }

    //设置http方式 设置model中的内容 设置viewName
    @RequestMapping(value = "/testModel2", method = {RequestMethod.GET})
    public ModelAndView testModelAndView(String name) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("name", name);
        modelAndView.setViewName(SUCCESS);
        return modelAndView;
    }

    //Rest
    @RequestMapping(value = "/pathVariable/{name}", method = RequestMethod.GET)
    public String testPathVariable(@PathVariable(value = "name") String name, Model model) {
        model.addAttribute("name", name);
        return SUCCESS;
    }

    //得到header 默认必须有
    @RequestMapping(value = "/header/encoding")
    public String testHeader(@RequestHeader(value = "Accept-Encoding") String encoding
            , @RequestHeader(value = "User-Agent") String userAgent) {
        this.log.info(encoding);
        this.log.info(userAgent);
        return SUCCESS;
    }

    private static final AtomicInteger atomicIntegerA = new AtomicInteger(0);

    //CookieValue得到JSESSIONID 在上面sessionAttribute设置session的属性 如果model中有sessionId则设置进去
    @RequestMapping(value = "/cookie")
    public String testCookieValue(@CookieValue(value = "JSESSIONID", defaultValue = "") String sessionId
            , Model model, HttpSession session) {
        this.log.info(session.getAttribute("sessionId"));
        model.addAttribute("object", sessionId);
        model.addAttribute("sessionId", sessionId);
        model.addAttribute("atomicIntegerA", atomicIntegerA);
        this.log.info(sessionId);
        this.log.info(session.getAttribute("atomicIntegerA"));
        return SUCCESS;
    }

    @RequestMapping("/getModelValue")
    public String getModelValue(Model model) {
        this.log.info(model);
        return SUCCESS;
    }

    //对HttpServletRequest进行Autowired是线程安全的 因为它的实现是ThreadLocal
    @RequestMapping("/getRequestHashCode")
    public String getRequestHashCode() {
        this.log.info(this.request.hashCode());
        return SUCCESS;
    }

    //ModelAttribute修饰参数 只能修饰已经存在model里的对象
    @RequestMapping("/changePojo")
    public String modelAttributeInParamChangePojo(@ModelAttribute("requestMappingPojo") RequestMappingPojo pojo
            , Model model) {
//        pojo.setName("zxm");
        this.log.info(model);
        return SUCCESS;
    }

}
