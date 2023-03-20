package top.tswinter.nowcoder_community.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import top.tswinter.nowcoder_community.Application;
import top.tswinter.nowcoder_community.entity.User;

@SpringBootTest
@ContextConfiguration(classes = Application.class)

public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void test() {
        User user = userMapper.selectById(1);
        System.out.println(user);
    }
}
