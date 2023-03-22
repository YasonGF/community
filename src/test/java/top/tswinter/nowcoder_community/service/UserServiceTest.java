package top.tswinter.nowcoder_community.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import top.tswinter.nowcoder_community.Application;
import top.tswinter.nowcoder_community.entity.User;

@SpringBootTest
@ContextConfiguration(classes = Application.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void test() {
        User user = new User();
        user.setUsername("赵心怡");
        user.setPassword("123456");
        user.setEmail("1693916428@qq.com");
        userService.register(user);
    }
}
