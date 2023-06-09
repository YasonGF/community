package top.tswinter.nowcoder_community.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import top.tswinter.nowcoder_community.entity.LoginTicket;
import top.tswinter.nowcoder_community.entity.User;
import top.tswinter.nowcoder_community.service.UserService;
import top.tswinter.nowcoder_community.util.CookieUtil;
import top.tswinter.nowcoder_community.util.HostHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
public class LoginTicketInterceptor implements HandlerInterceptor {
    @Autowired
    private UserService userService;

    @Autowired
    private HostHolder hostHolder;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        String ticket = CookieUtil.getValue(request, "ticket");
        if(ticket!=null) {
            // 查询凭证
            LoginTicket loginTicket =
                    userService.findLoginTicket(ticket);

            if(loginTicket!=null && loginTicket.getStatus() == 0 && loginTicket.getExpired().after(new Date())){
                User user = userService.findUserById(loginTicket.getUserId());

                hostHolder.setUser(user);
            }


        }


        return  true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {
        User user = hostHolder.getUser();
        if(user != null && modelAndView != null) {
            modelAndView.addObject("loginUser", user);
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {
        hostHolder.removeUser();
    }
}
