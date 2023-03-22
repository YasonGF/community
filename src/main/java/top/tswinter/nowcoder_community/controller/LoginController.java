package top.tswinter.nowcoder_community.controller;

import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import top.tswinter.nowcoder_community.config.KaptchaConfig;
import top.tswinter.nowcoder_community.entity.User;
import top.tswinter.nowcoder_community.service.UserService;
import top.tswinter.nowcoder_community.util.CommunityConstant;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

@Controller
public class LoginController implements CommunityConstant {

    @Autowired
    private UserService userService;
    @GetMapping("/register")
    public String getRegisterPage() {
        return "/site/register";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "/site/login";
    }

    @PostMapping("/register")
    public String register(Model model, User user) {
        Map<String, Object> map = userService.register(user);
        if(map == null || map.isEmpty()) {
            model.addAttribute("msg","注册成功，已向您的邮箱发送激活邮件，请尽快激活");
            model.addAttribute("target", "/index");
            return "/site/operate-result";
        }

        model.addAttribute("usernameMsg", map.get("usernameMsg"));
        model.addAttribute("passwordMsg", map.get("passwordMsg"));
        model.addAttribute("emailMsg", map.get("emailMsg"));


        return "/site/register";
    }


    @RequestMapping(value = "/activation/{userId}/{activation_code}", method = RequestMethod.GET)
    public String x(Model model, @PathVariable("userId") int id, @PathVariable("activation_code") String code) {
        int ans = userService.activation(id, code);
        if(ans == ACTIVATION_SUCCESS) {
            model.addAttribute("msg","激活成功，您的账号可以正常使用");
            model.addAttribute("target", "/login");

        }else if(ans == ACTIVATION_REPEAT) {
            model.addAttribute("msg","该账号已经激活过");
            model.addAttribute("target", "/index");

        }else if(ans == ACTIVATION_FAIL) {
            model.addAttribute("msg","激活失败，您提供的激活码错误");
            model.addAttribute("target", "/index");
        }
        return "/site/operate-result";
    }

    @Autowired
    private Producer kaptchaProducer;


    @GetMapping("/kaptcha")
    public void kaptcha(HttpServletResponse resp, HttpSession session) {
        String text = kaptchaProducer.createText();
        BufferedImage bufferedImage = kaptchaProducer.createImage(text);

        session.setAttribute("kaptcha", text);

        resp.setContentType("image/png");
        try {
            OutputStream outputStream = resp.getOutputStream();
            ImageIO.write(bufferedImage, "png", outputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}
