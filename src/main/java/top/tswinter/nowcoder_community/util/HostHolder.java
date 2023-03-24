package top.tswinter.nowcoder_community.util;

import org.springframework.stereotype.Component;
import top.tswinter.nowcoder_community.entity.User;

@Component
public class HostHolder {

    private ThreadLocal<User> users = new ThreadLocal<User>();

    public void setUser(User user) {
        users.set(user);
    }

    public User getUser() {
        return users.get();
    }

    public void removeUser() {
        users.remove();
    }
}
