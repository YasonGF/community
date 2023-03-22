package top.tswinter.nowcoder_community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.tswinter.nowcoder_community.util.CommunityUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/alpha")
public class TestController {
    @RequestMapping("/hello")
    @ResponseBody
    public String sayHello() {
        return "hello, springboot app! </br> 你好，哈哈哈";
    }

    private int cnt = 0;
    @GetMapping("/cookie/set")
    @ResponseBody
    public String xxx(HttpServletRequest req, HttpServletResponse resp) {
        // 服务器发送cookie给浏览器
        Cookie cookie =  new Cookie("zxy-key-"+ (++cnt), "zxy_"+ CommunityUtil.generateUUID());

//        cookie.setPath("/community/alpha");     // 设置cookie的生效范围
        cookie.setPath("/");     // 设置cookie的生效范围
        cookie.setMaxAge(60*60);
        resp.addCookie(cookie);
        StringBuilder sb = new StringBuilder();
        sb.append(cookie.getName() + " => " + cookie.getValue());
        return "set cookie: " + sb.toString();
    }

    @GetMapping("/cookie/get")
    @ResponseBody
    public String xxxx(HttpServletRequest req, HttpServletResponse resp) {

        HashMap<String, Object> map = new HashMap<>();
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            String name = cookie.getName();
            String value = cookie.getValue();
            map.put(name, value);
            System.out.println(name+" => " + value);
        }
        return "get:" + map.toString();
    }
}
