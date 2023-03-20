package top.tswinter.nowcoder_community.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.tswinter.nowcoder_community.entity.User;
import top.tswinter.nowcoder_community.mapper.UserMapper;
import top.tswinter.nowcoder_community.service.UserService;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUserById(int id) {
        return userMapper.selectById(id);
    }
}
