package top.tswinter.nowcoder_community.service;

import top.tswinter.nowcoder_community.entity.User;

import java.util.Map;

public interface UserService {
    User findUserById(int id) ;

    public Map<String, Object> register(User user);

    int activation(int userId, String code);
}
