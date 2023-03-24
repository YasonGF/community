package top.tswinter.nowcoder_community.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.tswinter.nowcoder_community.annotation.LoginRequired;
import top.tswinter.nowcoder_community.entity.User;
import top.tswinter.nowcoder_community.service.UserService;
import top.tswinter.nowcoder_community.util.CommunityUtil;
import top.tswinter.nowcoder_community.util.HostHolder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@Controller
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Value("${community.path.upload}")
    private String uploadPath;
    @Value("${community.path.domain}")
    private String domain;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Autowired
    private UserService userService;

    @Autowired
    private HostHolder hostHolder;

    @LoginRequired
    @RequestMapping(value = "/setting", method = RequestMethod.GET)
    public String getSettingPage() {
        //
        return "/site/setting";
    }



    @PostMapping("/upload")
    @LoginRequired
    public String uploadHeader(MultipartFile headerImage, Model model) {
        if(headerImage == null) {
            model.addAttribute("error", "您还没有选择图片！！！");
            return "/site/setting";
        }

        String filename = headerImage.getOriginalFilename();
        String suffix = filename.substring(filename.lastIndexOf("."));
        if(!StringUtils.hasText(suffix)) {
            model.addAttribute("error", "文件格式不正确！！！");
            return "/site/setting";
        }

        filename =  CommunityUtil.generateUUID() + suffix;

        File dest = new File(uploadPath + "/" + filename);

        try {
            headerImage.transferTo(dest);
        } catch (IOException e) {
            logger.error("上传文件失败： " + e.getMessage());
            throw new RuntimeException(e);
        }

        User user = hostHolder.getUser();
        String headUrl = domain + contextPath + "/user/header/" + filename;
        userService.updateHeader(user.getId(), headUrl);

        return "redirect:/index";
    }

    @GetMapping("/header/{filename}")
    public void getHeader(@PathVariable("filename") String filename, HttpServletResponse resp) {
        filename = uploadPath + "/" + filename;
        String suffix = filename.substring(filename.lastIndexOf("."));
        resp.setContentType("image/"+suffix);

        try (FileInputStream in = new FileInputStream(filename);){
            OutputStream out = resp.getOutputStream();
            byte[] buf = new byte[1024];
            int b = 0;
            while((b = in.read(buf)) != -1) {
                out.write(buf, 0, b);
            }
        } catch (IOException e) {
            logger.error("读取头像失败：" + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/updatePwd")
    @LoginRequired
    public String updatePwd(String srcPwd, String destPwd, Model model) {
        User user = hostHolder.getUser();

        if(!user.getPassword().equals(CommunityUtil.MD5(srcPwd + user.getSalt()))){
            model.addAttribute("pwdMsg", "原密码输入错误！！！");
            return "/site/setting";
        }

        userService.updatePassword(user.getId(), CommunityUtil.MD5(destPwd+user.getSalt()));
        return "redirect:/logout";
    }





}
