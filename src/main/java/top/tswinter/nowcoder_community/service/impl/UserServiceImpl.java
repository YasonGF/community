package top.tswinter.nowcoder_community.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import top.tswinter.nowcoder_community.entity.User;
import top.tswinter.nowcoder_community.mapper.UserMapper;
import top.tswinter.nowcoder_community.service.UserService;
import top.tswinter.nowcoder_community.util.CommunityConstant;
import top.tswinter.nowcoder_community.util.CommunityUtil;
import top.tswinter.nowcoder_community.util.MailClient;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService, CommunityConstant {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MailClient mailClient;
    @Autowired
    private TemplateEngine templateEngine;

    @Value("${community.path.domain}")
    private String domain;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Override
    public User findUserById(int id) {
        //
        return userMapper.selectById(id);
    }

    // 注册
    public Map<String, Object> register(User user) {
        HashMap<String, Object> map = new HashMap<>();
        if (user == null) throw new IllegalArgumentException("user不能为null");

        if (!StringUtils.hasText(user.getUsername())) {
            map.put("usernameMsg", "账号不能为空！");
            return map;
        }

        if (!StringUtils.hasText(user.getPassword())) {
            map.put("passwordMsg", "密码不能为空！");
            return map;
        }

        if (!StringUtils.hasText(user.getEmail())) {
            map.put("emailMsg", "邮箱不能为空！");
            return map;
        }

        User u = userMapper.selectByName(user.getUsername());
        if (u != null) {
            map.put("usernameMsg", "该账号已经存在！");
            return map;
        }

        u = userMapper.selectByEmail(user.getEmail());
        if (u != null) {
            map.put("emailMsg", "该邮箱已被使用，不可重复注册！");
            return map;
        }

        user.setSalt(CommunityUtil.generateUUID().substring(0, 5));
        user.setPassword(CommunityUtil.MD5(user.getPassword() + user.getSalt()));
        user.setType(0);
        user.setStatus(0);
        user.setActivationCode(CommunityUtil.generateUUID());
        user.setHeaderUrl(String.format("https://images.nowcoder.com/head/%dt.png",
                new Random().nextInt(1000)));
        user.setCreateTime(new Date());
        userMapper.insertUser(user);

        user=userMapper.selectByEmail(user.getEmail());     // 获取自增长的id

        // 发送邮件
        Context context = new Context();
        context.setVariable("email", user.getEmail());
        String url = domain + contextPath + "/activation/" + user.getId() + "/" + user.getActivationCode();
        context.setVariable("url", url);

        System.out.println("url:" + url);


        String content = templateEngine.process("/mail/activation", context);
        mailClient.sendMail(user.getEmail(), "激活账号", content);
        return map;
    }

    public int activation(int userId, String code) {
        User user = userMapper.selectById(userId);
        if(user.getStatus() == 1) {
            return ACTIVATION_REPEAT;
        }
        if(user.getActivationCode().equals(code)) {
            userMapper.updateStatus(userId, 1);     // status=1,成功激活
            return ACTIVATION_SUCCESS;
        }
        return ACTIVATION_FAIL;
    }
}
