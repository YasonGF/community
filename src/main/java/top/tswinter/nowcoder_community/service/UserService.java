package top.tswinter.nowcoder_community.service;

import top.tswinter.nowcoder_community.entity.LoginTicket;
import top.tswinter.nowcoder_community.entity.User;

import java.util.Map;

public interface UserService {
    User findUserById(int id) ;

    public Map<String, Object> register(User user);

    int activation(int userId, String code);

    public Map<String, Object> login(String username, String password, long expiredSec);

    void logout(String ticket);

    public LoginTicket findLoginTicket(String ticket) ;

    public int updateHeader(int userId, String headerUrl);
    public int updatePassword(int userId, String pwd);
}
