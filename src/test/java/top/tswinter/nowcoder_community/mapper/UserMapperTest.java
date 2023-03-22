package top.tswinter.nowcoder_community.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import top.tswinter.nowcoder_community.Application;
import top.tswinter.nowcoder_community.entity.User;

import java.util.Date;

@SpringBootTest
@ContextConfiguration(classes = Application.class)
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void selectById() {
        User user = userMapper.selectById(1);
        System.out.println(user);
    }

    @Test
    public void selectByName() {
        User user = userMapper.selectByName("lll");
        System.out.println(user);
    }

    @Test
    public void selectByEmail() {
        User user = userMapper.selectByEmail("nowcoder125@sina.com");
        System.out.println(user);
    }

    @Test
    public void insertUser() {
        User user = new User(null, "赵心怡", "123456",  "orPwT","test@sina.com",
                0,0,null, null, new Date());
        System.out.println(user);
        System.out.println("affected rows: " + userMapper.insertUser(user));
    }

    @Test
    public void updateStatus() {
        User user = userMapper.selectById(1);
        System.out.println(user);
    }

    @Test
    public void updateHeader() {
        User user = userMapper.selectById(1);
        System.out.println(user);
    }

    @Test
    public void updatePassword() {
        User user = userMapper.selectById(1);
        System.out.println(user);
    }

}
